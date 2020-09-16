package com.browser.service.impl;

import com.browser.dao.entity.BlTxEvents;
import com.browser.dao.mapper.BlTxEventsMapper;
import com.browser.service.TxEventsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class TxEventsServiceImpl implements TxEventsService {
    @Resource
    private BlTxEventsMapper txEventsMapper;

    @Override
    public int insert(BlTxEvents record) {
        return txEventsMapper.insert(record);
    }

    @Override
    public BlTxEvents selectByTrxIdAndEventSeq(String trxId, int eventSeq) {
        if(trxId == null) {
            return null;
        }
        BlTxEvents cond = new BlTxEvents();
        cond.setTrxId(trxId);
        cond.setEventSeq(eventSeq);
        List<BlTxEvents> events = txEventsMapper.selectAllByCond(cond);
        if(events.isEmpty()) {
            return null;
        } else {
            return events.get(0);
        }
    }

    @Override
    public List<BlTxEvents> selectAllByTrxId(String trxId) {
        if(trxId == null) {
            return Collections.emptyList();
        }
        BlTxEvents cond = new BlTxEvents();
        cond.setTrxId(trxId);
        return txEventsMapper.selectAllByCond(cond);
    }
}
