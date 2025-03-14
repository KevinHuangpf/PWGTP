package org.huang.pwgtp.controller;


import com.alibaba.fastjson.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.controller.vo.UserVO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Tag(name = "userApi")
@Slf4j
@Validated
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @Operation(summary = "hello")
    public String getHello() {
        return "Hello SpringBoot !";
    }


    @Operation(summary = "会员注册")
    @PostMapping(value = "/register")
    public CommonResult<String> register(@RequestParam @NotNull String username,
                                 @RequestParam @NotNull String password,
                                 @RequestParam @NotNull String telephone) {
        try {
            log.info("UserController.register start, username: {}, password: {}, telephone: {}", username, password, telephone);
            userService.register(username, password, telephone);
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("UserController.register error, username: {}, password: {}, telephone: {}", username, password, telephone, e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "获取会员信息")
    @GetMapping(value = "/info")
    public CommonResult<UserVO> info() {
        try {
            log.info("UserController.info start");
            return CommonResult.success(ConvertUtil.convert(UserVO.class, userService.getCurrentUser()));
        } catch (Exception e) {
            log.error("UserController.info error", e);
            return CommonResult.failed(e.getMessage());
        }
    }

}