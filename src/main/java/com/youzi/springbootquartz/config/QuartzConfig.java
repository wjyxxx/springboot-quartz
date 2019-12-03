package com.youzi.springbootquartz.config;

import com.youzi.springbootquartz.factory.TaskJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 主要是为了生成scheduler调度器
 */
//@Configuration
public class QuartzConfig {

    //注入数据源
    @Autowired
    DataSource dataSource;

    //注入任务工厂
    @Autowired
    TaskJobFactory jobFactory;

    @Bean(name="schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

        //创建读取配置文件的工厂实例
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //设置本地配置文件
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //设置在本地配置文件被读取后在初始化对象
        propertiesFactoryBean.afterPropertiesSet();

        //创建任务调度工厂
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //任务调度工厂设置quartz的属性配置
        schedulerFactoryBean.setQuartzProperties(propertiesFactoryBean.getObject());
        //任务调度工厂设置任务工厂
        schedulerFactoryBean.setJobFactory(jobFactory);

        //返回调度工厂对象
        return schedulerFactoryBean;
    }


    /**
     * 获得调度器对象
     * @return
     */
    @Bean(name = "scheduled")
    public Scheduler scheduled() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
