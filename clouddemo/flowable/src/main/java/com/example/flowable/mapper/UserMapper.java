package com.example.flowable.mapper;

import com.example.flowable.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserInfoByUserId(String userId);
}
