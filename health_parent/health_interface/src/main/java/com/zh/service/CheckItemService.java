package com.zh.service;

import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
