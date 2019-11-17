package com.wotung.integration.gym.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.gym.entity.Gym;
import com.wotung.integration.gym.mapper.GymMapper;
import com.wotung.integration.gym.service.IGymService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 场地基本信息 服务实现类
 * </p>
 *
 * @author 郑义军
 * @since 2019-11-03
 */
@Service
public class GymServiceImpl extends ServiceImpl<GymMapper, Gym> implements IGymService {

    @Autowired
    IGymService gymService;

    @Override
    public Boolean add(String name,String address,String contact_info,
                        String score,String pictureUrl,String instruction,
                       String contactname)
    {
        Boolean result = false;
        Gym gym = new Gym();
        gym.setAddress(address);
        gym.setName(name);
        gym.setContactInfo(contact_info);
        gym.setContactName(contactname);
        gym.setScore(score);
        gym.setPictureUrl(pictureUrl);
        gym.setInstruction(instruction);

        Gym selected = gymService.selectOne(new EntityWrapper<Gym>(gym));

        if(selected == null)
        {
            result = gymService.insert(gym);
        }
        else
        {
            result = true;
        }
        return result;
    }


    @Override
    public Boolean update (Gym gym)
    {
        boolean result = false;
        if(null != gym.getId())
        {
            result = gymService.updateById(gym);
        }
        return result;
    }

    @Override
    public  Boolean delete (Gym gym)
    {
        boolean result = false;
        result = gymService.deleteById(gym);
        return result;
    }

    @Override
    public List<Gym> getAllGym()
    {
        return gymService.selectList(null);
    }


}
