package com.nwrn.test.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardReReplyDTO {
    private Long reReplyNo;
    private int boardNo;
    private int replyNo;
    private String body;
    private int writeMemberNo;
    private boolean status;
}
