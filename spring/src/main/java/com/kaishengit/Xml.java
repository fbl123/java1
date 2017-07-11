package com.kaishengit;

        import org.junit.Test;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Administrator on 2017/7/10.
 * @Configuration 替代Spring配置文件
 * @ComponentScan  开启自动扫描
 * @EnableAspectJAutoProxy 开启AOP注解模式
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Xml {
}
