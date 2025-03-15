package org.huang.pwgtp.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.bizEnum.TravelStatusEnum;
import org.huang.pwgtp.convertor.TravelConvertor;
import org.huang.pwgtp.repository.TravelMapper;
import org.huang.pwgtp.repository.model.TravelDO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.service.model.UserDTO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class TravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelConvertor travelConvertor;


    @Autowired
    private RedissonClient redissonClient;



    public void createTravel(TravelDTO travelDTO) throws Exception{
        travelDTO.setHasRecruitedMemberList(Set.of(travelDTO.getCreatorUid()));
        TravelDO travelDO = travelConvertor.convertTravelDTOToDO(travelDTO);
        travelDO.setModifierUid(travelDO.getCreatorUid());
        travelDO.setStatus(TravelStatusEnum.DRAFT.name());
        int updateResult = travelMapper.insert(travelDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public void editTravel(TravelDTO travelDTO) throws Exception{
        if(Objects.isNull(travelDTO.getId())){
            throw new Exception("行程不存在，无法操作");
        }
        TravelDO travelDO = travelConvertor.convertTravelDTOToDO(travelDTO);
        int updateResult = travelMapper.updateById(travelDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public TravelDTO getTravelById(Long id){
        TravelDO travelDO = travelMapper.getById(id);
        return travelConvertor.convertTravelDOToDTO(travelDO);
    }


    public CommonPage<TravelDTO> listTravelByPage(Integer pageNum, Integer pageSize, String fuzzyKey) throws Exception {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("gmt_create desc");// 启用分页
        List<TravelDO> travelDOList = travelMapper.list(fuzzyKey);//普通查询
        CommonPage<TravelDO> commonPageDO = CommonPage.restPage(travelDOList);//分页对象，包含总页数、list分页等
        CommonPage<TravelDTO> commonPageDTO = new CommonPage<>();
        BeanUtils.copyProperties(commonPageDO, commonPageDTO);// 拷贝分页对象,但是list没拷贝到
        commonPageDTO.setList(travelConvertor.convertTravelDOToDTOList(commonPageDO.getList()));//list拷贝
        //  A:看到A的全部及B的已经发布的；B: 看到B的全部及A的已经发布的；
        //发布了状态就会改变，但是我们是一下全拿到全部数据
        //遍历list
       for (TravelDTO travelDTO : commonPageDTO.getList()){

             if (travelDTO.getStatus().equals(TravelStatusEnum.DRAFT.name()) && !userService.getCurrentUser().getId().equals(travelDTO.getCreatorUid())){
                 commonPageDTO.getList().remove(travelDTO);
             }

       }
        return commonPageDTO;
    }




    public void joinTravel(Long travelId, Long userId) throws Exception{
        // 获取分布式锁
        RLock lock = redissonClient.getLock("travelLock:" + travelId);
        try {
            // 尝试加锁，最多等待10秒，锁持有时间30秒
            boolean isLocked = lock.tryLock(10, 30, java.util.concurrent.TimeUnit.SECONDS);
            if (!isLocked) {
                throw new Exception("获取锁失败，请稍后重试");
            }

            TravelDTO travelDTO = this.getTravelById(travelId);
            if(Objects.isNull(travelDTO)){
                throw new Exception("行程不存在，无法操作");
            }
            if(travelDTO.getHasRecruitedMemberList().size() >= travelDTO.getPlanRecruitMemberNumber()){
                throw new Exception("行程已满员，无法操作");
            }

            // 检查用户是否已经加入行程
            if (travelDTO.getHasRecruitedMemberList().contains(userId)) {
                throw new Exception("用户已经加入该行程，无法重复加入");
            }

            travelDTO.getHasRecruitedMemberList().add(userId);

            TravelDO travelDONew = new TravelDO();
            travelDONew.setId(travelId);
            travelDONew.setHasRecruitedMemberList(JSON.toJSONString(travelDTO.getHasRecruitedMemberList()));
            int updateResult = travelMapper.updateById(travelDONew);
            if(updateResult != 1){
                throw new Exception("操作失败");
            }
        } finally {
            // 释放锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }




    public void updateTravelStatus(Long travelId, String status) throws Exception{
        TravelDO travelDO = travelMapper.getById(travelId);//先看看数据库有没有数据
        if(Objects.isNull(travelDO)){
            throw new Exception("行程不存在，无法操作");
        }
        int updateResult = travelMapper.updateStatusById(travelId, status);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }


    public void deleteTravel(Long travelId) throws Exception{
        TravelDO travelDO = travelMapper.getById(travelId);
        if(Objects.isNull(travelDO)){
            throw new Exception("行程不存在，无法操作");
        }
        int updateResult = travelMapper.deleteById(travelId);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }
    
}
