package org.huang.pwgtp.service;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.huang.pwgtp.repository.model.EvaluationDO;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class AIService {

    String systemRoleConfig = "你是一位旅行平台的经验专家，请根据用户的提问生成旅行攻略或出行注意事项，规范并简短回答，不要有问候词和对话，只提供攻略信息，不要超过1000字";

    public String askForTravelPlan(String userAskContent) throws Exception{
        Generation gen = new Generation();
        // 系统设定，设定模型的角色和能力
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content(systemRoleConfig)
                .build();
        // 用户提问，模型根据用户提问，生成旅行攻略
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(userAskContent)
                .build();
        // 组合参赛
        GenerationParam param = GenerationParam.builder()
                .apiKey("sk-39fafc54b53a4a7987273a1d83c4ccea")
                .model("qwen-max").topP(0.7)
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .enableSearch(true) // 开启联网搜索功能
                .build();
        // 发起调用
        GenerationResult generationResult = gen.call(param);
        log.info("AIService.askForTravelPlan gen.call, param: {}, generationResult:{}", JSON.toJSONString(Arrays.asList(systemMsg, userMsg)), JSON.toJSONString(generationResult));
        return generationResult.getOutput().getChoices().get(0).getMessage().getContent();
    }

}
