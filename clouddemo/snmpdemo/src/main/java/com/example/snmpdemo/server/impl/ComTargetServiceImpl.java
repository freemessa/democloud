package com.example.snmpdemo.server.impl;

import com.example.snmpdemo.domain.ComTarget;
import com.example.snmpdemo.map.ComTargetMapper;
import com.example.snmpdemo.server.ComTargetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ComTargetServiceImpl implements ComTargetService {

    // 注入mapper类
    @Resource
    private ComTargetMapper comTargetMapper;

    @Override
    public ComTarget getComTargetById(long targetId) {
        return comTargetMapper.selectByPrimaryKey(targetId);
    }

    @Override
    public boolean addComTarget(ComTarget comTarget) {
        return comTargetMapper.addComTarget(comTarget);
    }
}
