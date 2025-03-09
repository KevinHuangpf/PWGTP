package org.huang.pwgtp.controller;


import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.controller.vo.FavoriteVO;
import org.huang.pwgtp.controller.vo.TravelDetailVO;
import org.huang.pwgtp.controller.vo.TravelSaveVO;
import org.huang.pwgtp.convertor.FavoriteConvertor;
import org.huang.pwgtp.service.FavoriteService;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.FavoriteDTO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("favorite")
@Tag(name = "favoriteApi")
@Validated
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteConvertor favoriteConvertor;

    @Operation(summary = "添加收藏")
    @PostMapping("/add")
    public CommonResult<Void> add(@RequestParam @NotNull String favoriteType, @RequestParam @NotNull Long associateId) {
        try {
            log.info("FavoriteController.add start, favoriteType: {}, associateId: {}",favoriteType, associateId);
            favoriteService.addFavorite(userService.getCurrentUser().getId(), favoriteType, associateId);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("FavoriteController.add error, favoriteType: {}, associateId: {}",favoriteType, associateId, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "删除收藏")
    @PostMapping("/delete")
    public CommonResult<Void> delete(@RequestParam @NotNull Long favoriteId) {
        try {
            log.info("FavoriteController.delete start, favoriteId: {}", favoriteId);
            favoriteService.deleteFavorite(favoriteId);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("FavoriteController.delete error, favoriteId: {}", favoriteId, e);
            return CommonResult.failed(e.getMessage());
        }
    }



    @Operation(summary = "我的收藏")
    @GetMapping("/list")
    public CommonResult<CommonPage<FavoriteVO>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String favoriteType) {
        try {
            log.info("FavoriteController.list start, pageNum: {}, pageSize: {}, favoriteType: {}", pageNum, pageSize, favoriteType);
            CommonPage<FavoriteDTO> commonPageDTO = favoriteService.listFavoriteByPage(pageNum, pageSize, userService.getCurrentUser().getId(), favoriteType);
            CommonPage<FavoriteVO> commonPageVO = new CommonPage<>();
            BeanUtils.copyProperties(commonPageDTO, commonPageVO);
            commonPageVO.setList(favoriteConvertor.convertFavoriteDTOToVOList(commonPageDTO.getList()));
            return CommonResult.success(commonPageVO);
        } catch (Exception e) {
            log.error("FavoriteController.list error, pageNum: {}, pageSize: {}, favoriteType: {}", pageNum, pageSize, favoriteType, e);
            return CommonResult.failed(e.getMessage());
        }

    }


}
