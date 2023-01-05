package com.example.adauth.service.impl;

import com.example.adauth.domain.User;
import com.example.adauth.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Service
public class LdapServiceImpl implements LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;


    @Override
    public Iterable<User> getUserList(String cn) {
        LdapQuery query = LdapQueryBuilder.query().attributes("cn").where("objectclass")
                .is("user").and("cn").is(cn);
        return ldapTemplate.find(query,User.class);
    }

    @Override
    public User create(User user) {
        ldapTemplate.create(user);
        return user;
    }

    @Override
    public void delete(User user) {
        ldapTemplate.delete(user);
    }

    @Override
    public Iterable<User> findAll() {
        return ldapTemplate.findAll(User.class);
    }

    /*

    @Value("${ldap.domainName}")
    private String ldapDomainName;

    @Value("${ldap.base}")
    private String ldapBaseDn;

    //** * 获取部门列表 /
    @Override
    public List<String> getDepartmentList(String ldapBase, Filter filter) {
        return ldapTemplate.search(ldapBase, filter.encode(), new AttributesMapper() {
            @Override
            public String mapFromAttributes(Attributes attr) throws NamingException {
                String distinguishedName = (String)attr.get("distinguishedName").get();
                distinguishedName = StringUtils.substringBefore(distinguishedName,ldapBaseDn);

                return StringUtils.substringBeforeLast(distinguishedName, ",");
            }
        });
    }

    //** * 获取用户列表
    @Override
    public List<User> getPersonList(String ldapBase, Filter filter) {
        return ldapTemplate.search(ldapBase, filter.encode(), new AttributesMapper() {
            @Override
            public User mapFromAttributes(Attributes attr) throws NamingException {
                User person = new User();
                String distingugihedName = (String)attr.get("distinguishedName").get();
                person.setUserName((String)attr.get("username").get());
                person.setEmail((String)attr.get("mail").get());
                person.setRealName((String)attr.get("name").get());
                if (null != attr.get("mobile")) {
                    person.setMobile((String) attr.get("mobile").get());
                }
                if (null != attr.get("telephoneNumber")) {
                    person.setPhone((String) attr.get("telephoneNumber").get());
                }
                person.setLdapFlag(1);
                String departmentName = StringUtils.substringAfter(distingugihedName.split(",")[1], "OU=");
                person.setUnitName(departmentName);
                return person;
            }
        });
    }

    //* * 身份认证
    @Override
    public boolean authenticate(String userName, String password) {

        //String userDomainName = getDnForUser(userName);

        String userDomainName = String.format(ldapDomainName, userName);

        DirContext ctx = null;

        try {
            ctx = ldapTemplate.getContextSource().getContext(userDomainName,password);
            return true;

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            LdapUtils.closeContext(ctx);
        }

        return false;
    }

     */
}
