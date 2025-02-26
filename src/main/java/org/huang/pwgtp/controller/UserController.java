package org.huang.pwgtp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.huang.pwgtp.common.CommonResult;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Tag(name = "userApi")
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
    public CommonResult<String> register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {

        return CommonResult.success(username,"注册成功");
    }

    @Operation(summary = "会员登录")
    @PostMapping(value = "/login")
    public CommonResult<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
//        String token = memberService.login(username, password);
//        if (token == null) {
//            return CommonResult.validateFailed("用户名或密码错误");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", token);
//        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(null);
    }

    @Operation(summary = "获取会员信息")
    @GetMapping(value = "/info")
    public CommonResult<UserVO> info(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        return CommonResult.success(new UserVO());
    }

    @Operation(summary = "获取验证码")
    @GetMapping(value = "/getAuthCode")
    public CommonResult<String> getAuthCode(@RequestParam String telephone) {
        return CommonResult.success("authCode","获取验证码成功");
    }

    @Operation(summary = "会员修改密码")
    @PostMapping(value = "/updatePassword")
    public CommonResult<Void> updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
//        memberService.updatePassword(telephone,password,authCode);
        return CommonResult.success(null,"密码修改成功");
    }

    @Operation(summary = "刷新token")
    @GetMapping(value = "/refreshToken")
    public CommonResult<Map<String, String>> refreshToken(HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader);
//        String refreshToken = memberService.refreshToken(token);
//        if (refreshToken == null) {
//            return CommonResult.failed("token已经过期！");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", refreshToken);
//        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(new HashMap<>());
    }

}