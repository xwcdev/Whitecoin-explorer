package com.browser.service;

import com.browser.dao.entity.BlTxEvents;

import java.util.List;

public interface TxEventsService {
    int insert(BlTxEvents record);
    BlTxEvents selectByTrxIdAndEventSeq(String trxId, int eventSeq);
    List<BlTxEvents> selectAllByTrxId(String trxId);
}
