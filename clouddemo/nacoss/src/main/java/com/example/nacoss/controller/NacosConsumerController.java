package com.example.nacoss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/nacosclient")
public class NacosConsumerController {
    private final RestTemplate restTemplate;

    @Autowired
    public NacosConsumerController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @RequestMapping(value = "/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://nacos-server-demo/config/nacosserver/" + str, String.class);
    }
}
