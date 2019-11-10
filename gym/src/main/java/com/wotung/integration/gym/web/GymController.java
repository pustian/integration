package com.wotung.integration.gym.web;

import com.wotung.integration.gym.entity.Gym;
import com.wotung.integration.gym.entity.TestInstructors;
import com.wotung.integration.gym.service.impl.GymServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wotung.integration.gym.web.vo.DefaultRespEntity;
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
@RequestMapping("/gym")
public class GymController {

    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Autowired
    GymServiceImpl gymServiceImp;


    @PostMapping("/add")
    @ResponseBody
    public Response<DefaultRespEntity> add(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "address")String address,
            @RequestParam(name = "contactinfo")String contactinfo
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();
        boolean  bool = gymServiceImp.add(name,address,contactinfo);
        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }


    @PostMapping("/updateGym")
    @ResponseBody
    public  Response<DefaultRespEntity> updateGym(
            @RequestBody Request<GymReq> reqGym
    )
    {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

       GymReq gymReq = reqGym.getReqBody();
        Gym gym = new Gym();
        try {
            BeanUtils.copyProperties(gym, gymReq);
        } catch (IllegalAccessException e) {
            logger.error("", e);
        } catch (InvocationTargetException e) {
            logger.error("", e);
        }
        boolean bool = gymServiceImp.update(gym);

        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }


    @PostMapping("/delete")
    @ResponseBody
    public  Response<DefaultRespEntity> deleteGym(
            @RequestBody Request<GymReq> reqGym
    ) {
        Response<DefaultRespEntity> response = new Response<DefaultRespEntity>();
        DefaultRespEntity defaultRespEntity = new DefaultRespEntity();

        GymReq gymReq = reqGym.getReqBody();
        Gym gym = new Gym();
        gym.setId(gymReq.getId() );
        //member.setIsDeleted(1);
        boolean bool = gymServiceImp.deleteById(gym);

        defaultRespEntity.setIsSuccess(""+bool);
        response.setRespBody(defaultRespEntity);
        return response;
    }


    @GetMapping("/getAllGym")
    public List<Gym>  getAllGym(){

        List ret = new ArrayList();
        ret =gymServiceImp.getAllGym();
        return ret;
    }

    @GetMapping("/getAllInstructors")
    public List<TestInstructors> getAllInstructors()
    {
        List Ret = new ArrayList();
        TestInstructors instructor = new TestInstructors();

        Ret.add(instructor);
        return Ret;
    }
}
