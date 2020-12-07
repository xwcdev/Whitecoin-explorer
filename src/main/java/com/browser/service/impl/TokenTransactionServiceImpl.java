package com.browser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.browser.dao.entity.*;
import com.browser.dao.mapper.BlTokenMapper;
import com.browser.dao.mapper.BlTokenTransactionMapper;
import com.browser.protocol.EUDataGridResult;
import com.browser.service.TokenTransactionService;
import com.browser.tools.Constant;
import com.browser.tools.common.StringUtil;
import com.browser.wallet.PrecisionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class TokenTransactionServiceImpl implements TokenTransactionService {
    @Resource
    private BlTokenTransactionMapper blTokenTransactionMapper;
    @Resource
    private BlTokenMapper blTokenMapper;

    @Override
    public int insert(BlTokenTransaction record) {
        return blTokenTransactionMapper.insert(record);
    }

    @Override
    public BlTokenTransaction selectByTrxIdAndEventSeq(String trxId, int eventSeq) {
        BlTokenTransaction cond = new BlTokenTransaction();
        cond.setTrxId(trxId);
        cond.setEventSeq(eventSeq);
        List<BlTokenTransaction> records = blTokenTransactionMapper.selectAllByCond(cond);
        if(records.isEmpty()) {
            return null;
        }
        handleAmountData(records.get(0));
        return records.get(0);
    }


    private void handleAmountData(BlTokenTransaction trx) {

        String contractId = trx.getContractId();
        if(StringUtil.isEmpty(contractId)) {
            return;
        }
        BlToken token = blTokenMapper.selectByContractAddress(contractId);
        if(token == null || token.getTokenSymbol()==null || token.getPrecision()==null) {
            return;
        }
        trx.setTokenInfo(token);
        BigDecimal precision = PrecisionUtils.decimalsToPrecision(token.getPrecision());
        if(trx.getAmount()!=null) {
            trx.setAmountStr(new BigDecimal(trx.getAmount()).setScale(token.getPrecision(), RoundingMode.FLOOR).divide(precision, RoundingMode.FLOOR).toString());
        }
        if(trx.getFee()!=null) {
            trx.setFeeStr(new BigDecimal(trx.getFee()).setScale(token.getPrecision(), RoundingMode.FLOOR).divide(precision, RoundingMode.FLOOR).toString());
        }
    }


    @Override
    public EUDataGridResult getTokenTransactionList(BlTokenTransaction transaction) {
        // 分页处理
        PageHelper.startPage(transaction.getPage(), transaction.getRows());
        List<BlTokenTransaction> list = blTokenTransactionMapper.selectAllByCond(transaction);
        if (list != null && list.size() > 0) {
            for (BlTokenTransaction trx : list) {
                handleAmountData(trx);
            }
        }
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取记录总条数
        PageInfo<BlTokenTransaction> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setPages(pageInfo.getPages());
        return result;
    }

    @Override
    public EUDataGridResult selectListByUserAddress(BlTokenTransaction transaction) {
        // 分页处理
        PageHelper.startPage(transaction.getPage(), transaction.getRows());
        List<BlTokenTransaction> list = blTokenTransactionMapper.selectListByUserAddress(transaction);
        if (list != null && list.size() > 0) {
            for (BlTokenTransaction trx : list) {
                handleAmountData(trx);
            }
        }
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取记录总条数
        PageInfo<BlTokenTransaction> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setPages(pageInfo.getPages());
        return result;
    }

    @Override
    public EUDataGridResult selectTrxList(BlTokenTransaction transaction) {
        // 分页处理
        PageHelper.startPage(transaction.getPage(), transaction.getRows());
        List<BlTokenTransaction> list = blTokenTransactionMapper.selectTrxList(transaction);
        if (list != null && list.size() > 0) {
            for (BlTokenTransaction trx : list) {
                handleAmountData(trx);
            }
        }
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取记录总条数
        PageInfo<BlTokenTransaction> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setPages(pageInfo.getPages());
        return result;
    }

    @Override
    public List<BlTokenTransaction> selectAllByTrxId(String trxId) {
        BlTokenTransaction cond = new BlTokenTransaction();
        cond.setTrxId(trxId);
        List<BlTokenTransaction> list = blTokenTransactionMapper.selectAllByCond(cond);
        if (list != null && list.size() > 0) {
            for (BlTokenTransaction trx : list) {
                handleAmountData(trx);
            }
        }
        return list;
    }
}
