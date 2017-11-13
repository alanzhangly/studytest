package com.alanzhang.domain;

import java.io.Serializable;

/**
 * @author 
 */
public class TBoard implements Serializable {

    private String boardId;

    private String boardName;

    private String boardDesc;

    private Integer topicNum;

    private static final long serialVersionUID = 1L;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public Integer getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum) {
        this.topicNum = topicNum;
    }
}