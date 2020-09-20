package com.browser.service;

import com.browser.dao.entity.BlToken;
import com.browser.protocol.EUDataGridResult;

import java.util.List;

public interface TokenService {
    List<BlToken> selectAllNotActive();
    EUDataGridResult getActiveTokenList(BlToken token);
    BlToken selectByContractAddress(String contractAddress);
}
