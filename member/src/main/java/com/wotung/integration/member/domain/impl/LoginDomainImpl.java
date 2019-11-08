package com.wotung.integration.member.domain.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.member.domain.ILoginDomain;
import com.wotung.integration.member.entity.Member;
import com.wotung.integration.member.entity.MemberPasswd;
import com.wotung.integration.member.properties.MemberLoginProperties;
import com.wotung.integration.member.service.IMemberPasswdService;
import com.wotung.integration.member.service.IMemberService;
import com.wotung.integration.member.web.ResponseCode;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    @Override
//    public Boolean register(String phone, String name) {
//        Boolean result = false;
//        Member member = new Member();
//        member.setPhone(phone);
//        Member selected = memberService.selectOne(new EntityWrapper<Member>(member));
//        if(null == selected) {
//            member.setName(name);
//            member.setGmtCreate(new Date());
//            member.setGmtModified(new Date());
//            result = memberService.insert(member);
////        } else {
////            try {
////                BeanUtils.copyProperties(member, selected);
////            } catch (IllegalAccessException e) {
////                logger.error("The phone has registered, return exception", e);
////            } catch (InvocationTargetException e) {
////                logger.error("The phone has registered, return exception", e);
////            }
//        } else {
//            result = true;
//        }
//        return result;
//    }

    @Override
    public ResponseCode register2(String phone, String name, String email, String passwd) {
        ResponseCode result = ResponseCode.SYSTEM_ERROR;
        // 1 检查手机号是否已注册。
        Member selected = memberService.selectOne(new EntityWrapper<Member>().eq("phone", phone));
        if(null == selected ) {
            Member member = new Member();
            member.setPhone(phone);
            member.setName(name);
            member.setEmail(email);
            member.setGmtCreate(new Date());
            member.setGmtModified(new Date());
            boolean memberInsert = memberService.insert(member);

            selected = memberService.selectOne(new EntityWrapper<Member>().eq("phone", phone));
            MemberPasswd memberPasswd = new MemberPasswd();
            memberPasswd.setMemberId(selected.getId());
            memberPasswd.setPasswd(passwd);
            memberPasswd.setStatus(0);
            memberPasswd.setTimes(0);
            memberPasswd.setLockTime(null);
            memberPasswd.setSalt(null);
            memberPasswd.setGmtCreate(new Date());
            memberPasswd.setGmtModified(new Date());
            boolean memberPasswdInsert = memberPasswdService.insert(memberPasswd);
            if(memberInsert && memberPasswdInsert) {
                result = ResponseCode.OK;
            }
        } else {
            result = ResponseCode.REGISTER_ERROR;
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
    public ResponseCode passwdLogin(String phone, String passwd) {
        logger.error("需要跟前端加密为密文传输 phone={} passwd={}", phone, passwd);
        // REDIS IP黑名单防止 刷密码
        // TODO
        ResponseCode result = ResponseCode.SYSTEM_ERROR;
        Member selected = memberService.selectOne(new EntityWrapper<Member>().eq("phone", phone));
        if(null != selected ) {
            MemberPasswd clause = new MemberPasswd();
            clause.setMemberId(selected.getId());
            MemberPasswd memberPasswd = memberPasswdService.selectOne(new EntityWrapper<MemberPasswd>(clause));

            // 正常 或是 锁定状态，间隔时间已到达，重新登陆
            if(0 == memberPasswd.getStatus() ) {
                if (passwd.equals(memberPasswd.getPasswd())) {
                    result = ResponseCode.OK;
                } else {
                    result = ResponseCode.LOGIN_FAILED;
                }
            }

//                } else {
//                    // 超过5次锁定，锁定后
//                    int count = memberPasswd.getTimes();
//                    if (count < memberLoginProperties.getAllowedErrorTime() ) {
//                        memberPasswd.setTimes(++count);
//                    } else {
//                        memberPasswd.setLockTime(new Date());
//                        memberPasswd.setStatus(1);
//                    }
//                    memberPasswd.setGmtModified(new Date());
//                    memberPasswdService.updateById(memberPasswd);
//                }
//            } else {
//                if(1 == memberPasswd.getStatus() && null != memberPasswd.getLockTime() ) {
//                    Date allowLoginTime = DateUtils.addMinutes(memberPasswd.getLockTime(), memberLoginProperties.getLockInterval());
//                    if(allowLoginTime.getTime() > System.currentTimeMillis()  ) {
//                        if(passwd.equals(memberPasswd.getPasswd() ) ) {
//                            result = true;
//                            memberPasswd.setStatus(0);
//                            memberPasswd.setLockTime(null);
//                        } else {
//                            memberPasswd.setLockTime(new Date());
//                            memberPasswd.setStatus(1);
//                        }
//                        memberPasswd.setGmtModified(new Date());
//                        memberPasswdService.updateById(memberPasswd);
//                    }
//                }
//            }
        }
        return result;
    }

    public ResponseCode updateMember(Member member) {
        ResponseCode result = ResponseCode.SYSTEM_ERROR;
        if(null == member.getId() && null != member.getPhone() ) {
            Member selected = memberService.selectOne(new EntityWrapper<Member>().eq("phone", member.getPhone()));
            member.setId(selected.getId());
        }
        boolean bool = memberService.updateById(member);
        if(bool) {
            result = ResponseCode.OK;
        }
        return result;
    }
}
