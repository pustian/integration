package com.wotung.integration.gym.service;

import com.wotung.integration.gym.entity.Gym;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 场地基本信息 服务类
 * </p>
 *
 * @author 郑义军
 * @since 2019-11-03
 */
public interface IGymService extends IService<Gym> {
    Boolean add(String name,String address,String contact_info,
                String score,String pictureUrl,String instruction,
                String contactname);

    Boolean update (Gym gym);

    Boolean delete (Gym gym);

    List<Gym>  getAllGym();


}
