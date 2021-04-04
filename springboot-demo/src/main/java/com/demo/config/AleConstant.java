package com.demo.config;

/**
 * @author liuhoujie
 * @date 2020/10/20
 */
public class AleConstant {

    /**
     * <p>
     * 节点操作类型枚举</br>
     * 对应实际操作
     * </p>
     */
    public enum OperateTypeEnum {
        AUDIT("audit", "审核"),
        SUBMIT("submit", "提交"),
        NOP("nop","无"),
        ;
        public final String value;
        public final String desc;

        OperateTypeEnum(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static OperateTypeEnum getEnum(String value) {
            for (OperateTypeEnum nodeOperateTypeEnum : OperateTypeEnum.values())
                if (nodeOperateTypeEnum.value.equalsIgnoreCase(value)) return nodeOperateTypeEnum;
            return null;
        }
    }

    /**
     * <p>
     *     行政处罚流程 审核结果枚举
     * </p>
     * @author liuhoujie
     * @date 2020/10/20
     */
    public enum AuditTypeEnum {
        PASS(1,"同意"),
        BACKOFF(2,"同意"),
        ADJUST(3,"流程调整"),
        ;

        public final Integer value;
        public final String desc;

        AuditTypeEnum(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static AuditTypeEnum getEnum(Integer value) {
            for (AuditTypeEnum auditTypeEnum : AuditTypeEnum.values())
                if (auditTypeEnum.value.intValue() == value.intValue()) return auditTypeEnum;
            return null;
        }
    }

    public enum CaseTypeEnum {
        ALL("all", "所有案件"),
        UNFILING("unfiling", "未立案案件"),
        TODEAL("todeal", "待办案件"),
        END("end", "已结案件"),
        TERMINATE("terminate", "终止案件"),
        REVOKE("revoke", "撤销案件"),
        DELAY("delay", "延期案件"),
        SUSPEND("suspend", "中止案件"),
        ;

        public final String value;
        public final String desc;

        CaseTypeEnum(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        CaseTypeEnum getEnum(String value) {
            for (CaseTypeEnum caseTypeEnum : CaseTypeEnum.values())
                if (caseTypeEnum.value.equalsIgnoreCase(value)) return caseTypeEnum;
            return null;
        }

    }
}
