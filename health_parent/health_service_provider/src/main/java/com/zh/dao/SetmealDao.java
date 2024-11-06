package com.zh.dao;

import com.github.pagehelper.Page;
import com.zh.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {

    /**
     * 添加套餐
     *
     * @param setmeal 套餐信息
     */
    void add(Setmeal setmeal);


    /**
     * 添加套餐和检查组多对多关系
     *
     * @param map 套餐id合检查组id
     */
    void setSetmealAndCheckGroup(Map<String, Integer> map);

    /**
     * 分页查询体检套餐
     *
     * @param queryString 分页条件
     * @return 页面结果
     */
    Page<Setmeal> selectByCondition(String queryString);
}
