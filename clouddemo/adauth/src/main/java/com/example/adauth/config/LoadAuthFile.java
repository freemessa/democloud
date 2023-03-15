package com.example.adauth.config;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@Component
public class LoadAuthFile {
    @Value("${auth.file:/mnt/auth/auth.poe}")
    private String authFilePath;

    @PostConstruct
    private void loadLicenseFile()  throws  Exception {
        log.info("读取文件：" + authFilePath);
        //File file = new File(authFilePath);
        ClassPathResource classPathResource = new ClassPathResource(authFilePath);
        File file = classPathResource.getFile();

        Assert.isTrue(file.exists() && file.isFile(), "没有找到文件:" + authFilePath);
    }

}
