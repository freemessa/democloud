package com.example.flowable.service;

import com.example.flowable.config.MyUserCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.idm.api.GroupQuery;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.IdmIdentityServiceImpl;
import org.springframework.context.ApplicationContext;

import javax.websocket.DecodeException;

@Slf4j
public class MyIdentityServiceImpl  extends IdmIdentityServiceImpl {
    MyUserCenterProperties properties;

    ApplicationContext applicationContext;

    public MyIdentityServiceImpl(ApplicationContext applicationContext, MyUserCenterProperties properties, IdmEngineConfiguration idmEngineConfiguration) {
        super(idmEngineConfiguration);
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    @Override
    public boolean checkPassword(String userId, String password) {
        return executeCheckPassword(userId, password);
    }

    protected boolean executeCheckPassword(final String userId, final String password) {
        // Extra password check, see http://forums.activiti.org/comment/22312
        if (password == null || password.length() == 0) {
            throw new FlowableException("Null or empty passwords are not allowed!");
        }

        try {
            // 调用用户中心服务进行登录
            //UserCenterFeignService userCenterFeignService = applicationContext.getBean(UserCenterFeignService.class);
            //userCenterFeignService.login(new UcLoginReq(properties.getAppId(), userId, password));
            return true;
        } catch (DecodeException e) {
            log.error("用户中心认证失败：{}", userId, e);
            return false;
        } catch (FlowableException e) {
            log.error("Could not authenticate user : {}", userId, e);
            return false;
        }
    }

    // 很多方法直接不支持
    // throw new FlowableException("My identity service doesn't support getting groups with privilege");
    //...

    @Override
    public UserQuery createUserQuery() {
        // 通过调用其他服务来查询
        //SoaCommonPhpFeignService soaCommonPhpFeignService = applicationContext.getBean(SoaCommonPhpFeignService.class);
        //SoaCommonJavaFeignService soaCommonJavaFeignService = applicationContext.getBean(SoaCommonJavaFeignService.class);
        //return new MyUserQueryImpl(soaCommonPhpFeignService, soaCommonJavaFeignService);
        return createUserQuery();
    }

    // 这个在对任务查询时需要针对user_id/group_id查询时需要
    @Override
    public GroupQuery createGroupQuery() {
        return new MyGroupQueryImpl();
    }
}
