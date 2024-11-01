package com.zh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zh.constant.MessageConstant;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.entity.Result;
import com.zh.pojo.CheckItem;
import com.zh.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    //通过Dubbo在zk注册中心中查找服务
    @Reference
    private CheckItemService checkItemService;


    //新增检查项目
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {

        try{
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }
        return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult pageResult=checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }
}
