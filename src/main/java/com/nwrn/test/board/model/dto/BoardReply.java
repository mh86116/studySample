package com.nwrn.test.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardReply {
    private Long replyNo;
    private int boardNo;
    private String body;
    private String order;
    private boolean status;
}
