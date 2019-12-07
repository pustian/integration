package com.wotung.integration.gym.service.impl;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.gym.entity.Gym;
import com.wotung.integration.gym.mapper.GymMapper;
import com.wotung.integration.gym.service.IGymService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wotung.integration.gym.web.vo.QueryRespEntity;
import com.wotung.integration.gym.web.ResponseCode;

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
    public ResponseCode add(String name,String address,String contact_info,
                        String score,String pictureUrl,String instruction,
                       String contactname)
    {
        ResponseCode resultcode = ResponseCode.SYSTEM_ERROR;
        Boolean result = false;
        Gym gym = new Gym();
        gym.setAddress(address);
        gym.setName(name);
        gym.setContactInfo(contact_info);
        gym.setContactName(contactname);
        gym.setScore(score);
        gym.setPictureUrl(pictureUrl);
        gym.setInstruction(instruction);

        gym.setIs_Delete(0);

        Gym selected = gymService.selectOne(new EntityWrapper<Gym>(gym));

        if(selected == null)
        {
            gym.setIs_Delete(1);
            Gym selected1 = gymService.selectOne(new EntityWrapper<Gym>(gym));
            if(selected1 == null)
            {
                gym.setIs_Delete(0);
                result = gymService.insert(gym);
            }
            else
            {
                selected1.setIs_Delete(0);
                result = gymService.updateById(selected1);
            }

            resultcode = ResponseCode.OK;
        }
        else
        {
            result = true;
            resultcode = ResponseCode.ADD_ERROR;
        }

        return resultcode;
    }


    @Override
    public ResponseCode update (Gym gym)
    {
        ResponseCode resultcode = ResponseCode.SYSTEM_ERROR;
        boolean result = false;
        if(null != gym.getId())
        {
            result = gymService.updateById(gym);
        }

        if(result == true)
        {
            resultcode = ResponseCode.OK;
        }
        return resultcode;
    }

    @Override
    public  ResponseCode delete (Gym gym)
    {
        ResponseCode resultcode = ResponseCode.SYSTEM_ERROR;
        boolean result = false;
        result = gymService.deleteById(gym);

        if(result == true)
        {
            resultcode = ResponseCode.OK;
        }
        return resultcode;
    }

    @Override
    public  ResponseCode delete (Integer id)
    {
        ResponseCode responseCode = ResponseCode.SYSTEM_ERROR;
        boolean result = false;
        EntityWrapper <Gym> entityWrapper = new EntityWrapper<Gym>();
        entityWrapper.eq("id",id);
        Gym selected = gymService.selectOne(entityWrapper);
        if(selected != null)
        {
            selected.setIs_Delete(1);
            gymService.update(selected);
            responseCode = responseCode.OK;
        }
        return responseCode;
    }
    @Override
    public List<Gym> getAllGym()
    {
        EntityWrapper<Gym> wrapper = (EntityWrapper<Gym>) new EntityWrapper<Gym>().eq("Is_Delete",0);

        return gymService.selectList(wrapper);
    }

    @Override
    public QueryRespEntity getGymbyPage(Integer Pagenum, Integer PageSize, String Name)
    {
        QueryRespEntity queryRespEntity = new QueryRespEntity();
        Page <Gym> page = new Page<Gym>(Pagenum,PageSize);
        ResponseCode result = ResponseCode.SYSTEM_ERROR;
        try
        {
            Gym gym = new Gym();
            gym.setName(Name);
            EntityWrapper<Gym> wrapper = (EntityWrapper<Gym>) new EntityWrapper<Gym>().like("name", gym.getName()).eq("Is_Delete",0);

            page = gymService.selectPage(page,wrapper);

            if(page != null)
            {
                queryRespEntity.setTotalCount(page.getTotal());
                queryRespEntity.setCurrent(page.getCurrent());
                queryRespEntity.setQueries(page.getRecords());
            }
            queryRespEntity.setIsSuccess(ResponseCode.OK.message);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            queryRespEntity.setIsSuccess(ResponseCode.SYSTEM_ERROR.message);
        }

        return queryRespEntity;
    }

}
