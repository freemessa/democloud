package com.example.ldapauth.service.impl;

import com.example.ldapauth.domain.User;
import com.example.ldapauth.service.LdapService;
import com.example.ldapauth.service.UserDao;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LdapServiceImpl implements LdapService {
    @Resource
    UserDao userRepository;

    @Override
    public User findOne(String cn) {
        LdapQuery query = LdapQueryBuilder.query().attributes("cn").where("objectclass")
                .is("person").and("cn").is(cn);
        return userRepository.findOne(query).orElse(null);
    }
}
