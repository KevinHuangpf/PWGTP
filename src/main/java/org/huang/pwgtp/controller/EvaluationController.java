package org.huang.pwgtp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.controller.vo.EvaluationVO;
import org.huang.pwgtp.controller.vo.FavoriteVO;
import org.huang.pwgtp.convertor.EvaluationConvertor;
import org.huang.pwgtp.service.EvaluationService;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.EvaluationDTO;
import org.huang.pwgtp.service.model.FavoriteDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("evaluation")
@Tag(name = "evaluationApi")
@Validated
public class EvaluationController {


    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluationConvertor evaluationConvertor;


    @Operation(summary = "添加行程评价")
    @PostMapping("/add")
    public CommonResult<Void> add(@RequestParam @NotNull Long travelId, @RequestParam @NotNull String evaluationContent) {
        try {
            log.info("EvaluationController.addEvaluation start, travelId: {}, evaluationContent: {}", travelId, evaluationContent);
            evaluationService.addEvaluation(userService.getCurrentUser().getId(), travelId, evaluationContent);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("EvaluationController.addEvaluation error, travelId: {}, evaluationContent: {}", travelId, evaluationContent, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "删除行程评价")
    @PostMapping("/delete")
    public CommonResult<Void> delete(@RequestParam @NotNull Long evaluationId) {
        try {
            log.info("EvaluationController.delete start, evaluationId: {}", evaluationId);
            evaluationService.deleteEvaluation(evaluationId);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("EvaluationController.delete error, evaluationId: {}", evaluationId, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "行程评价列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<EvaluationVO>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam Long travelId) {
        try {
            log.info("EvaluationController.list start, pageNum: {}, pageSize: {}, travelId: {}", pageNum, pageSize, travelId);
            CommonPage<EvaluationDTO> commonPageDTO = evaluationService.listEvaluationByPage(pageNum, pageSize, travelId);
            CommonPage<EvaluationVO> commonPageVO = new CommonPage<>();
            BeanUtils.copyProperties(commonPageDTO, commonPageVO);
            commonPageVO.setList(evaluationConvertor.convertEvaluationDTOToVOList(commonPageDTO.getList()));
            return CommonResult.success(commonPageVO);
        } catch (Exception e) {
            log.error("EvaluationController.list error, pageNum: {}, pageSize: {}, travelId: {}", pageNum, pageSize, travelId, e);
            return CommonResult.failed(e.getMessage());
        }
    }

}
