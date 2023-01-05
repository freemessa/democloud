package com.example.ldapauth.service;

import com.example.ldapauth.domain.User;
import org.springframework.ldap.filter.Filter;

import java.util.List;

public interface LdapService {
    User findOne(String cn);

}
