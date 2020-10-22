package com.browser.dao.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BlToken {
    private Long id;
    private String contractAddress;
    private String tokenSymbol;
    private Integer precision;
    private String creatorAddress;
    private String createTrxId;
    private BigDecimal tokenSupply;
    private Integer topSort;

    // 暂存的查询参数
    private Integer page;
    private Integer rows;
}
