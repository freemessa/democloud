package com.example.ldapauth.service;

import com.example.ldapauth.domain.User;
import org.springframework.data.ldap.repository.LdapRepository;

/** * UserDao继承CrudRepository接口实现基于Ldap的增删改查操作 */
//public interface UserDao extends CrudRepository<Person, Name> {
//}

public interface UserDao extends LdapRepository<User> {
}
