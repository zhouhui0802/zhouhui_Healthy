package com.zh.dao;

import com.github.pagehelper.Page;
import com.zh.pojo.CheckGroup;

import java.util.List;
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

    Page<CheckGroup> selectByCondition(String queryString);

    /**
     * 查询检查组
     *
     * @param id 检查组id
     * @return 对应检查组信息
     */
    CheckGroup findById(Integer id);

    /**
     * 根据检查组id查询对应的所有检查项id
     *
     * @param id 检查组id
     * @return 检查项id集合
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

}
