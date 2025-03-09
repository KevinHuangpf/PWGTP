package org.huang.pwgtp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.huang.pwgtp.repository.model.EvaluationDO;
import org.huang.pwgtp.repository.model.FavoriteDO;

import java.util.List;

@Mapper
public interface EvaluationMapper {

    int insert(EvaluationDO evaluationDO);

    EvaluationDO getById(Long id);

    List<EvaluationDO> list(Long travelId);

    int deleteById(Long id);

}
