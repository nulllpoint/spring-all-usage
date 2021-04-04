package com.demo.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Map;

/**
 * @author liuhoujie
 * @date 2020/9/29
 */
@Data
public class ProcessNode {
    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 节点编码
     */
    private Integer nodeCode;
    /**
     * 节点名
     */
    private String nodeName;
    // /**
    //  * <p>
    //  * 节点操作类型 <br>
    //  * - audit 审核 <br>
    //  * - submit 提交 <br>
    //  * - nop 无 <br>
    //  * </p>
    //  */
    // private String nodeOperateType = "nop";

    /**
     * 是否需要提交操作 , 业务需求, 默认false 不需要
     */
    private boolean requireSubmit = false;

    /**
     * <p>
     * 节点类型  <br>
     * - startEvent 开始 <br>
     * - endEvent 结束 <br>
     * - suspendEvent 暂停事件 <br>
     * - delayEvent 延期事件 <br>
     * - userTask 用户任务  (默认)<br>
     * </p>
     */
    private String nodeType = "userTask";
    /**
     * 候选组
     */
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Long[] candidateGroup;
    /**
     * 候选用户
     */
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Long[] candidateUser;
    /**
     * 可回滚 , (默认)false不可回滚
     */
    private boolean fallbackAble = false;
    /**
     * 下一节点集合
     */
    private Map<String, String> nexts;
}
