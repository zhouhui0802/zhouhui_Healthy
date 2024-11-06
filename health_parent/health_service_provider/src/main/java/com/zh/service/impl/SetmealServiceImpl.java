package com.zh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh.constant.RedisConstant;
import com.zh.dao.SetmealDao;
import com.zh.entity.PageResult;
import com.zh.entity.QueryPageBean;
import com.zh.pojo.Setmeal;
import com.zh.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setmealDao.add( setmeal );
        Integer setmealId = setmeal.getId();
        this.setSetmealAndCheckGroup( setmealId, checkGroupIds );
        //将图片名称保存到Redis
        jedisPool.getResource().sadd( RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg() );
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page=setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 绑定套餐和检查组的多对多关系
     *
     * @param setmealId     套餐id
     * @param checkGroupIds 检查组id
     */
    public void setSetmealAndCheckGroup(Integer setmealId, Integer[] checkGroupIds) {
        if (checkGroupIds!=null && checkGroupIds.length>0) {
            for (Integer checkGroupId : checkGroupIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put( "setmealId", setmealId );
                map.put( "checkgroupId", checkGroupId );
                setmealDao.setSetmealAndCheckGroup( map );
            }
        }
    }
}
