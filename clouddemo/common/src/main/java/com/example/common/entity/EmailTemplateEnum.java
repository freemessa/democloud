package com.example.common.entity;

public enum EmailTemplateEnum {
    INFO_COLLECTION_EMAIL("infoCollection.ftl"),
    PROJ_REVENUE_WARN("ProjRevenueWarn.ftl"),
    PROJ_DELAY_REMIND("ProjDelayRemind.ftl"),
    PROJ_COST_WARN("ProjCostWarn.ftl"),
    PUBLIC_PERFOR("PublicPerfor.ftl"),
    PUBLIC_HW_PERFOR("PublicHwPerfor.ftl"),
    ENTRY_APPROVAL("EntryApproval.ftl"),
    ENTRY_APPROVAL_FAILED("EntryApprovalFailed.ftl"),
    RMS_POST_MATCH("RmsPostMatch.ftl"),
    PROJ_REMIND("ProjRemind.ftl"),
    COMMENDATION("Commendation.ftl");

    private final String templateName;

    EmailTemplateEnum(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }
}
