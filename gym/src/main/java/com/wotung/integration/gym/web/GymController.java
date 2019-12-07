package com.wotung.integration.gym.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.wotung.integration.gym.entity.Gym;
import com.wotung.integration.gym.entity.TestInstructors;
import com.wotung.integration.gym.service.impl.GymServiceImpl;
import com.wotung.integration.gym.web.vo.ResposePageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wotung.integration.gym.web.vo.DefaultRespEntity;
import com.wotung.integration.gym.web.vo.QueryRespEntity;
import com.wotung.integration.gym.web.vo.Request;
import com.wotung.integration.gym.web.vo.Response;
import com.wotung.integration.gym.web.vo.header.RespHeader;
import com.wotung.integration.gym.web.vo.gym.GymReq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 场地基本信息 前端控制器
 * </p>
 *
 * @author 郑义军
 * @since 2019-11-03
 */
@Controller
@RestController
@RequestMapping("/api/v1/gym")
public class GymController {

    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Autowired
    GymServiceImpl gymServiceImp;


    @PostMapping("/add")
    @ResponseBody
    public Response<DefaultRespEntity> add(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "address")String address,
            @RequestParam(name = "contactInfo")String contactinfo,
            @RequestParam(name = "score")String score,
            @RequestParam(name = "pictureUrl")String pictureUrl,
            @RequestParam(name = "instruction")String instruction,
            @RequestParam(name = "contactName")String contactname
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        ResponseCode  bool = gymServiceImp.add(name,address,contactinfo,score,pictureUrl,instruction,contactname);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);

        RespHeader respHeader = new RespHeader();
        respHeader.setRespCode(bool.code);
        respHeader.setRespMessage(bool.message);
        response.setRespHeader(respHeader);

        return response;
    }


    @PostMapping("/updateGym")
    @ResponseBody
    public  Response<DefaultRespEntity> updateGym(
            @RequestBody Gym reqGym
    )
    {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

      // GymReq gymReq = reqGym;
        Gym gym = reqGym;

        ResponseCode bool = gymServiceImp.update(gym);

        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);

        RespHeader respHeader = new RespHeader();
        respHeader.setRespCode(bool.code);
        respHeader.setRespMessage(bool.message);
        response.setRespHeader(respHeader);
        return response;
    }


    @PostMapping("/delete")
    @ResponseBody
    public  Response<DefaultRespEntity> deleteGym(
            @RequestParam(name = "gymId")Integer reqGymId
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

       // GymReq gymReq = reqGym.getReqBody();
        Gym gym = new Gym();
        gym.setId(reqGymId);
        //member.setIsDeleted(1);
        ResponseCode bool = gymServiceImp.delete(gym);

        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);

        RespHeader respHeader = new RespHeader();
        respHeader.setRespCode(bool.code);
        respHeader.setRespMessage(bool.message);
        response.setRespHeader(respHeader);
        return response;
    }



    @PostMapping("/deleteBylogic")
    @ResponseBody
    public  Response<DefaultRespEntity> deleteGymByLogic(
            @RequestParam(name = "gymId")Integer reqGymId
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

        ResponseCode bool = gymServiceImp.delete(reqGymId);

        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);

        RespHeader respHeader = new RespHeader();
        respHeader.setRespCode(bool.code);
        respHeader.setRespMessage(bool.message);
        response.setRespHeader(respHeader);
        return response;
    }


    @GetMapping("/getAllGym")
    public List<Gym>  getAllGym(){

        List ret = new ArrayList();
        ret =gymServiceImp.getAllGym();
        return ret;
    }




    @PostMapping("/getGymByPage")
    public Response<QueryRespEntity>  getGymByPage(
                                @RequestParam(name = "pagenum")Integer Pagenum,
                                @RequestParam(name = "pagesize")Integer PageSize,
                                @RequestParam(name = "name")String Name
                                )
    {
        Response<QueryRespEntity> response = new Response<QueryRespEntity>();

        QueryRespEntity queryRespEntity  = gymServiceImp.getGymbyPage(Pagenum,PageSize,Name);


        response.setRespBody(queryRespEntity);

        RespHeader respHeader = new RespHeader();
        respHeader.setRespCode(ResponseCode.OK.code);
        respHeader.setRespMessage(ResponseCode.OK.message);
        response.setRespHeader(respHeader);
        return response;

    }


    }
