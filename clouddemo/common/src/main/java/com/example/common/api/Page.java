package com.example.common.api;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class Page<T> {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";

    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;
    private String order;

    T param;

    public int getPageNum() {
        return pageNum != null && pageNum > 0 ? pageNum: 1;
    }

    public int getPageSize() {
        return pageSize !=null && pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public String getOrderBy() {
        return StrUtil.toUnderlineCase(orderBy);
    }

    public String getOrder() {
        return StringUtils.isBlank(this.order) || StringUtils.equals(this.order, ORDER_ASC) ? ORDER_ASC : ORDER_DESC;
    }

    public String getSort() {
        if (StringUtils.isNotEmpty(orderBy)) {
            return StrUtil.format("{} {}", getOrderBy(), getOrder());
        }
        return null;
    }
}
