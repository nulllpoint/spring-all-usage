package com.demo.config;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 主要用在保存当前任务时下一节点显示的说明的属性封装</br>
 * 如果存在多个网关节点, 返回的节点编码为0 , 节点名称为多节点名称组合
 * </p>
 *
 * @author liuhoujie
 * @date 2020/9/30
 */
@Data
@Builder
public class ProcessNodeDesc {
    private Integer nodeCode;
    private String nodeName;
}
