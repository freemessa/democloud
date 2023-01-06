package com.example.flowable.service;

import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.idm.api.Group;
import org.flowable.idm.engine.impl.GroupQueryImpl;

import java.util.ArrayList;
import java.util.List;

public class MyGroupQueryImpl extends GroupQueryImpl {
    @Override
    public List<Group> executeList(CommandContext commandContext) {
        // 也不需要这里的值，只是为了在执行其他逻辑时不报错
        return new ArrayList<>();
    }
}
