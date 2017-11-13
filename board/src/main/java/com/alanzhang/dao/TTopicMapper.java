package com.alanzhang.dao;

import com.alanzhang.domain.TTopic;

public interface TTopicMapper {
    int deleteByPrimaryKey(String topicId);

    int insert(TTopic record);

    int insertSelective(TTopic record);

    TTopic selectByPrimaryKey(String topicId);

    int updateByPrimaryKeySelective(TTopic record);

    int updateByPrimaryKey(TTopic record);
}