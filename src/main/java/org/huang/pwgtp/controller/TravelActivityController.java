package org.huang.pwgtp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.convertor.TravelActivityConvertor;
import org.huang.pwgtp.service.TravelActivityService;
import org.huang.pwgtp.service.model.TravelActivityDTO;
import org.huang.pwgtp.controller.vo.TravelActivityDetailVO;
import org.huang.pwgtp.controller.vo.TravelActivitySaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("travelActivity")
@Tag(name = "travelActivityApi")
public class TravelActivityController {

    @Autowired
    private TravelActivityService travelActivityService;

    @Autowired
    private TravelActivityConvertor travelActivityConvertor;


    @Operation(summary = "新建行程活动")
    @PostMapping("/createTravelActivity")
    public CommonResult<Long> createTravelActivity(@RequestBody TravelActivitySaveVO travelActivitySaveVO) {
        TravelActivityDTO travelActivityDTO = travelActivityConvertor.convertTravelActivitySaveVOToDTO(travelActivitySaveVO);
        return CommonResult.success(travelActivityService.createTravelActivity(travelActivityDTO));
    }

    @Operation(summary = "编辑行程活动")
    @PostMapping("/editTravelActivity")
    public CommonResult<Void> editTravelActivity(@RequestBody TravelActivitySaveVO travelActivitySaveVO) {
        TravelActivityDTO travelActivityDTO = travelActivityConvertor.convertTravelActivitySaveVOToDTO(travelActivitySaveVO);
        travelActivityService.editTravelActivity(travelActivityDTO);
        return CommonResult.success(null);
    }


    @Operation(summary = "查询某行程信息")
    @GetMapping("/getTravelActivityById/{id}")
    public CommonResult<TravelActivityDetailVO> getTravelActivityById(@PathVariable Long id) {
        TravelActivityDTO travelActivityDTO = travelActivityService.getTravelActivityById(id);
        TravelActivityDetailVO travelActivityDetailVO = travelActivityConvertor.convertTravelActivityDTOToVO(travelActivityDTO);
        return CommonResult.success(travelActivityDetailVO);
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
