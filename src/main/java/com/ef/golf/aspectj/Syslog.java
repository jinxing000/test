package com.ef.golf.aspectj;

/**
 * com.ef.golf.aspectj
 * Administrator
 * 2018/10/13 10:45
 */


import java.lang.annotation.*;


/**
 @Target(ElementType.TYPE)   //接口、类、枚举、注解
 @Target(ElementType.FIELD) //字段、枚举的常量
 @Target(ElementType.METHOD) //方法
 @Target(ElementType.PARAMETER) //方法参数
 @Target(ElementType.CONSTRUCTOR)  //构造函数
 @Target(ElementType.LOCAL_VARIABLE)//局部变量
 @Target(ElementType.ANNOTATION_TYPE)//注解
 @Target(ElementType.PACKAGE) ///包 
 @Document：说明该注解将被包含在javadoc中
 @Inherited：说明子类可以继承父类中的该注解
 @Retention(RetentionPolicy.RUNTIME) 定义的这个注解是注解会在class字节码文件中存在，在运行时可以通过反射获取到。
 * */

@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Syslog {
    String title() default "";
    String methods() default "";
    String description() default "";
}

