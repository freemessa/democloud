package com.example.adauth.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;
import java.io.Serializable;


@Entry(base = "dc=ad,dc=com", objectClasses = {"top","person"})
public class User implements Serializable {

    @Id
    @JsonIgnore
    private Name dn;

//    @DnAttribute(value = "distiguishedName")
//    private String distinguishedName;

    @Attribute(name = "cn")
//    @DnAttribute(value="cn", index=1)
    private String commonName;

    @Attribute(name = "sn")
    private String suerName;

    @Attribute(name="userPassword")
    private String userPassword;

    //    @DnAttribute(value="ou", index=0)
    @Attribute(name="ou")
    private String organizaitonUnit;

    @Attribute(name="displayName")
    private String displayName;

    public User() {
    }

    public User(String commonName, String organizaitonUnit) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou",organizaitonUnit)
                .add("cn",commonName)
                .build();
        this.dn = dn;
        this.commonName = commonName;
        this.organizaitonUnit = organizaitonUnit;
    }
}
