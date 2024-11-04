package com.zh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zh.constant.MessageConstant;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.entity.Result;
import com.zh.pojo.CheckGroup;
import com.zh.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @PostMapping("/add/{checkItemIds}")
    public Result add(@RequestBody CheckGroup checkGroup, @PathVariable("checkItemIds") Integer[] checkItemIds) {
        try {
            checkGroupService.add( checkGroup, checkItemIds );
        } catch (Exception e) {
            e.printStackTrace();
            //新增检查组失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        //新增检查组成功
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean 分页条件
     * @return 分页结果数据封装对象
     */
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.pageQuery( queryPageBean );
    }

}
