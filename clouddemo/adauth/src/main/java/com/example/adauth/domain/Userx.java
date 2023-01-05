package com.example.adauth.domain;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;

import javax.naming.Name;
//import javax.persistence.Id;

@Data
@Entry(base = "ou=XX公司,dc=example,dc=com", objectClasses = {"OrganizationalPerson", "Person", "top"})
public class Userx {
   // @Id
    private Name id;

    @DnAttribute(value = "distiguishedName")
    private String distinguishedName;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String suerName;

    @Attribute(name = "email")
    private String email;
}
