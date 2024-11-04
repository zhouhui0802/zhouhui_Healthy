package com.zh.service;

import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.CheckGroup;

public interface CheckGroupService {

    /**
     * 新增检查组
     *
     * @param checkGroup   检查组信息
     * @param checkitemIds 与检查组关联的检查项id
     */
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询
     *
     * @param queryPageBean 分页条件封装类
     * @return 分页结果
     */
    PageResult pageQuery(QueryPageBean queryPageBean);
}
