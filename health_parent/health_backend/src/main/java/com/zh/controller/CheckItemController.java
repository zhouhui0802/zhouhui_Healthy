package com.zh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zh.constant.MessageConstant;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.entity.Result;
import com.zh.pojo.CheckItem;
import com.zh.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    //通过Dubbo在zk注册中心中查找服务
    @Reference
    private CheckItemService checkItemService;


    //新增检查项目
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {

        try{
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }
        return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult pageResult=checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {

       try{
           checkItemService.deleteById(id);
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
       }

       return new Result(false, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {

        try{
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }

        return new Result(false, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) {

        try{
            CheckItem checkItem=checkItemService.findById(id);
            return new Result(false, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }


    }
}
