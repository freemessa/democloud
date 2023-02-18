
package com.example.flowable.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/flowapp")
public class FlowableStencilSetResource {

    @Autowired
    protected ObjectMapper objectMapper;

    @RequestMapping(value = "/rest/stencil-sets/editor", method = RequestMethod.GET, produces =
            "application/json")
    public JsonNode getStencilSetForEditor() {
        try {
            JsonNode stencilNode =
                    objectMapper.readTree(this.getClass().getClassLoader().
                            getResourceAsStream("stencilset/stencilset_bpmn.json"));
            return stencilNode;
        } catch (Exception e) {
            throw new InternalServerErrorException("Error reading bpmn stencil set json");
        }
    }
}
