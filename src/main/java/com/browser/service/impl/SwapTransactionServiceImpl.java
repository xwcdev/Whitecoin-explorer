package com.browser.service.impl;

import com.browser.dao.entity.BlSwapTransaction;
import com.browser.dao.mapper.BlSwapTransactionMapper;
import com.browser.service.SwapTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SwapTransactionServiceImpl implements SwapTransactionService {
    @Resource
    private BlSwapTransactionMapper blSwapTransactionMapper;

    @Override
    public BlSwapTransaction selectByTrxIdAndOpNumAndEventSeq(String trxId, int opNum, int eventSeq) {
        BlSwapTransaction cond = new BlSwapTransaction();
        cond.setTrxId(trxId);
        cond.setOpNum(opNum);
        cond.setEventSeq(eventSeq);
        List<BlSwapTransaction> records = blSwapTransactionMapper.selectAllByCond(cond);
        if(records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public int insert(BlSwapTransaction record) {
        return blSwapTransactionMapper.insert(record);
    }
}
