package com.wotung.integration.member.web.logincontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class _1_LoginControllerRegisterTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    public void register01() throws Exception{
        String phone = "12345678";
        String DELETE_SQL = "delete from member where phone=?";
        int i = jdbcTemplate.update(DELETE_SQL, phone);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/register");
        request.param("phone",phone);
        MvcResult result = mvc.perform(request)
                .andExpect(status().isOk() )
                .andReturn();
         String responseString = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSON.parseObject(responseString);
        Assert.assertEquals(jsonObject.getJSONObject("respBody").getBoolean("isSuccess"), true);

        String QUERY_SQL = "select * from member where phone=?";
        List list = jdbcTemplate.queryForList(QUERY_SQL, phone);
        Assert.assertTrue(list.size() == 1);
        System.out.println(responseString);
    }

    // 重复注册
//    @Test
//    public void register02() throws Exception{
//        String phone = "123456789";
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/register");
//        request.param("phone",phone);
//        MvcResult result = mvc.perform(request)
//                .andExpect(status().isOk() )
//                .andReturn();
//        //
//        phone = "123456789";
//        request = MockMvcRequestBuilders.post("/v1/register");
//        request.param("phone",phone);
//        result = mvc.perform(request)
//                .andExpect(status().isOk() )
//                .andReturn();
//
//        String responseString = result.getResponse().getContentAsString();
//        JSONObject jsonObject = JSON.parseObject(responseString);
//        Assert.assertEquals(jsonObject.getJSONObject("respBody").getBoolean("isSuccess"), true);
//        System.out.println(responseString);
//    }

    // 删除后再注册


}
