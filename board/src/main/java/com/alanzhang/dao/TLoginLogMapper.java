package com.alanzhang.dao;

import com.alanzhang.domain.TLoginLog;

public interface TLoginLogMapper {
    int deleteByPrimaryKey(String loginLogId);

    int insert(TLoginLog record);

    int insertSelective(TLoginLog record);

    TLoginLog selectByPrimaryKey(String loginLogId);

    int updateByPrimaryKeySelective(TLoginLog record);

    int updateByPrimaryKey(TLoginLog record);
}