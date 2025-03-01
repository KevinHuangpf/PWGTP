
package org.huang.pwgtp.repository;//package org.huang.publicwelfaregrouptravelplatform.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.huang.pwgtp.repository.model.TravelActivityDO;

@Mapper
public interface TravelActivityMapper {


    long insert(@Param("travelActivityDO") TravelActivityDO travelActivityDO);


    int updateByPrimaryKey(@Param("travelActivityDO") TravelActivityDO travelActivityDO);


    TravelActivityDO selectById(@Param("id") Long id);

}
