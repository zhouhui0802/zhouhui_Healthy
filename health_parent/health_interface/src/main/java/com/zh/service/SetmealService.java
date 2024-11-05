package com.zh.service;

import com.zh.pojo.Setmeal;

public interface SetmealService {

    /**
     * 新增套餐
     *
     * @param setmeal       套餐信息
     * @param checkGroupIds 检查组id集合
     */
    void add(Setmeal setmeal, Integer[] checkGroupIds);
}
