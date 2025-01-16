package ido.style.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Log4j2
@Aspect
@Component
public class ControllerAspect {
    // 컨트롤러의 메서드가 실행될 때 실행되는 로그
    @Before("within(ido.style.controller.* && !ido.style.controller.FileController)")
    public void method_start_log(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("==== " + method.getName() + " ====");
        Object[] arguments = joinPoint.getArgs();
        Parameter[] params = method.getParameters();
        for (int i = 0; i < params.length; i++) {
            log.info(params[i].getName() + ": " + arguments[i] );
        }
    }
}
