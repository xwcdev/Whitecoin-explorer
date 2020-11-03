package com.browser.dao.mapper;

import com.browser.dao.entity.BlTokenBalance;

import java.util.List;

public interface BlTokenBalanceMapper {
    int insert(BlTokenBalance record);
    BlTokenBalance selectByAddrAndTokenContract(BlTokenBalance cond);
    List<BlTokenBalance> selectAllByAddr(BlTokenBalance cond);
    List<BlTokenBalance> selectAllByTokenContract(BlTokenBalance cond);
    int updateByPrimaryKeySelective(BlTokenBalance record);
    int updateByPrimaryKey(BlTokenBalance record);
}
