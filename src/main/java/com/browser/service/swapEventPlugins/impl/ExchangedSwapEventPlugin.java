package com.browser.service.swapEventPlugins.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.browser.dao.entity.BlSwapContract;
import com.browser.dao.entity.BlSwapTransaction;
import com.browser.dao.entity.BlTransaction;
import com.browser.service.swapEventPlugins.ISwapEventPlugin;
import com.browser.wallet.beans.TxReceiptEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("swapPlugin.Exchanged")
public class ExchangedSwapEventPlugin implements ISwapEventPlugin {
    @Override
    public String eventName() {
        return "Exchanged";
    }

    @Override
    public BlSwapTransaction decodeSwapEvent(BlSwapContract swapContract, TxReceiptEvent receiptEvent, int eventSeq, String txId, BlTransaction trx, String blockId) {
        try {
            JSONObject argJson = JSON.parseObject(receiptEvent.getEvent_arg());
            BigDecimal fee = argJson.getBigDecimal("fee");
            String addr = argJson.getString("addr");
            String buyAsset = argJson.getString("buy_asset");
            BigDecimal buyAmount = argJson.getBigDecimal("buy_amount");
            String sellAsset = argJson.getString("sell_asset");
            BigDecimal sellAmount = argJson.getBigDecimal("sell_amount");

            if(addr == null || buyAsset == null || buyAmount == null || sellAsset == null || sellAmount == null) {
                log.error("invalid {} event arg {}", eventName(), receiptEvent.getEvent_arg());
                return null;
            }

            BlSwapTransaction swapTx = createBaseSwapTransaction(swapContract, receiptEvent, eventSeq, txId, trx, blockId);
            swapTx.setChangeAddress(addr);
            swapTx.setBuyAsset(buyAsset);
            swapTx.setBuyAmount(buyAmount);
            swapTx.setSellAsset(sellAsset);
            swapTx.setSellAmount(sellAmount);
            swapTx.setExchangeFee(fee);
            return swapTx;
        } catch(Exception e) {
            log.error("decodeSwapEvent error", e);
            return null;
        }
    }
}
