package com.sparta.apiserver1.Common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    //com/sparta/apiserver1/Comment/controller 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
    @Pointcut("execution(* com.sparta.apiserver1.Comment.controller..*.*(..))")
    private void comment() {}
    @Pointcut("execution(* com.sparta.apiserver1.Like.controller..*.*(..))")
    private void like() {}
    @Pointcut("execution(* com.sparta.apiserver1.Post.controller..*.*(..))")
    private void post() {}
    @Pointcut("execution(* com.sparta.apiserver1.User.controller..*.*(..))")
    private void user() {}

    //comment() 등의 메서드가 실행 되는 지점 이전에 before() 메서드 실행
    @Before("comment()||like()||post()||user()")
    public void before(JoinPoint joinPoint) {

        //실행되는 함수 이름을 가져오고 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName() + "메서드 실행");

        //메서드에 들어가는 매개변수 배열을 읽어옴
        Object[] args = joinPoint.getArgs();

        //매개변수 배열의 종류와 값을 출력
        for(Object obj : args) {
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    //comment() 등의 메서드가 종료되는 시점에 afterReturn() 메서드 실행
    //@AfterReturning 어노테이션의 returning 값과 afterReturn 매개변수 obj의 이름이 같아야 함
    @AfterReturning(value = "comment()||like()||post()||user()", returning = "obj")
    public void afterReturn(JoinPoint joinPoint, Object obj) {
        System.out.println("return obj");
        System.out.println(obj);
    }
}