package com.nwrn.test.board.model.dto;

import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.board.model.entity.BoardReply;
import com.nwrn.test.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardReplyDTO {
    private Long replyNo;
    private int boardNo;
    private String body;
    private String writer;
    private boolean status;

    public BoardReplyDTO(BoardReply reply) {
        this.replyNo = reply.getReplyNo();
        this.boardNo = Math.toIntExact(reply.getBoardNo().getBoardNo());
        this.body = reply.getReplyBody();
        this.writer = reply.getWriter().getName();
    }

    public BoardReply toEntity(Board board, Member member) {
        BoardReply.BoardReplyBuilder builder = BoardReply.builder();

        return builder
                .boardNo(board)
                .replyBody(this.body)
                .writer(member)
                .status(true)
                .build();
    }
}
