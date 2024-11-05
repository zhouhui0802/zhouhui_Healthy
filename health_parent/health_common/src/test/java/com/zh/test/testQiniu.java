package com.zh.test;

import com.zh.utils.QiniuUtils;
import org.junit.Test;

public class testQiniu {

    @Test
    public void upload() {
        QiniuUtils.uploadToQiNiu( "D:\\bg.jpg", "bg.jpg" );
    }

    @Test
    public void delete() {
        QiniuUtils.deleteFileFromQiNiu( "bg.jpg" );
    }

}
