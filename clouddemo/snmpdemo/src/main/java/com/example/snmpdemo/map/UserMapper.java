package com.example.snmpdemo.map;


import com.example.snmpdemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 对应xml映射文件元素的ID
    User getUserInfoByUserId(String userId);
}
