package com.zh.service;

import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.CheckGroup;
import java.util.List;
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

    /**
     * 查询检查组
     *
     * @param id 检查组id
     * @return 指定检查组信息
     */
    CheckGroup findById(Integer id);

    /**
     * 根据检查组id查询对应的所有检查项id
     *
     * @param id 检查组id
     * @return 指定检查组关联的所有检查项
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
}
