package org.huang.pwgtp.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.common.bizEnum.TravelActivityStatusEnum;
import org.huang.pwgtp.convertor.TravelActivityConvertor;
import org.huang.pwgtp.service.TravelActivityService;
import org.huang.pwgtp.service.model.TravelActivityDTO;
import org.huang.pwgtp.controller.vo.TravelActivityDetailVO;
import org.huang.pwgtp.controller.vo.TravelActivitySaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("travelActivity")
@Tag(name = "travelActivityApi")
@Validated
public class TravelActivityController {

    @Autowired
    private TravelActivityService travelActivityService;

    @Autowired
    private TravelActivityConvertor travelActivityConvertor;


    @Operation(summary = "新建行程活动")
    @PostMapping("/createTravelActivity")
    public CommonResult<Long> createTravelActivity(@RequestBody @NotNull TravelActivitySaveVO travelActivitySaveVO) {
        try {
            //todo 获取登录态 验证+ 取id
            log.info("TravelActivityController.createTravelActivity start, travelActivitySaveVO: {}", JSON.toJSONString(travelActivitySaveVO));
            TravelActivityDTO travelActivityDTO = travelActivityConvertor.convertTravelActivitySaveVOToDTO(travelActivitySaveVO);
            return CommonResult.success(travelActivityService.createTravelActivity(travelActivityDTO));
        } catch (Exception e) {
            log.error("TravelActivityController.createTravelActivity error, travelActivitySaveVO: {}", JSON.toJSONString(travelActivitySaveVO), e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "编辑行程活动")
    @PostMapping("/editTravelActivity")
    public CommonResult<Void> editTravelActivity(@RequestBody @NotNull TravelActivitySaveVO travelActivitySaveVO) {
        try {
            //todo 获取登录态 验证+ 取id
            log.info("TravelActivityController.editTravelActivity start, travelActivitySaveVO: {}", JSON.toJSONString(travelActivitySaveVO));
            TravelActivityDTO travelActivityDTO = travelActivityConvertor.convertTravelActivitySaveVOToDTO(travelActivitySaveVO);
            travelActivityService.editTravelActivity(travelActivityDTO);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelActivityController.editTravelActivity error, travelActivitySaveVO: {}", JSON.toJSONString(travelActivitySaveVO), e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "发布行程活动")
    @PostMapping("/publishTravelActivity")
    public CommonResult<Void> publishTravelActivity(@RequestParam @NotNull Long travelActivityId) {
        try {
            log.info("TravelActivityController.publishTravelActivity start, travelActivityId: {}", travelActivityId);
            travelActivityService.updateTravelActivityStatus(travelActivityId, TravelActivityStatusEnum.PUBLISHED.name());
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelActivityController.publishTravelActivity error, travelActivityId: {}", travelActivityId, e);
            return CommonResult.failed(e.getMessage());
        }

    }

    @Operation(summary = "下线行程活动")
    @PostMapping("/offlineTravelActivity")
    public CommonResult<Void> offlineTravelActivity(@RequestParam @NotNull Long travelActivityId) {
        try {
            log.info("TravelActivityController.offlineTravelActivity start, travelActivityId: {}", travelActivityId);
            travelActivityService.updateTravelActivityStatus(travelActivityId, TravelActivityStatusEnum.DRAFT.name());
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelActivityController.offlineTravelActivity error, travelActivityId: {}", travelActivityId, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Operation(summary = "查询某行程信息")
    @GetMapping("/getTravelActivityById/{id}")
    public CommonResult<TravelActivityDetailVO> getTravelActivityById(@PathVariable  Long id) {
        try {
            log.info("TravelActivityController.getTravelActivityById start, travelActivityId: {}", id);
            TravelActivityDTO travelActivityDTO = travelActivityService.getTravelActivityById(id);
            TravelActivityDetailVO travelActivityDetailVO = travelActivityConvertor.convertTravelActivityDTOToVO(travelActivityDTO);
            return CommonResult.success(travelActivityDetailVO);
        } catch (Exception e) {
            log.error("TravelActivityController.getTravelActivityById error, travelActivityId: {}", id, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "加入行程活动")
    @PostMapping("/joinTravelActivity")
    public CommonResult<Void> joinTravelActivity(@RequestParam @NotNull Long travelActivityId) {
        try {
            log.info("TravelActivityController.joinTravelActivity start, travelActivityId: {}", travelActivityId);
            travelActivityService.joinTravelActivity(travelActivityId,null);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("TravelActivityController.joinTravelActivity error, travelActivityId: {}", travelActivityId, e);
            return CommonResult.failed(e.getMessage());
        }
    }




    @Operation(summary = "列表查询行程信息")
    @GetMapping("/listTravelActivity")
    public CommonResult<CommonPage<TravelActivityDetailVO>> listTravelActivity(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "fuzzyKey", defaultValue = "") Integer fuzzyKey) {

        TravelActivityDetailVO travelActivityDetailVO = new TravelActivityDetailVO();
        travelActivityDetailVO.setName("一起去杭州");

        return CommonResult.success(CommonPage.restPage(null));
    }


}
