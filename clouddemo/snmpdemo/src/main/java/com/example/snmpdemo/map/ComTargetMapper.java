package com.example.snmpdemo.map;

import com.example.snmpdemo.domain.ComTarget;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComTargetMapper {
    // 对应xml映射文件元素的ID
    ComTarget selectByPrimaryKey(long targetId);

    Boolean addComTarget(ComTarget comTarget);
}
