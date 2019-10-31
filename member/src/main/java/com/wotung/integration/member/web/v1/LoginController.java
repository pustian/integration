package com.wotung.integration.member.web.v1;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.member.domain.ILoginDomain;
import com.wotung.integration.member.entity.Member;
import com.wotung.integration.member.entity.MemberPasswd;
import com.wotung.integration.member.properties.MemberLoginProperties;
import com.wotung.integration.member.service.IMemberPasswdService;
import com.wotung.integration.member.service.IMemberService;
import com.wotung.integration.member.web.vo.DefaultRespEntity;
import com.wotung.integration.member.web.vo.Response;
import com.wotung.integration.member.web.vo.login.MemberResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Api(tags = "用户注册登录")
@Controller("/v1")
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Autowired
    ILoginDomain loginDomain;

    @Autowired
    IMemberService memberService;
    @Autowired
    IMemberPasswdService memberPasswdService;

    @Autowired
    MemberLoginProperties memberLoginProperties;

    @ApiOperation(value = "注册", notes = "注册:返回字段中status 0：未激活，需要用户重新注册。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form")
    })
    @PostMapping("/register")
    @ResponseBody
    public Response<DefaultRespEntity> register(
            @RequestParam(name = "phone")String phone
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        boolean  bool = loginDomain.register(phone);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }

    @ApiOperation(value = "验证码登录", notes = "TODO 登录：手机号验证码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "validateNo", value = "手机号验证码", dataType = "String", paramType = "form")
    })
    @PostMapping("/validateLogin")
    @ResponseBody
    public Response<DefaultRespEntity> validateLogin(
            @RequestParam(name = "phone")String phone,
            @RequestParam(name = "validateNo")String validateNo
    ) {
        logger.error("需要跟前端加密为密文传输 phone={} validateNo={}", validateNo);
        // TODO
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        boolean  bool = false;
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }

    @ApiOperation(value = "（重新）设置密码", notes = "（重新）设置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "validateNo", value = "验证码", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "passwd", value = "暂时先使用明文密码", dataType = "String", paramType = "form")
    })
    @PostMapping("/setPasswd")
    @ResponseBody
    public Response<DefaultRespEntity> setPasswd(
            @RequestParam(name = "phone")String phone,
            @RequestParam(name = "validateNo")String validateNo,
            @RequestParam(name = "passwd")String passwd
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        boolean  bool = loginDomain.setPasswd(phone, validateNo, passwd);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }

    @ApiOperation(value = "更新密码", notes = "更新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "oldpasswd", value = "旧密码", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "passwd", value = "新密码", dataType = "String", paramType = "form")
    })
    @PostMapping("/retPasswd")
    @ResponseBody
    public Response<DefaultRespEntity> retPasswd(
            @RequestParam(name = "phone")String phone,
            @RequestParam(name = "oldpasswd")String oldpasswd,
            @RequestParam(name = "passwd")String passwd
    ){
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        boolean  bool = loginDomain.resetPasswd(phone, oldpasswd, passwd);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }

    @ApiOperation(value = "密码登录", notes = "登录：密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "passwd", value = "暂时先使用明文密码", dataType = "String", paramType = "form")
    })
    @PostMapping("/passwdLogin")
    @ResponseBody
    public Response<DefaultRespEntity> passwdLogin(
            @RequestParam(name = "phone")String phone,
            @RequestParam(name = "passwd")String passwd
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        boolean  bool = loginDomain.passwdLogin(phone, passwd);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);

        if(bool) {


        }


        return response;
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "用户内部Id", dataType = "String", paramType = "form"),
    })
    @PostMapping("/updateMember")
    @ResponseBody
    public Boolean updateMember() {
        return null;
    }
}
