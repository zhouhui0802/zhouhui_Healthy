package com.zh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh.dao.CheckGroupDao;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.CheckGroup;
import com.zh.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组，操作t_checkgroup表
        checkGroupDao.add( checkGroup );
        //设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        Integer checkGroupId = checkGroup.getId();

        this.setCheckGroupAndCheckItem( checkGroupId, checkitemIds);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString=queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page=checkGroupDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById( id );
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId( id );
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        //修改检查组基本信息，操作检查组t_checkgroup表
        checkGroupDao.edit( checkGroup );
        //根据检查组id删除中间表数据（清理原有关联关系）
        checkGroupDao.deleteAssociation( checkGroup.getId() );
        //建立检查组和检查项的最新关联关系
        this.setCheckGroupAndCheckItem( checkGroup.getId(), checkItemIds );
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if(checkitemIds!=null&&checkitemIds.length>0){
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put( "checkgroupId", checkGroupId );
                map.put( "checkitemId", checkitemId );
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

}
