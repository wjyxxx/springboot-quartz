package com.youzi.springbootquartz.jobimpl;

import com.youzi.springbootquartz.ext.SysExt;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 *Job能通过JobExecutionContext对象访问到Quartz运行时候的环境以及Job本身的明细数据
 */
@Component
public class QuartzJobImpl implements Job {

    @Autowired
    private SysExt sysExt;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获得触发器中的JobDataMap()
//        JobDataMap jobDataMap =(JobDataMap) jobExecutionContext.getTrigger().getJobDataMap().get("jobDataMap");

       //具体执行的方法在SysExt类中执行
        try {
            sysExt.execute(jobExecutionContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
