package com.example.adauth.controller;

import com.example.adauth.domain.User;
import com.example.adauth.service.LdapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "域账号操作接口")
@RequestMapping("/ldapauth")
@Slf4j
public class findAll {
    @Autowired
    private LdapService userDao;

    @ApiOperation("findAll")
    @RequestMapping(value ="/findAll", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> findAll() throws Exception {
        userDao.findAll().forEach(p -> {
            //System.out.println("Distigushed Name:" + p.distinguishedName);
            log.info(p.toString());
        });
        return userDao.findAll();
    }
}
