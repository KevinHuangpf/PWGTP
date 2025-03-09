
package org.huang.pwgtp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.huang.pwgtp.repository.model.TravelDO;

import java.util.List;

@Mapper
public interface TravelMapper {


    int insert(TravelDO travelDO);

    int updateById(TravelDO travelDO);

    TravelDO getById(Long id);

    List<TravelDO> list(String fuzzyKey);

    int deleteById(Long id);

    int updateStatusById(Long id, String status);

}
