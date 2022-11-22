package com.example.snmpdemo.server;

import com.example.snmpdemo.domain.ComTarget;


public interface ComTargetService {
    ComTarget getComTargetById(long targetId);

    boolean addComTarget(ComTarget comTarget);
}
