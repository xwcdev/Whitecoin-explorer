package com.browser.service.impl;

import com.browser.dao.entity.BlToken;
import com.browser.dao.entity.BlTokenTransaction;
import com.browser.dao.mapper.BlTokenMapper;
import com.browser.protocol.EUDataGridResult;
import com.browser.service.TokenService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    @Resource
    private BlTokenMapper blTokenMapper;

    @Override
    public List<BlToken> selectAllNotActive() {
        return blTokenMapper.selectAllNotActive();
    }

    @Override
    public EUDataGridResult getActiveTokenList(BlToken token) {
        // 分页处理
        PageHelper.startPage(token.getPage(), token.getRows());
        List<BlToken> list = blTokenMapper.getActiveTokenList(token);
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取记录总条数
        PageInfo<BlToken> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        result.setPages(pageInfo.getPages());
        return result;
    }

    @Override
    public BlToken selectByContractAddress(String contractAddress) {
        return blTokenMapper.selectByContractAddress(contractAddress);
    }
}
