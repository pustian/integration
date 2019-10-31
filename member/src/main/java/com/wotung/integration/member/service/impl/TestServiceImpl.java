package com.wotung.integration.member.service.impl;

import com.wotung.integration.member.entity.Test;
import com.wotung.integration.member.mapper.TestMapper;
import com.wotung.integration.member.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 田圃森
 * @since 2019-10-31
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
	
}
