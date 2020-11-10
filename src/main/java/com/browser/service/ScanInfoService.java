package com.browser.service;

import com.browser.dao.entity.BlScanInfo;

public interface ScanInfoService {
    Long queryBlockNum();
    int updateOrInsertBlockNum(long blockNum);
}
