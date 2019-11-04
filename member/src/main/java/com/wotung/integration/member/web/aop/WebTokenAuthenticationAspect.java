package com.wotung.integration.member.web.aop;

import com.wotung.integration.member.Constants;
import com.wotung.integration.member.uitl.JWTHelper;
import com.wotung.integration.member.web.ResponseCode;
import com.wotung.integration.member.web.vo.Request;
import com.wotung.integration.member.web.vo.Response;
import com.wotung.integration.member.web.vo.entity.ReqEntity;
import com.wotung.integration.member.web.vo.header.ReqHeader;
import com.wotung.integration.member.web.vo.header.RespHeader;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

@Profile("sit")
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
//        logger.info("methodName: "+methodName);  // 输出目标方法名
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes(); //获取目标方法参数类型
        Object[] args = pjp.getArgs();  //获取目标方法的入参
        if(args[0] instanceof Request) {
            Request request = (Request)args[0];
            if(request != null) {
                ReqHeader reqHeader = request.getReqHeader();
                if( reqHeader != null) {
                    if(StringUtils.isNotBlank(reqHeader.getAccessToken())) {
                        Response response = new Response();
                        RespHeader respHeader = new RespHeader();;
                        try {
                            // JWTHelper 解析
                            Claims claims = JWTHelper.parseJWT(reqHeader.getAccessToken() );
                            if(null != claims.getId() && StringUtils.isNotBlank(claims.getSubject())
                                    && Constants.SERVICE_NAME.equals(claims.getIssuer()) ) {
                                Object proceed = pjp.proceed();
                                response = (Response)proceed;
                                if(null != response.getRespHeader() ) {
                                    respHeader =  response.getRespHeader();
                                }
                                respHeader.setRespCode(ResponseCode.OK.code);
                                respHeader.setRespMessage(ResponseCode.OK.message);
                            }
                        } catch (ExpiredJwtException | UnsupportedJwtException |MalformedJwtException| SignatureException| IllegalArgumentException ex) {
                            logger.error("", ex);
                            respHeader.setRespCode(ResponseCode.TOKEN_ERROR.code);
                            respHeader.setRespMessage(ResponseCode.TOKEN_ERROR.message);
                        } catch (Throwable throwable) {
                            logger.error("", throwable);
                            respHeader.setRespCode(ResponseCode.SYSTEM_ERROR.code);
                            respHeader.setRespMessage(ResponseCode.SYSTEM_ERROR.message);
                        } finally {
                            respHeader.setReqId(reqHeader.getReqId());
                            respHeader.setSessionId(reqHeader.getSessionId());
                            response.setRespHeader(respHeader);
                            return response;
                        }
                    };
                }
            }
        }
        return null;
    }

}
