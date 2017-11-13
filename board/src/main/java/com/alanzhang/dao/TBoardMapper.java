package com.alanzhang.dao;

import com.alanzhang.domain.TBoard;

public interface TBoardMapper {
    int deleteByPrimaryKey(String boardId);

    int insert(TBoard record);

    int insertSelective(TBoard record);

    TBoard selectByPrimaryKey(String boardId);

    int updateByPrimaryKeySelective(TBoard record);

    int updateByPrimaryKey(TBoard record);
}