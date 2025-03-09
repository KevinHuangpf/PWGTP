package org.huang.pwgtp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.huang.pwgtp.repository.model.UserDO;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(UserDO userDO);

    int updateById(UserDO userDO);

    UserDO getById(Long id);

    UserDO getByName(String name);

    List<UserDO> listByIds(List<Long> ids);

}
