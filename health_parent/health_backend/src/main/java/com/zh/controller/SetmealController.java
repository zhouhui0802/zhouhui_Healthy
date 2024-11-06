package com.zh.controller;

import com.zh.constant.MessageConstant;
import com.zh.constant.RedisConstant;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.entity.Result;
import com.zh.pojo.Setmeal;
import com.zh.service.SetmealService;
import com.zh.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 文件上传
     *
     * @param imgFile 前端发送来的文件
     * @return 上传七牛云成功与否
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        //获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        //拆分字符串获取文件后缀
        int index = originalFilename.lastIndexOf( "." ) ;
        String extension = originalFilename.substring(index-1);
        String fileName= UUID.randomUUID().toString()+extension;
        //创建UUID.文件类型新文件名防止覆盖 类似于：b17f24ff026d40949c85a24f4f375d42.jpg
        try {
            //将文件上传到七牛云服务器
            QiniuUtils.uploadToQiNiu( imgFile.getBytes(), fileName );
            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd( RedisConstant.SETMEAL_PIC_RESOURCES, fileName );
        } catch (IOException e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
        //服务调用成功
        return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
    }

    /**
     * 新增体检套餐
     *
     * @param setmeal       套餐信息
     * @param checkGroupIds 检查组id集合
     * @return 新增是够成功
     */
    @PostMapping("/add/{checkGroupIds}")
    public Result add(@RequestBody Setmeal setmeal, @PathVariable("checkGroupIds") Integer[] checkGroupIds) {
        try {
            setmealService.add( setmeal, checkGroupIds );
        } catch (Exception e) {
            //新增套餐失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean 分页条件
     * @return 分页结果数据封装对象
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return setmealService.pageQuery( queryPageBean );
    }

}
