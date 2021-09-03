package com.nwrn.test.board.service;

import com.nwrn.test.board.model.dto.BoardDTO;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.member.model.entity.Member;

import java.util.List;

public interface BoardService {
    //전체조회
    List<Board> getBoards(BoardDTO dto);
    //등록
    String insertBoard(BoardDTO dto, Member member);
    //수정
    String updateBoard(BoardDTO dto, Long id);
    //삭제
    String deleteBoard(Long id);
}
