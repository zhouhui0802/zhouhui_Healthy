package com.zh.dao;

import com.zh.pojo.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {

    /**
     * 添加检查组
     *
     * @param checkGroup 检查组信息
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组和检查项多对多关系
     *
     * @param map 检查组id合检查项id
     */
    void setCheckGroupAndCheckItem(Map<String, Integer> map);
}
