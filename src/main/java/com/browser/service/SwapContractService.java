package com.browser.service;

import com.browser.dao.entity.BlSwapContract;

import java.util.List;

public interface SwapContractService {
    List<BlSwapContract> selectAllActive();
}
