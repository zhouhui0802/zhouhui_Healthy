package com.zh;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("spring-jobs.xml");
        context.getBean("jobDetail");
    }
}
