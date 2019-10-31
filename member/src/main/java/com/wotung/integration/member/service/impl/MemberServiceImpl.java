package com.wotung.integration.member.service.impl;

import com.wotung.integration.member.entity.Member;
import com.wotung.integration.member.mapper.MemberMapper;
import com.wotung.integration.member.service.IMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员基本信息 服务实现类
 * </p>
 *
 * @author 田圃森
 * @since 2019-10-31
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
	
}
