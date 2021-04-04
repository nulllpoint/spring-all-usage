package com.demo.flowable.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.ui.common.model.UserRepresentation;
import org.flowable.ui.common.security.DefaultPrivileges;
import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类主要用于替换源码包里的接口
 *
 * @author liuhoujie
 * @date 2020/9/19
 */
@RestController
@RequestMapping("replace")
public class ReplaceReqDataController {
    @Resource
    protected ObjectMapper objectMapper;

    /**
     * <p>
     * 替换源码包的/app/rest/account/ 接口获取用户授权信息
     * 需要在modler静态static/scripts/uri-config.js中getAccountUrl修改为此请求地址
     * </p>
     */
    @GetMapping(value = "/rest/account", produces = "application/json")
    public UserRepresentation getAccount() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName("admin");
        userRepresentation.setFullName("admin");
        userRepresentation.setId("admin");

        List<String> privileges = new ArrayList<>();
        privileges.add(DefaultPrivileges.ACCESS_MODELER);
        privileges.add(DefaultPrivileges.ACCESS_IDM);
        privileges.add(DefaultPrivileges.ACCESS_ADMIN);
        privileges.add(DefaultPrivileges.ACCESS_TASK);
        privileges.add(DefaultPrivileges.ACCESS_REST_API);
        userRepresentation.setPrivileges(privileges);

        return userRepresentation;
    }

    /**
     * <p>
     * 替换源码包的/rest/stencil-sets/editor 获取菜单信息
     * 需要在modler静态static/editor-app/configuration/url-config.js中getStencilSet替换为此地址
     * </p>
     */
    @GetMapping(value = "/rest/stencil-sets/editor", produces = "application/json")
    public JsonNode getStencilSetForEditor(@RequestParam String language) {
        JsonNode stencilNode;
        try {
            if (!"en".equalsIgnoreCase(language)) {
                stencilNode = objectMapper.readTree(this.getClass().getClassLoader().getResourceAsStream("static/stencilset/zh/stencilset_bpmn.json"));
            } else {
                stencilNode = objectMapper.readTree(this.getClass().getClassLoader().getResourceAsStream("static/stencilset/stencilset_bpmn.json"));
            }
            return stencilNode;
        } catch (Exception e) {
            // LOGGER.error("Error reading bpmn stencil set json", e);
            throw new InternalServerErrorException("Error reading bpmn stencil set json");
        }
    }
}
