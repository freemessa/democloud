package com.example.flowable.utils;


import org.flowable.common.engine.api.FlowableIllegalStateException;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.security.DefaultPrivileges;
import org.flowable.ui.common.security.FlowableSecurityScopeProvider;
import org.flowable.ui.common.security.SecurityScope;
import org.flowable.ui.common.security.SecurityScopeProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 重构流程编辑器获取用户信息
 */
public class SecurityUtil {

    private static User assumeUser;

    private static SecurityScopeProvider securityScopeProvider = new FlowableSecurityScopeProvider();

    private SecurityUtil() {
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentUserId() {
        User user = getCurrentUserObject();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    /**
     * @return the {@link User} object associated with the current logged in user.
     */
    public static User getCurrentUserObject() {
        if (assumeUser != null) {
            return assumeUser;
        }

        RemoteUser user = new RemoteUser();
        user.setId("admin");
        user.setDisplayName("admin");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("admin@flowable.com");
        user.setPassword("test");
        List<String> pris = new ArrayList<>();
        pris.add(DefaultPrivileges.ACCESS_MODELER);
        pris.add(DefaultPrivileges.ACCESS_IDM);
        pris.add(DefaultPrivileges.ACCESS_ADMIN);
        pris.add(DefaultPrivileges.ACCESS_TASK);
        pris.add(DefaultPrivileges.ACCESS_REST_API);
        user.setPrivileges(pris);
        return user;
    }

    public static void setSecurityScopeProvider(SecurityScopeProvider securityScopeProvider) {
        SecurityUtil.securityScopeProvider = securityScopeProvider;
    }

    public static SecurityScope getCurrentSecurityScope() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null) {
            return getSecurityScope(securityContext.getAuthentication());
        }
        return null;
    }

    public static SecurityScope getSecurityScope(Authentication authentication) {
        return securityScopeProvider.getSecurityScope(authentication);
    }

    public static SecurityScope getAuthenticatedSecurityScope() {
        SecurityScope currentSecurityScope = getCurrentSecurityScope();
        if (currentSecurityScope != null) {
            return currentSecurityScope;
        }
        throw new FlowableIllegalStateException("User is not authenticated");
    }

    public static void assumeUser(User user) {
        assumeUser = user;
    }

    public static void clearAssumeUser() {
        assumeUser = null;
    }
}