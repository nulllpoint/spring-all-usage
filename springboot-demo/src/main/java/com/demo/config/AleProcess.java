package com.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 封装的流程节点工具类</br>
 * 业务中只需要注入此类, 不用关心配置读取
 * </p>
 *
 * @author liuhoujie
 * @date 2020/9/29
 */
@SuppressWarnings({"unused"})
public class AleProcess {
    private List<ProcessNode> nodes;
    private List<ProcessNode> fallbackAbleNodes;
    private Map<Integer, ProcessNode> nodeCodeMap;
    private Map<String, ProcessNode> nodeIdMap;

    /**
     * 初始化 流程节点
     */
    void init(List<ProcessNode> nodes) {
        this.nodes = nodes;
        try {
            /* 以nodeCode为key的map集合 */
            nodeCodeMap = nodes.parallelStream().collect(Collectors.toMap(ProcessNode::getNodeCode, node -> node));
            /* 以nodeId为key的map集合 */
            nodeIdMap = nodes.parallelStream().collect(Collectors.toMap(ProcessNode::getNodeId, node -> node));
            /* 可回滚的节点集合 */
            fallbackAbleNodes = nodes.parallelStream().filter(ProcessNode::isFallbackAble).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过节点枚举获取节点
     *
     * @param node 节点枚举
     * @return node
     */
    public ProcessNode getNode(NodeEnum node) {
        return nodeIdMap.get(node.id);
    }

    /**
     * 通过节点id获取节点
     *
     * @param nodeId 节点id
     * @return node
     */
    public ProcessNode getNode(String nodeId) {
        return nodeIdMap.get(nodeId);
    }

    /**
     * 通过节点编码获取节点
     *
     * @param nodeCode 节点编码
     * @return node
     */
    public ProcessNode getNode(Integer nodeCode) {
        return nodeCodeMap.get(nodeCode);
    }

    /**
     * 获取全部可回滚节点
     *
     * @return nodes
     */
    public List<ProcessNode> fallbackNodes() {
        return this.fallbackAbleNodes;
    }

    /**
     * 获取当前节点可回滚节点
     *
     * @param nodeId 节点id
     * @return nodes
     */
    public List<ProcessNode> fallbackNodes(String nodeId) {
        return this.nodes.subList(0, nodes.indexOf(nodeIdMap.get(nodeId))).parallelStream().filter(ProcessNode::isFallbackAble).collect(Collectors.toList());
    }

    /**
     * 获取当前节点可回滚节点
     *
     * @param nodeCode 节点编码
     * @return nodes
     */
    public List<ProcessNode> fallbackNodes(Integer nodeCode) {
        return this.nodes.subList(0, nodes.indexOf(nodeCodeMap.get(nodeCode))).parallelStream().filter(ProcessNode::isFallbackAble).collect(Collectors.toList());
    }

    /**
     * 获取当前节点可回滚节点
     *
     * @param node 节点枚举
     * @return nodes
     */
    public List<ProcessNode> fallbackNodes(NodeEnum node) {
        return this.nodes.subList(0, nodes.indexOf(nodeIdMap.get(node.id))).parallelStream().filter(ProcessNode::isFallbackAble).collect(Collectors.toList());
    }

    /**
     * 获取下一节点
     *
     * @param node      节点枚举
     * @param condition 条件枚举
     * @return nodes
     */
    public ProcessNode nextNode(NodeEnum node, ConditionEnum condition) {
        return this.getNode(this.getNode(node).getNexts().get(condition.value));
    }

    /**
     * 获取下一节点
     *
     * @param nodeId    节点id
     * @param condition 条件枚举
     * @return nodes
     */
    public ProcessNode nextNode(String nodeId, ConditionEnum condition) {
        return this.getNode(this.getNode(nodeId).getNexts().get(condition.value));
    }

    /**
     * 获取下一节点
     *
     * @param nodeCode  节点编码
     * @param condition 条件枚举
     * @return nodes
     */
    public ProcessNode nextNode(Integer nodeCode, ConditionEnum condition) {
        return this.getNode(this.getNode(nodeCode).getNexts().get(condition.value));
    }

    /**
     * <p>
     * 获取下一节点说明</br>
     * 主要用在保存当前任务时下一节点显示的说明</br>
     * 如果存在多个网关节点, 返回的节点编码为0 , 节点名称为多节点名称组合
     * </p>
     *
     * @param nodeId 节点编码
     * @return nodes
     */
    public ProcessNodeDesc nextNodeDesc(String nodeId) {
        return this.nextNodeDesc(this.getNode(nodeId).getNexts());
    }

    /**
     * <p>
     * 获取下一节点说明</br>
     * 主要用在保存当前任务时下一节点显示的说明</br>
     * 如果存在多个网关节点, 返回的节点编码为0 , 节点名称为多节点名称组合
     * </p>
     *
     * @param nodeCode 节点编码
     * @return nodes
     */
    public ProcessNodeDesc nextNodeDesc(Integer nodeCode) {
        return this.nextNodeDesc(this.getNode(nodeCode).getNexts());
    }

    /**
     * <p>
     * 获取下一节点说明</br>
     * 主要用在保存当前任务时下一节点显示的说明</br>
     * 如果存在多个网关节点, 返回的节点编码为0 , 节点名称为多节点名称组合
     * 如果没有下个节点, 返回节点编码为-1 , 节点名称为"-" , 如: 结束节点
     * </p>
     *
     * @param node 节点枚举
     * @return nodes
     */
    public ProcessNodeDesc nextNodeDesc(NodeEnum node) {
        return this.nextNodeDesc(this.getNode(node).getNexts());
    }

    public ProcessNodeDesc nextNodeDesc(Map<String, String> nextsMap) {
        // 为空时, 代表上个节点未指定下个节点, 编码设为1, 名称 "-"
        if (nextsMap == null || nextsMap.isEmpty()) {
            return ProcessNodeDesc.builder()
                    .nodeCode(-1)
                    .nodeName("-")
                    .build();
        }

        ProcessNodeDesc processNodeDesc = null;
        List<String> conditions = new ArrayList<>(nextsMap.values());
        if (conditions.size() > 1) {    // 大于1 说明是网关性质的多节点, 编码设为0, 名称为"|"分隔的多个节点名称组合
            StringBuilder nodeName = new StringBuilder();
            conditions.forEach(nodeId -> nodeName.append(this.getNode(nodeId).getNodeName()).append("|"));
            processNodeDesc = ProcessNodeDesc.builder()
                    .nodeCode(0)
                    .nodeName(nodeName.subSequence(0, nodeName.length() - 1).toString())
                    .build();
        } else if (conditions.size() == 1) {
            processNodeDesc = ProcessNodeDesc.builder()
                    .nodeCode(this.getNode(conditions.get(0)).getNodeCode())
                    .nodeName(this.getNode(conditions.get(0)).getNodeName())
                    .build();
        }
        return processNodeDesc;
    }

    /**
     * 获取流程节点归属状态
     *
     * @param node 节点枚举
     * @return NODESTAGE
     */
    public NodeStageEnum nodeStage(NodeEnum node) {
        return this.nodeStage(this.getNode(node));
    }

    /**
     * 获取流程节点归属状态
     *
     * @param nodeId 节点id
     * @return NODESTAGE
     */
    public NodeStageEnum nodeStage(String nodeId) {
        return this.nodeStage(this.getNode(nodeId));
    }

    /**
     * 获取流程节点归属状态
     *
     * @param nodeCode 节点编码
     * @return NODESTAGE
     */
    public NodeStageEnum nodeStage(Integer nodeCode) {
        return this.nodeStage(this.getNode(nodeCode));
    }

    /**
     * 获取流程节点归属状态
     *
     * @param processNode 节点对象
     * @return NODESTAGE
     */
    public NodeStageEnum nodeStage(ProcessNode processNode) {
        /* 开始节点 -> 开始状态*/
        if (NodeEnum.START.id.equalsIgnoreCase(processNode.getNodeId())) return NodeStageEnum.START;
            /* 开始事件 -> 开始状态 */
        else if (NodeTypeEnum.START_EVENT.value.equalsIgnoreCase(processNode.getNodeType())) return NodeStageEnum.START;
            /* 结束节点 -> 结束状态 */
        else if (NodeEnum.END.id.equalsIgnoreCase(processNode.getNodeId())) return NodeStageEnum.END;
            /* 结束事件 -> 结束状态 */
        else if (NodeTypeEnum.END_EVENT.value.equalsIgnoreCase(processNode.getNodeType())) return NodeStageEnum.END;
            /* 用户任务事件 -> 进行中状态 */
        else if (NodeTypeEnum.USER_TASK.value.equalsIgnoreCase(processNode.getNodeType())) return NodeStageEnum.PROCESS;
            /* 默认   -> 进行中状态 */
        else return NodeStageEnum.PROCESS;
    }

    /**
     * <p>
     * 节点内部枚举</br>
     * 包含了节点的id和编码<br>
     * 根据节点配置生成的数据
     * </p>
     */
    public enum NodeEnum {
        /* 立案 */
        FILINGCASE_APPLY("filingCase_apply" ,1000 ),
        FILINGCASE_DEPARTMENT_AUDIT("filingCase_department_audit" ,1001 ),
        FILINGCASE_LEGAL_AUDIT("filingCase_legal_audit" ,1002 ),
        FILINGCASE_CHARGEPERSON_AUDIT("filingCase_chargePerson_audit" ,1003 ),

        /* 案件处理 */
        HANDLECASE_TAKEEVIDENCE("handleCase_takeEvidence" ,2000 ),
        HANDLECASE_DEPARTMENT_AUDIT("handleCase_department_audit" ,2001 ),
        HANDLECASE_LEGAL_AUDIT("handleCase_legal_audit" ,2002 ),
        HANDLECASE_CHARGEPERSON_AUDIT("handleCase_chargePerson_audit" ,2003 ),

        /* 行政处罚 */
        ADMINISTRATIONPUNISH_NOTIFY("administrationPunish_notify" ,3000 ),
        ADMINISTRATIONPUNISH_STATEANDDEFEND("administrationPunish_stateAndDefend" ,3001 ),
        ADMINISTRATIONPUNISH_DEPARTMENT_AUDIT("administrationPunish_department_audit" ,3002 ),
        ADMINISTRATIONPUNISH_LEGAL_AUDIT("administrationPunish_legal_audit" ,3003 ),
        ADMINISTRATIONPUNISH_CHARGEPERSON_AUDIT("administrationPunish_chargePerson_audit" ,3004 ),
        ADMINISTRATIONPUNISH_DECISION("administrationPunish_decision" ,3005 ),
        ADMINISTRATIONPUNISH_SEND("administrationPunish_send" ,3006 ),
        ADMINISTRATIONPUNISH_EXECUTE("administrationPunish_execute" ,3007 ),

        /* 结案 */
        CASECLOSE_APPLY("caseClose_apply" ,4000 ),
        CASECLOSE_DEPARTMENT_AUDIT("caseClose_department_audit" ,4001 ),
        CASECLOSE_LEGAL_AUDIT("caseClose_legal_audit" ,4002 ),
        CASECLOSE_CHARGEPERSON_AUDIT("caseClose_chargePerson_audit" ,4003 ),

        /* 非业务节点 */
        START("start" ,100 ),
        END("end" ,200 ),
        TERMINATE("terminate" ,201 ),
        REVOKE("revoke" ,202 ),
        SUSPEND("suspend" ,300 ),
        DELAY("delay" ,301 ),

        ;

        public final String id;
        public final Integer code;

        NodeEnum(String id, Integer code) {
            this.id = id;
            this.code = code;
        }

        public static NodeEnum getEnum(Integer code) {
            for (NodeEnum node : NodeEnum.values())
                if (node.code.intValue() == code.intValue()) return node;
            return null;
        }

        public static NodeEnum getEnum(String id) {
            for (NodeEnum node : NodeEnum.values())
                if (node.id.equalsIgnoreCase(id)) return node;
            return null;
        }
    }

    // /**
    //  * <p>
    //  * 仅使用在行政执法工作流相关的接口
    //  * 放在controller的PostMapping里面的params, 作为请求的参数<br>
    //  * 使用节点配置生成的数据
    //  * </p>
    //  */
    // @Getter
    // public static class RequestEnum {
    //     /* 固定的格式, 可以动态修改*/
    //     public final static String PREFIX = "method=whately.jnybes.case.inprocess.";
    //     /* 以下为根据节点配置生成的常量 */
    //     public final static String START=PREFIX+"100";
    //     public final static String END=PREFIX+"200";
    //     public final static String TERMINATE=PREFIX+"201";
    //     public final static String REVOKE=PREFIX+"202";
    //     public final static String SUSPEND=PREFIX+"300";
    //     public final static String DELAY=PREFIX+"301";
    // }

    /**
     * <p>
     * 操作静态常量</br>
     * 对应实际操作
     * </p>
     */
    public enum ConditionEnum {
        PASS("pass", "审核通过"),
        // SUBMIT("submit", "提交"),
        PUNISH("punish", "处罚"),
        NOPUNISH("nopunish", "不处罚"),
        ;
        public final String value;
        public final String desc;

        ConditionEnum(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static ConditionEnum getEnum(String value) {
            for (ConditionEnum condition : ConditionEnum.values())
                if (condition.value.equalsIgnoreCase(value)) return condition;
            return null;
        }
    }

    /**
     * <p>
     * 操作类型静态常量</br>
     * </p>
     */
    public enum conditionTypeEnum {
        INPROCESS("inprocess", "流程内"),
        OUTPROCESS("outprocess", "流程外"),
        ;
        public final String value;
        public final String desc;

        conditionTypeEnum(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static conditionTypeEnum getEnum(String value) {
            for (conditionTypeEnum conditionType : conditionTypeEnum.values())
                if (conditionType.value.equalsIgnoreCase(value)) return conditionType;
            return null;
        }
    }

    /**
     * <p>
     * 操作状态 静态常量</br>
     * 对应实际操作
     * </p>
     */
    public enum NodeStageEnum {
        START(0, "开始"),
        PROCESS(1, "进行中"),
        END(3, "已结束"),

        ;
        public final Integer value;
        public final String desc;

        NodeStageEnum(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static NodeStageEnum getEnum(Integer value) {
            for (NodeStageEnum nodeStage : NodeStageEnum.values())
                if (nodeStage.value.intValue() == value.intValue()) return nodeStage;
            return null;
        }
    }

    /**
     * <p>
     * 操作静态常量</br>
     * 对应实际操作
     * </p>
     */
    public enum NodeTypeEnum {
        START_EVENT("startEvent", "开始"),
        END_EVENT("endEvent", "结束"),
        SUSPEND_EVENT("suspendEvent", "暂停"),
        DELAY_EVENT("delayEvent", "延期"),
        USER_TASK("userTask", "用户任务"),

        ;
        public final String value;
        public final String desc;

        NodeTypeEnum(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static NodeTypeEnum getEnum(String value) {
            for (NodeTypeEnum nodeType : NodeTypeEnum.values())
                if (nodeType.value.equalsIgnoreCase(value)) return nodeType;
            return null;
        }
    }

    public void print() {
        // // 获取当前节点
        // System.out.println(this.getNode(NodeEnum.FILINGCASE_DEPARTMENT_SUBMIT));
        //
        // // 获取所有可回滚节点
        // System.out.println(this.fallbackNodes());
        //
        // // 获取当前节点的可回滚节点
        // System.out.println(this.fallbackNodes(NodeEnum.FILINGCASE_LEGAL_AUDIT));
        //
        // // 获取当前节点下一节点
        // System.out.println(this.nextNode(NodeEnum.FILINGCASE_DEPARTMENT_SUBMIT, ConditionEnum.PASS));
        //
        // // 获取下一节点说明 完成任务时 保存下一节点信息填充用
        // System.out.println(this.nextNodeDesc(NodeEnum.FILINGCASE_DEPARTMENT_AUDIT));

        // 打印所有节点id和code 枚举定义
        nodes.forEach(node -> System.out.println(node.getNodeId().toUpperCase() + "(\"" + node.getNodeId() + "\" ," + node.getNodeCode() + " ),"));

        // // 打印静态常量定义 如: public final static String XXX_YYY=PREFIX+"1000";
        // nodes.forEach(node -> System.out.println("public final static String " + node.getNodeId().toUpperCase() + "=PREFIX+" + "\"" + node.getNodeCode() + "\";"));
    }


}
