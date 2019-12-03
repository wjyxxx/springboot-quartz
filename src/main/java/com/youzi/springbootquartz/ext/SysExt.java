package com.youzi.springbootquartz.ext;

import com.youzi.springbootquartz.bean.User;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

@Component
public class SysExt {

    /**
     * 具体执行定时任务中的方法的实现
     * @param jobExecutionContext
     */
    public void execute(JobExecutionContext jobExecutionContext) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        //获得触发器中的JobDataMap()
        JobDataMap jobDataMap =(JobDataMap) jobExecutionContext.getTrigger().getJobDataMap().get("jobDataMap");

        //获得service对象的字节码
        Class className = (Class)jobDataMap.get("className");
        //获得方法名
        String methodName = jobDataMap.getString("methodName");
        //获得方法的参数
        Object parameters = parseParameters(jobDataMap);

        System.out.println("className :" + className);
        System.out.println("methodName :" + methodName);
        System.out.println("parameters :" + parameters);

        //反射操作
        Method[] methods = className.getMethods();
        for (Method method : methods){
            if (method.getName().equals(methodName)){
                method.invoke(className.newInstance(), parameters);
            }
        }


    }


    /**
     * 解析传过来的参数类型
     */
    public Object parseParameters(JobDataMap jobDataMap) {
        Object parameters = jobDataMap.get("parameters");
        return parameters;
    }
}
