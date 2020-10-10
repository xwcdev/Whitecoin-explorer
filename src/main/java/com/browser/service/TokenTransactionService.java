package com.browser.service;

import com.browser.dao.entity.BlTokenTransaction;
import com.browser.dao.entity.BlTransaction;
import com.browser.protocol.EUDataGridResult;

import java.util.List;

public interface TokenTransactionService {
    int insert(BlTokenTransaction record);
    BlTokenTransaction selectByTrxIdAndEventSeq(String trxId, int eventSeq);
    EUDataGridResult getTokenTransactionList(BlTokenTransaction transaction);
    EUDataGridResult selectListByUserAddress(BlTokenTransaction transaction);
    List<BlTokenTransaction> selectAllByTrxId(String trxId);
}
