package com.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liuhoujie
 * @date 2020/9/29
 */
@Configuration
@Slf4j
public class ProcessConfiguration {

    private static final YamlMapFactoryBean yamlMapFactoryBean;
    private static final Map<String, Object> processMap;

    static {
        yamlMapFactoryBean = new YamlMapFactoryBean();
        // 加载流程配置, 支持加载多配置
        yamlMapFactoryBean.setResources(new ClassPathResource("process.yml"));
        // 获取map格式配置
        processMap = yamlMapFactoryBean.getObject();
    }

    @Resource
    ObjectMapper objectMapper;

    /**
     * 行政执法流程 初始化&注入容器
     */
    @ConditionalOnMissingBean
    @Bean
    public AleProcess aleProcess() {
        AleProcess aleProcess = new AleProcess();

        // 校验配置
        if (processMap == null || processMap.isEmpty()) {
            log.warn("流程配置加载为空!!!");
            log.warn("无流程配置加载, 返回基础对象注册到容器, 不影响依赖注入");
            return aleProcess;
        }

        // 流程配置转对象 & 初始化AdministrationLawExecuteProcess
        try {
            JsonNode jsonNodeNodes = objectMapper.readTree(objectMapper.writeValueAsString(processMap)).get("administrationLawExecute").get("nodes");
            CollectionLikeType collectionLikeType = objectMapper.getTypeFactory().constructCollectionLikeType(List.class, ProcessNode.class);
            aleProcess.init(objectMapper.readValue(jsonNodeNodes.toString(), collectionLikeType));
        } catch (JsonProcessingException e) {
            log.error("读取流程配置异常,返回基础对象注册到容器, 不影响依赖注入", e);
            return aleProcess;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aleProcess;
    }
}
