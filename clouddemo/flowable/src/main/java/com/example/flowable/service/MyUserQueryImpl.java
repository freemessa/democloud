package com.example.flowable.service;

import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class MyUserQueryImpl extends UserQueryImpl {
    //SoaCommonPhpFeignService soaCommonPhpFeignService;
    //SoaCommonJavaFeignService soaCommonJavaFeignService;

    //public MyUserQueryImpl(SoaCommonPhpFeignService soaCommonPhpFeignService, SoaCommonJavaFeignService soaCommonJavaFeignService) {
    //    this.soaCommonPhpFeignService = soaCommonPhpFeignService;
    //    this.soaCommonJavaFeignService = soaCommonJavaFeignService;
    //}

    @Override
    public long executeCount(CommandContext commandContext) {
        return executeQuery().size();
    }

    @Override
    public List<User> executeList(CommandContext commandContext) {
        return executeQuery();
    }

    protected List<User> executeQuery() {
        if (getId() != null) {
            List<User> result = new ArrayList<>();
            UserEntity user = findById(getId());
            if (user != null) {
                result.add(user);
            }
            return result;

        } else if (getIdIgnoreCase() != null) {
            List<User> result = new ArrayList<>();
            UserEntity user = findById(getIdIgnoreCase());
            if (user != null) {
                result.add(user);
            }
            return result;

        } else if (getFullNameLike() != null) {
            return executeNameQuery(getFullNameLike());

        } else if (getFullNameLikeIgnoreCase() != null) {
            return executeNameQuery(getFullNameLikeIgnoreCase());

        } else {
            return executeAllUserQuery();
        }
    }

    // 我们的使用场景并不需要这些返回值，所以都返回null
    protected List<User> executeNameQuery(String name) {
        return null;
    }

    protected List<User> executeAllUserQuery() {
        return null;
    }

    protected UserEntity findById(final String userId) {
        return null;
    }
}
