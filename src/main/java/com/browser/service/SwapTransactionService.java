package com.browser.service;

import com.browser.dao.entity.BlSwapTransaction;

public interface SwapTransactionService {
    BlSwapTransaction selectByTrxIdAndOpNumAndEventSeq(String trxId, int opNum, int eventSeq);
    int insert(BlSwapTransaction record);
}
