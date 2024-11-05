package com.zh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zh.constant.MessageConstant;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.entity.Result;
import com.zh.pojo.CheckGroup;
import com.zh.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        try{
            CheckGroup checkGroup=checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 根据检查组id查询对应的所有检查项id
     *
     * @param id 检查组id
     * @return 指定检查组关联的所有检查项
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId/{id}")
    public Result findCheckItemIdsByCheckGroupId(@PathVariable("id") Integer id) {
        try {
            List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId( id );
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


    /**
     * 编辑检查组
     *
     * @param checkGroup   检查组信息
     * @param checkItemIds 与检查组关联的检查项id
     * @return 新增成功或者失败提示
     */
    @PutMapping("/edit/{checkItemIds}")
    public Result edit(@RequestBody CheckGroup checkGroup, @PathVariable("checkItemIds") Integer[] checkItemIds) {
        try {
            checkGroupService.edit( checkGroup, checkItemIds );
        } catch (Exception e) {
            //服务调用失败
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        //服务调用成功
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 查询所有检查组
     *
     * @return 检查组信息集合
     */
    @GetMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckGroup> list=checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            //服务调用失败
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

}
