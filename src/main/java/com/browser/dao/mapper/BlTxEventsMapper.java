package com.browser.dao.mapper;

import com.browser.dao.entity.BlTxEvents;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlTxEventsMapper {
    BlTxEvents selectByPrimaryKey(@Param("id") Long id);
    List<BlTxEvents> selectAllByCond(BlTxEvents cond);
    int insert(BlTxEvents record);
}
