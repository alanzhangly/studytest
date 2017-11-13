package com.alanzhang.dao;

import com.alanzhang.domain.TPost;

public interface TPostMapper {
    int deleteByPrimaryKey(String postId);

    int insert(TPost record);

    int insertSelective(TPost record);

    TPost selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(TPost record);

    int updateByPrimaryKeyWithBLOBs(TPost record);

    int updateByPrimaryKey(TPost record);
}