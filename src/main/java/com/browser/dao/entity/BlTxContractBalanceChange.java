package com.browser.dao.entity;

public class BlTxContractBalanceChange {
    private Long id;
    private String trxId;
    private Long blockNum;
    private String changeType;
    private String address;
    private String assetId;
    private Long amount;
    private Integer changeSeq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public Long getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(Long blockNum) {
        this.blockNum = blockNum;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getChangeSeq() {
        return changeSeq;
    }

    public void setChangeSeq(Integer changeSeq) {
        this.changeSeq = changeSeq;
    }
}
