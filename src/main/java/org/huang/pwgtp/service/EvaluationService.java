package org.huang.pwgtp.service;

import com.github.pagehelper.PageHelper;
import org.huang.pwgtp.common.CommonPage;
import org.huang.pwgtp.repository.EvaluationMapper;
import org.huang.pwgtp.repository.model.EvaluationDO;
import org.huang.pwgtp.repository.model.FavoriteDO;
import org.huang.pwgtp.service.model.EvaluationDTO;
import org.huang.pwgtp.service.model.FavoriteDTO;
import org.huang.pwgtp.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    public void addEvaluation(Long creatorUid, Long travelId, String evaluationContent) throws Exception{
        EvaluationDO evaluationDO = new EvaluationDO();
        evaluationDO.setCreatorUid(creatorUid);
        evaluationDO.setModifierUid(creatorUid);
        evaluationDO.setTravelId(travelId);
        evaluationDO.setEvaluationContent(evaluationContent);
        int updateResult = evaluationMapper.insert(evaluationDO);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public void deleteEvaluation(Long id) throws Exception{
        EvaluationDO evaluationDO = evaluationMapper.getById(id);
        if(Objects.isNull(evaluationDO)){
            throw new Exception("记录不存在，无法操作");
        }
        int updateResult = evaluationMapper.deleteById(id);
        if(updateResult != 1){
            throw new Exception("操作失败");
        }
    }

    public CommonPage<EvaluationDTO> listEvaluationByPage(Integer pageNum, Integer pageSize, Long travelId){
        PageHelper.startPage(pageNum, pageSize).setOrderBy("gmt_create desc");
        List<EvaluationDO> favoriteDOList = evaluationMapper.list(travelId);
        CommonPage<EvaluationDO> commonPageDO = CommonPage.restPage(favoriteDOList);
        CommonPage<EvaluationDTO> commonPageDTO = new CommonPage<>();
        BeanUtils.copyProperties(commonPageDO, commonPageDTO);
        commonPageDTO.setList(ConvertUtil.convertList(EvaluationDTO.class, commonPageDO.getList()));
        return commonPageDTO;
    }

}
