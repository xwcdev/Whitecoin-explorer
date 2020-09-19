package com.browser.service.swapEventPlugins.impl;

import com.browser.dao.entity.BlSwapContract;
import com.browser.dao.entity.BlSwapTransaction;
import com.browser.dao.entity.BlTransaction;
import com.browser.service.swapEventPlugins.ISwapEventPlugin;
import com.browser.wallet.beans.TxReceiptEvent;
import org.springframework.stereotype.Component;

@Component("swapPlugin.LiquidityAdded")
public class LiquidityAddedSwapEventPlugin implements ISwapEventPlugin {
    @Override
    public String eventName() {
        return "LiquidityAdded";
    }

    @Override
    public BlSwapTransaction decodeSwapEvent(BlSwapContract swapContract, TxReceiptEvent receiptEvent, int eventSeq, String txId, BlTransaction trx, String blockId) {
        // TODO
        return null;
    }
}
