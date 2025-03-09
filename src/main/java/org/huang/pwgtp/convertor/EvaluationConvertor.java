package org.huang.pwgtp.convertor;

import org.huang.pwgtp.common.bizEnum.FavoriteTypeEnum;
import org.huang.pwgtp.controller.vo.EvaluationVO;
import org.huang.pwgtp.controller.vo.FavoriteVO;
import org.huang.pwgtp.controller.vo.UserVO;
import org.huang.pwgtp.service.UserService;
import org.huang.pwgtp.service.model.EvaluationDTO;
import org.huang.pwgtp.service.model.FavoriteDTO;
import org.huang.pwgtp.service.model.TravelDTO;
import org.huang.pwgtp.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class EvaluationConvertor {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertor userConvertor;


    public List<EvaluationVO> convertEvaluationDTOToVOList(List<EvaluationDTO> evaluationDTOList) {
        if(CollectionUtils.isEmpty(evaluationDTOList)){
            return new ArrayList<>();
        }
        List<EvaluationVO> evaluationVOList = new ArrayList<>();
        for(EvaluationDTO evaluationDTO: evaluationDTOList){
            EvaluationVO evaluationVO = ConvertUtil.convert(EvaluationVO.class, evaluationDTO);
            UserVO userVO = userConvertor.convertDTOToVO(userService.getUserById(evaluationDTO.getCreatorUid()));
            evaluationVO.setCreator(userVO);
            evaluationVOList.add(evaluationVO);
        }
        return evaluationVOList;
    }

}
