package com.example.adauth.service;

import com.example.adauth.domain.User;
import org.springframework.ldap.filter.Filter;

import java.util.List;

public interface LdapService {
    //List<String> getDepartmentList(String ldapBase, Filter filter);
    //List<User> getPersonList(String ldapBase, Filter filter);
    //boolean authenticate(String userName, String password);
    Iterable<User> getUserList(String cn);
    User create(User user);
    void delete(User user);
    Iterable<User> findAll();


}
