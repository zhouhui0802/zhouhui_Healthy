package com.zh.jobs;

import com.zh.constant.RedisConstant;
import com.zh.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
        Set<String> sdiff = jedisPool.getResource().sdiff( RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES );
        //判断是否存在差积
        if (sdiff!=null) {
            for (String picName : sdiff) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiNiu( picName );
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem( RedisConstant.SETMEAL_PIC_RESOURCES, picName );
            }
        }
    }

}
