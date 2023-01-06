package com.example.flowable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    //SoaCommonPhpFeignService soaCommonPhpFeignService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 去别的服务查询用户 MySoaEmployeeDetailResp需要实现UserDetails接口
       // MySoaEmployeeDetailResp employeeByAccount = soaCommonPhpFeignService.getEmployeeByAccount(s);
       // if (null == employeeByAccount) {
       //     throw new UsernameNotFoundException("用户不存在");
       // }
       // return employeeByAccount;
        return  null;
    }
}
