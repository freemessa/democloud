package com.example.common.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class MailInfo {
    /**
     * 操作此邮件用户
     */
    @ApiModelProperty(value = "操作此邮件用户")
    private String opUserId;

    /**
     * 收件人邮箱
     */
    @ApiModelProperty(value = "收件人邮箱")
    @NotBlank(message = "收件人邮箱不能为空")
    private String toEmail;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    @NotBlank(message = "邮件主题不能为空")
    private String subject;

    /**
     * 邮件正文
     */
    @ApiModelProperty(value = "邮件正文")
    private String text;

    /**
     * 邮件模板名，使用EmailTemplate常量
     */
    @ApiModelProperty(value = "邮件模板")
    private EmailTemplateEnum emailTemplateEnum;

    /**
     * 邮件模板内容
     */
    @ApiModelProperty(value = "邮件模板内容")
    private List<? extends Object> contentList;

    /**
     * 附件地址
     */
    @ApiModelProperty(value = "附件地址")
    private List<String> fileUrlList;

    /**
     * 抄送
     */
    @ApiModelProperty(value = "抄送")
    private String cc;

    /**
     * 密送
     */
    @ApiModelProperty(value = "密送")
    private String bcc;

    /**
     * 是否同步发送
     */
    @ApiModelProperty(value = "同步")
    private Boolean sync = false;

    private String modelName; // 模块名称

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }


    private static final long serialVersionUID = 1L;

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }


    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<? extends Object> getContentList() {
        return contentList;
    }

    public void setContentList(List<? extends Object> contentList) {
        this.contentList = contentList;
    }

    public List<String> getFileUrlList() {
        return fileUrlList;
    }

    public void setFileUrlList(List<String> fileUrlList) {
        this.fileUrlList = fileUrlList;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public EmailTemplateEnum getEmailTemplateEnum() {
        return emailTemplateEnum;
    }

    public void setEmailTemplateEnum(EmailTemplateEnum emailTemplateEnum) {
        this.emailTemplateEnum = emailTemplateEnum;
    }

    public MailInfo() {
    }

    public MailInfo(@NotBlank(message = "收件人邮箱不能为空") String toEmail,
                    @NotBlank(message = "邮件主题不能为空") String subject,
                    String text, EmailTemplateEnum emailTemplateEnum, List<? extends Object> contentList) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.text = text;
        this.emailTemplateEnum = emailTemplateEnum;
        this.contentList = contentList;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
