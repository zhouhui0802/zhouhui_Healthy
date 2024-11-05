package com.zh.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zh.dao.SetmealDao;
import com.zh.pojo.Setmeal;
import com.zh.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setmealDao.add( setmeal );
        Integer setmealId = setmeal.getId();
        this.setSetmealAndCheckGroup( setmealId, checkGroupIds );
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
