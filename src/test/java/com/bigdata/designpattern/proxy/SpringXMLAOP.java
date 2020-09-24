package com.bigdata.designpattern.proxy;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Car4 {
    public void move(){
        System.out.println("wu~~~");
    }
}

class LoggingProxy{

    private long startMills, stopMills;

    private void preLogging() {
        System.out.println("start logging");
    }

    private void postLogging() {
        System.out.println("stop logging");
    }

}

@Aspect
class TimerProxy{
    private long startMills, stopMills;

    @Before("execution(* com.bigdata.designpattern.proxy.Car4.move())")
    private void startTiming() {
        startMills = System.currentTimeMillis();
    }

    @After("execution(* com.bigdata.designpattern.proxy.Car4.move())")
    private void stopTiming() {
        stopMills = System.currentTimeMillis();
        System.out.println("total cost " + (stopMills - startMills) + "ms");
    }

    /**
     * @Before 前置通知，目标方法执行前，然后执行
     * @After 后置通知，目标方法执行后，然后执行
     * @Around 环绕通知，目标方法执行前、执行后，都要执行
     * @AfterReturn 方法返回通知，目标方法执行并返回后，然后执行
     * @AfterThrowing 发生异常后通知，目标方法执行过程发送异常时，然后执行
     */

}


/**
 * AOP Aspect Oriented Programing 面向切面编程，实现的就是动态代理理念；
 * Spring 框架核心是 IOC + AOP
 * 正是bean工厂的灵活装配，加上aop的动态拼接，成就了spring框架的霸主地位
 */
public class SpringXMLAOP {

    /**
     * 基于xml配置切面
     */
    @Test
    public void createAopByXmlConfig(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("designpattern/proxy/spring-xml-aop.xml");
        Car4 bean = (Car4)context.getBean("car4");
        bean.move();
    }

    /**
     * 基于注解配置切面
     */
    @Test
    public void createAopByAnnotationConfig(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("designpattern/proxy/spring-anno-aop.xml");
        Car4 bean = (Car4)context.getBean("car4");
        bean.move();
    }


}
