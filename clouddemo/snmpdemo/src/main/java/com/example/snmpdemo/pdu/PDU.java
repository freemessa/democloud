package com.example.snmpdemo.pdu;

import com.example.snmpdemo.pdu.type.Type;


/**
 * Get类型的PDU
 *
 * @author : demo
 * @version : 1.0
 * @since : 2022/11/1 14:29
 */
public class PDU {

    /**
     * PDU类型(PDU type)：区分PDU的类型。
     */
    private String pduType;
    /**
     * 请求标识(Request ID)：SNMP 给每个请求分配全局唯一的 ID，用于匹配请求和
     * 响应。请求标识的另一个作用是检测由不可靠的传输服务产生的重复报文。
     */
    private String requestId;
    /**
     * 错误状态(Error status)：用于表示在处理请求时出现的状况，共有 6 种错误状态：
     * noError(0)、tooBig(1)、noSuchName(2)、badValue(3)、readOnly(4)、genError(5)
     */
    private String errorStatus;
    /**
     * 错误索引(Error index)：当错误状态非 0 时指向出错的变量。
     */
    private String errorIndex;
    /**
     * 变量绑定表(Variable bindings)：变量绑定列表，由变量名和变量值对组成。
     * 在检索请求报文中，变量的值应为 0。
     */
    private VariableBindings variableBindings;

    @Override
    public String toString() {
        return pduType +
                "\n   " + pduType +
                "\n     request-id : " + requestId +
                "\n     error-status : " + errorStatus +
                "\n     error-index : " + errorIndex +
                "\n     variable-bindings : " + variableBindings;
    }

    public PDU() {
        this.pduType = Type.PDUTYPE[2];
        this.errorStatus = Type.STATUS[0];
        this.errorIndex = "0";
    }

    public PDU(int pduType, String requestId, VariableBindings variableBinding) {
        this.pduType = Type.PDUTYPE[pduType];
        this.requestId = requestId;
        this.errorStatus = Type.STATUS[0];
        this.errorIndex = "0";
        this.variableBindings = variableBinding;
    }

    public PDU(int pduType, String requestId, int errorStatus, String errorIndex, VariableBindings variableBindings) {
        this.pduType = Type.PDUTYPE[pduType];
        this.requestId = requestId;
        this.errorStatus = Type.STATUS[errorStatus];
        this.errorIndex = errorIndex;
        this.variableBindings = variableBindings;
    }

    public String getPduType() {
        return pduType;
    }

    public void setPduType(int pduType) {
        this.pduType = Type.PDUTYPE[pduType];
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getVariableBindings() {
        return variableBindings;
    }

    public void setVariableBindings(VariableBindings variableBinding) {
        this.variableBindings = variableBinding;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(int errorStatus) {
        this.errorStatus = Type.STATUS[errorStatus];
    }

    public String getErrorIndex() {
        return errorIndex;
    }

    public void setErrorIndex(String errorIndex) {
        this.errorIndex = errorIndex;
    }
}
