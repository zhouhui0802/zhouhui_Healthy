package com.zh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh.dao.CheckItemDao;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.CheckItem;
import com.zh.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;


    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }


    //检查项分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        Integer currentPage=queryPageBean.getCurrentPage();
        Integer pageSize=queryPageBean.getPageSize();
        String queryString=queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);

        Page<CheckItem> page=checkItemDao.selectByCondition(queryString);

        long total=page.getTotal();
        List<CheckItem> rows=page.getResult();

        return new PageResult(total,rows);
    }
}
