package com.zh.dao;

import com.github.pagehelper.Page;
import com.zh.pojo.CheckItem;



public interface CheckItemDao {

    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

}
