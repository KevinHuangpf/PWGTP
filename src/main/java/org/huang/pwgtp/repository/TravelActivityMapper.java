
package org.huang.pwgtp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.huang.pwgtp.repository.model.TravelActivityDO;

@Mapper
public interface TravelActivityMapper {


    long insert(@Param("travelActivityDO") TravelActivityDO travelActivityDO);

    int updateById(@Param("travelActivityDO") TravelActivityDO travelActivityDO);

    TravelActivityDO getById(@Param("id") Long id);

    int deleteById(@Param("id") Long id);

    int updateStatusById(@Param("id") Long id, @Param("status") String status);


}
