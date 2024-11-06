package com.zh.service;

import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.Setmeal;

public interface SetmealService {

    /**
     * 新增套餐
     *
     * @param setmeal       套餐信息
     * @param checkGroupIds 检查组id集合
     */
    void add(Setmeal setmeal, Integer[] checkGroupIds);

    /**
     * 分页查询
     *
     * @param queryPageBean 分页条件封装类
     * @return 分页结果
     */
    PageResult pageQuery(QueryPageBean queryPageBean);
}
