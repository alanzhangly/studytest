package com.alanzhang.dao;

import com.alanzhang.domain.TBoardManagerKey;

public interface TBoardManagerMapper {
    int deleteByPrimaryKey(TBoardManagerKey key);

    int insert(TBoardManagerKey record);

    int insertSelective(TBoardManagerKey record);
}