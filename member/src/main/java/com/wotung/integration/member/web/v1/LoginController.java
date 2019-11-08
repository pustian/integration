package com.wotung.integration.member.web.v1;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.member.domain.ILoginDomain;
import com.wotung.integration.member.entity.Member;
import com.wotung.integration.member.properties.MemberLoginProperties;
import com.wotung.integration.member.service.IMemberPasswdService;
import com.wotung.integration.member.service.IMemberService;
import com.wotung.integration.member.uitl.JWTHelper;
import com.wotung.integration.member.web.ResponseCode;
import com.wotung.integration.member.web.vo.DefaultRespEntity;
import com.wotung.integration.member.web.vo.Request;
import com.wotung.integration.member.web.vo.Response;
import com.wotung.integration.member.web.vo.header.RespHeader;
import com.wotung.integration.member.web.vo.login.MemberReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@Api(tags = "用户注册登录")
@RestController
@RequestMapping("/api/v1")
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

//    @ApiOperation(value = "注册", notes = "注册:返回字段中status 0：未激活，需要用户重新注册。")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String", paramType = "form")
//    })
//    @PostMapping("/register")
//    @ResponseBody
//    public Response<DefaultRespEntity> register(
//            @RequestParam(name = "phone")String phone,
//            @RequestParam(name = "name")String name
//    ) {
//        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
//        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
//        boolean  bool = loginDomain.register(phone, name);
//        defaultRespEntity.setIsSuccess(""+bool);
//        response.setRespBody(defaultRespEntity);
//        return response;
//    }

    @ApiOperation(value = "注册设置密码", notes = "注册:返回字段中status 0：未激活，需要用户重新注册。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "passwd", value = "密码", dataType = "String", paramType = "form"),
    })
    @PostMapping("/register2")
    @ResponseBody
    public Response<DefaultRespEntity> register2(
            @RequestParam(name = "phone")String phone,
            @RequestParam(name = "name")String name,
            @RequestParam(name = "email")String email,
            @RequestParam(name = "passwd")String passwd
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        ResponseCode register2Reult = loginDomain.register2(phone, name, email, passwd);
        defaultRespEntity.setIsSuccess("" + (ResponseCode.OK == register2Reult));
        response.setRespBody(defaultRespEntity);
        RespHeader respHeader = new RespHeader();
        respHeader.setRespCode(register2Reult.code);
        respHeader.setRespMessage(register2Reult.message);
        response.setRespHeader(respHeader);
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
        ResponseCode  responseCode= loginDomain.passwdLogin(phone, passwd);

        RespHeader respHeader = new RespHeader();
        if(responseCode == ResponseCode.OK) {
            Member member = memberService.selectOne(new EntityWrapper<Member>().eq("phone", phone));
            String jwt = JWTHelper.createJWT(member.getId().toString(), phone);
            respHeader.setToken(jwt);
            defaultRespEntity.setIsSuccess("true");
        }
        response.setRespBody(defaultRespEntity);
        response.setRespHeader(respHeader);
        return response;
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "memberId", value = "用户id", dataType = "Integer", paramType = "form"),
//            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "sex", value = "性别:0-男性，1-女性", dataType = "Integer", paramType = "form"),
//            @ApiImplicitParam(name = "idType", value = "证件类别：00-身份证,01-护照", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "idNumber", value = "证件号码", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "linkmanName", value = "联系人姓名", dataType = "String", paramType = "form"),
//            @ApiImplicitParam(name = "linkmanPhone", value = "联系人手机号", dataType = "String", paramType = "form"),
//    })
    @PostMapping("/updateMember")
    @ResponseBody
    public  Response<DefaultRespEntity> updateMember(
            @RequestBody Request<MemberReq> reqMember
            ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

        MemberReq memberReq = reqMember.getReqBody();
        Member member = new Member();
        try {
            BeanUtils.copyProperties(member, memberReq);
        } catch (IllegalAccessException |InvocationTargetException e) {
            logger.error("", e);
        }
        ResponseCode bool = loginDomain.updateMember(member);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping("/delete")
    @ResponseBody
    public  Response<DefaultRespEntity> deleteMember(
            @RequestBody Request<MemberReq> reqMember
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

        MemberReq memberReq = reqMember.getReqBody();
        Member member = new Member();
        member.setId(memberReq.getId() );
        member.setIsDeleted(1);
        boolean bool = memberService.deleteById(member);

        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }
}

