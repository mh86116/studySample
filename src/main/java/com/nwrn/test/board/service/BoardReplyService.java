package com.nwrn.test.board.service;

import com.nwrn.test.board.model.dto.BoardReplyDTO;
import com.nwrn.test.board.model.entity.BoardReply;

import java.util.List;

public interface BoardReplyService {
    //전체조회
    List<BoardReply> replies(BoardReplyDTO dto);
    //단건조회
    BoardReply getReply(Long id);
    //작성
    String insertReply(BoardReplyDTO dto, Long boardNo, Long memberNo);
    //수정
    String updateReply(BoardReplyDTO dto, Long id);
    //삭제
    String deleteReply(Long id);

}
