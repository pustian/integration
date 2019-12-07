package com.wotung.integration.gym.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wotung.integration.gym.entity.Gym;
import com.baomidou.mybatisplus.service.IService;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

import com.wotung.integration.gym.web.ResponseCode;
import com.wotung.integration.gym.web.vo.QueryRespEntity;

/**
 * <p>
 * 场地基本信息 服务类
 * </p>
 *
 * @author 郑义军
 * @since 2019-11-03
 */
public interface IGymService extends IService<Gym> {
    ResponseCode add(String name, String address, String contact_info,
                     String score, String pictureUrl, String instruction,
                     String contactname);

    ResponseCode update (Gym gym);

    ResponseCode delete (Gym gym);

    public  ResponseCode delete (Integer id);

    List<Gym>  getAllGym();

    QueryRespEntity getGymbyPage(Integer Pagenum, Integer PageSize, String Name);
}
