package com.wotung.integration.member.domain.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.member.domain.ILoginDomain;
import com.wotung.integration.member.entity.Member;
import com.wotung.integration.member.entity.MemberPasswd;
import com.wotung.integration.member.properties.MemberLoginProperties;
import com.wotung.integration.member.service.IMemberPasswdService;
import com.wotung.integration.member.service.IMemberService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@Service
public class LoginDomainImpl implements ILoginDomain {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Autowired
    IMemberService memberService;

    @Autowired
    IMemberPasswdService memberPasswdService;

    @Autowired
    MemberLoginProperties memberLoginProperties;

    @Override
    public Boolean register(String phone) {
        Boolean result = false;
        Member member = new Member();
        member.setPhone(phone);
        Member selected = memberService.selectOne(new EntityWrapper<Member>(member));
        if(null == selected) {
            member.setGmtCreate(new Date());
            member.setGmtModified(new Date());
            result = memberService.insert(member);
//        } else {
//            try {
//                BeanUtils.copyProperties(member, selected);
//            } catch (IllegalAccessException e) {
//                logger.error("The phone has registered, return exception", e);
//            } catch (InvocationTargetException e) {
//                logger.error("The phone has registered, return exception", e);
//            }
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public Boolean setPasswd(String phone, String validateNo, String passwd) {
        // 1. 确认已注册用户
        logger.error("需要跟前端加密为密文传输 phone={} passwd={}", phone, passwd);
        Member member = new Member();
        member.setPhone(phone);
        Member selected = memberService.selectOne(new EntityWrapper<Member>(member));

        boolean result = false;
        if(null != selected ) {
            // 2, 检查验证码
            // TODO
            //validateNo

            // 3, 重置密码
            MemberPasswd clause = new MemberPasswd();
            clause.setMemberId(selected.getId());
            MemberPasswd memberPasswd = memberPasswdService.selectOne(new EntityWrapper<MemberPasswd>(clause));

            // 已存在重置密码
            if(null != memberPasswd ) {
                memberPasswd.setPasswd(passwd);
                memberPasswd.setStatus(0);
                memberPasswd.setTimes(0);
                memberPasswd.setLockTime(null);
                memberPasswd.setSalt(null);
                memberPasswd.setGmtModified(new Date());
                result = memberPasswdService.updateById(memberPasswd);
            } else {
                memberPasswd = new MemberPasswd();
                memberPasswd.setMemberId(selected.getId());
                memberPasswd.setPasswd(passwd);
                memberPasswd.setStatus(0);
                memberPasswd.setTimes(0);
                memberPasswd.setLockTime(null);
                memberPasswd.setSalt(null);
                memberPasswd.setGmtCreate(new Date());
                memberPasswd.setGmtModified(new Date());
                result = memberPasswdService.insert(memberPasswd);
            }
        }
        return result;
    }

    @Override
    public Boolean resetPasswd(String phone, String oldPasswd, String newPasswd) {
        // 1. 确认已注册用户
        logger.error("需要跟前端加密为密文传输 phone={} oldPasswd={} passwd={}", phone, oldPasswd, newPasswd);
        Member member = new Member();
        member.setPhone(phone);
        Member selected = memberService.selectOne(new EntityWrapper<Member>(member));

        boolean result = false;
        if(null != selected ) {
            // 1, 检查旧密码
            MemberPasswd clause = new MemberPasswd();
            clause.setMemberId(selected.getId());
            MemberPasswd memberPasswd = memberPasswdService.selectOne(new EntityWrapper<MemberPasswd>(clause));
            if(null != memberPasswd && oldPasswd.equals(memberPasswd.getPasswd() ) ) {
                // 2, 更新密码
                memberPasswd.setPasswd(newPasswd);
                memberPasswd.setStatus(0);
                memberPasswd.setTimes(0);
                memberPasswd.setLockTime(null);
                memberPasswd.setSalt(null);
                memberPasswd.setGmtModified(new Date());
                result = memberPasswdService.updateById(memberPasswd);
            }
        }
        return result;


    }

    @Override
    public Boolean passwdLogin(String phone, String passwd) {
        logger.error("需要跟前端加密为密文传输 phone={} passwd={}", phone, passwd);
        // REDIS IP黑名单防止 刷密码
        // TODO

        Member member = new Member();
        member.setPhone(phone);
        Member selected = memberService.selectOne(new EntityWrapper<Member>(member));
        boolean result = false;
        if(null != selected ) {
            MemberPasswd clause = new MemberPasswd();
            clause.setMemberId(selected.getId());
            MemberPasswd memberPasswd = memberPasswdService.selectOne(new EntityWrapper<MemberPasswd>(clause));

            // 正常 或是 锁定状态，间隔时间已到达，重新登陆
            if(0 == memberPasswd.getStatus()|| ( 1 == memberPasswd.getStatus() && DateUtils.addMinutes(
                    memberPasswd.getLockTime(), memberLoginProperties.getLockInterval()).getTime() > System.currentTimeMillis() ) ) {
                if(passwd.equals(memberPasswd.getPasswd() ) ) {
                    result = true;
                } else {
                    // 超过5次锁定，锁定后
                    int count = memberPasswd.getTimes();
                    if (count < memberLoginProperties.getAllowedErrorTime() ) {
                        memberPasswd.setTimes(++count);
                    } else {
                        memberPasswd.setLockTime(new Date());
                        memberPasswd.setStatus(1);
                    }
                    memberPasswd.setGmtModified(new Date());
                    memberPasswdService.updateById(memberPasswd);
                }
            }
        }
        return result;
    }

    public Boolean updateMember(Member member) {
        boolean result = false;
        if(null == member.getId() ) {
            result = memberService.updateById(member);
        }
        return result;
    }
}
