package com.wotung.integration.member.web.aop;

import com.wotung.integration.member.web.vo.Request;
import com.wotung.integration.member.web.vo.entity.ReqEntity;
import com.wotung.integration.member.web.vo.header.ReqHeader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class WebTokenAuthenticationAspect {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    private final static String UPDATE_MEMBER = "execution(public * com.wotung.integration.member.web.v1.LoginController.updateMember(..) ) ";
    private final static String DELETE_MEMBER = "execution(public * com.wotung.integration.member.web.v1.LoginController.deleteMember(..) ) ";

    private final String POINT_CUT = UPDATE_MEMBER + " || " + DELETE_MEMBER;

    @Pointcut(POINT_CUT)
    public void pointCut() { }

//    ThreadLocal<String> tokens = new ThreadLocal<String>();

    @Before(value = "pointCut()")
    public void around(JoinPoint joinPoint) {
        logger.info("前置通知");
        // 处理token
        Request<ReqEntity> request =  (Request<ReqEntity>)joinPoint.getArgs()[0];
        ReqHeader reqHeader = request.getReqHeader();
        String token = reqHeader.getAccessToken();
//        tokens.set(token);
    }

    @Around(value = "pointCut()")
    public Object assertAround(ProceedingJoinPoint pjp) {
        //判断注解标识如果不为false则，进行登录
        Class<?> aClass = pjp.getTarget().getClass(); //先获取被织入增强处理的目标对象，再获取目标类的clazz
        String methodName = pjp.getSignature().getName(); //先获取目标方法的签名，再获取目标方法的名
        logger.info("methodName: "+methodName);  // 输出目标方法名
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes(); //获取目标方法参数类型
        Object[] args = pjp.getArgs();  //获取目标方法的入参
        for (int i = 0; i < args.length; i++) {
            logger.info("argsName: "+args[i]); //输出目标方法的参数
        }
        try {
            Method method = aClass.getMethod(methodName, parameterTypes);  //获取目标方法
//            AssertOK annotation = method.getAnnotation(AssertOK.class);  //获取方法上的注解
//            annotation.isLogin();  //获取注解函数值
            long starttime = System.currentTimeMillis();
            Object proceed = pjp.proceed();  //执行目标方法
            long exctime = System.currentTimeMillis() - starttime;
            logger.info("执行时间："+exctime + "毫秒");
            logger.info("proceed: "+proceed);  //打印目标方法的return结果
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        return "aop的返回值";
        return null;
    }

}
