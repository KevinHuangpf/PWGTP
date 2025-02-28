package org.huang.pwgtp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.huang.pwgtp.repository.model.UserDO;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDO getUserById(Long id);

    List<UserDO> listByIds(List<Long> ids);
}
