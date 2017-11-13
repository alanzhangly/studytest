package com.alanzhang.domain;

import java.io.Serializable;

/**
 * @author 
 */
public class TBoardManagerKey implements Serializable {
    private String boardId;

    private String userId;

    private static final long serialVersionUID = 1L;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}