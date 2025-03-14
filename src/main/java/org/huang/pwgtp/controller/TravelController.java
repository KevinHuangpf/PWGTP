package org.huang.pwgtp.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.common.bizEnum.TravelStatusEnum;
import org.huang.pwgtp.convertor.TravelConvertor;
import org.huang.pwgtp.service.AIService;
import org.huang.pwgtp.service.TravelService;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.controller.vo.TravelDetailVO;
import org.huang.pwgtp.controller.vo.TravelSaveVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("travel")
@Tag(name = "travelApi")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelConvertor travelConvertor;


    @Autowired
    private AIService aiService;

    @Operation(summary = "新建出行")
    @PostMapping("/create")
    public CommonResult<Void> create(@RequestBody TravelSaveVO travelSaveVO) {
        try {
            log.info("TravelController.create start, travelSaveVO: {}", JSON.toJSONString(travelSaveVO));
            TravelDTO travelDTO = travelConvertor.convertTravelSaveVOToDTO(travelSaveVO);
            travelDTO.setCreatorUid(userService.getCurrentUser().getId());
            travelService.createTravel(travelDTO);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelController.create error, travelSaveVO: {}", JSON.toJSONString(travelSaveVO), e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "编辑行程")
    @PostMapping("/edit")
    public CommonResult<Void> edit(@RequestBody TravelSaveVO travelSaveVO) {
        try {
            log.info("TravelController.edit start, travelSaveVO: {}", JSON.toJSONString(travelSaveVO));
            if(travelSaveVO.getId() == null){
                return CommonResult.validateFailed("参数有误");
            }
            TravelDTO travelDTO = travelConvertor.convertTravelSaveVOToDTO(travelSaveVO);
            travelDTO.setCreatorUid(userService.getCurrentUser().getId());
            travelService.editTravel(travelDTO);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelController.edit error, travelSaveVO: {}", JSON.toJSONString(travelSaveVO), e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "发布行程")
    @PostMapping("/publish")
    public CommonResult<Void> publish(@RequestParam Long travelId) {
        try {
            if(travelId == null){
                return CommonResult.validateFailed("参数有误");
            }
            log.info("TravelController.publish start, travelId: {}", travelId);
            travelService.updateTravelStatus(travelId, TravelStatusEnum.PUBLISHED.name());
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelController.publish error, travelId: {}", travelId, e);
            return CommonResult.failed(e.getMessage());
        }

    }

    @Operation(summary = "下线行程")
    @PostMapping("/offline")
    public CommonResult<Void> offline(@RequestParam @NotNull Long travelId) {
        try {
            log.info("TravelController.offline start, travelId: {}", travelId);
            travelService.updateTravelStatus(travelId, TravelStatusEnum.ENDED.name());
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelController.offline error, travelId: {}", travelId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "删除行程")
    @PostMapping("/delete")
    public CommonResult<Void> delete(@RequestParam @NotNull Long travelId) {
        try {
            log.info("TravelController.delete start, travelId: {}", travelId);
            travelService.deleteTravel(travelId);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelController.delete error, travelId: {}", travelId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "查询某行程信息")
    @GetMapping("/getByTravelId/{travelId}")
    public CommonResult<TravelDetailVO> getByTravelId(@PathVariable Long travelId) {
        try {
            log.info("TravelController.getByTravelId start, travelId: {}", travelId);
            TravelDTO travelDTO = travelService.getTravelById(travelId);
            TravelDetailVO travelDetailVO = travelConvertor.convertTravelDTOToVO(travelDTO);
            return CommonResult.success(travelDetailVO);
        } catch (Exception e) {
            log.error("TravelController.getByTravelId error, travelId: {}", travelId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "加入行程")
    @PostMapping("/join")
    public CommonResult<Void> join(@RequestParam @NotNull Long travelId) {
        try {
            log.info("TravelController.join start, travelId: {}", travelId);
            travelService.joinTravel(travelId, userService.getCurrentUser().getId());
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelController.join error, travelId: {}", travelId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "列表查询行程信息")
    @GetMapping("/list")
    public CommonResult<CommonPage<TravelDetailVO>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String fuzzyKey) {
        try {
            log.info("TravelController.list start, pageNum: {}, pageSize: {}, fuzzyKey: {}", pageNum, pageSize, fuzzyKey);
            CommonPage<TravelDTO> commonPageDTO = travelService.listTravelByPage(pageNum, pageSize, fuzzyKey);//分页对象包含总页数、list
            CommonPage<TravelDetailVO> commonPageVO = new CommonPage<>();
            BeanUtils.copyProperties(commonPageDTO, commonPageVO);//不同层数据的转换
            commonPageVO.setList(travelConvertor.convertTravelDTOToVOList(commonPageDTO.getList()));//集合的转换



            return CommonResult.success(commonPageVO);
        } catch (Exception e) {
            log.error("TravelController.list error, pageNum: {}, pageSize: {}, fuzzyKey: {}", pageNum, pageSize, fuzzyKey, e);
            return CommonResult.failed(e.getMessage());
        }

    }


    @Operation(summary = "ai获取旅行攻略")
    @PostMapping("/askForTravelPlan")
    public CommonResult<String> askForTravelPlan(@RequestParam @NotNull String userAskContent) {
        try {
            log.info("TravelController.askForTravelPlan start, userAskContent: {}", userAskContent);
            return CommonResult.success(aiService.askForTravelPlan(userAskContent));
        } catch (Exception e) {
            log.error("TravelController.askForTravelPlan error, userAskContent: {}", userAskContent, e);
            return CommonResult.failed(e.getMessage());
        }
    }


}
