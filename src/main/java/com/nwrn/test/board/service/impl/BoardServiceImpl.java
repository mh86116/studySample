package com.nwrn.test.board.service.impl;

import com.nwrn.test.board.model.dto.BoardDTO;
import com.nwrn.test.board.model.dto.BoardReplyDTO;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.board.repository.BoardRepository;
import com.nwrn.test.board.service.BoardService;
import com.nwrn.test.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    
    private final BoardRepository boardRepository;

    /********************************************************************************************
     * 게시판
     *******************************************************************************************/
    @Override
    public List<Board> getBoards(BoardDTO dto) {
        return boardRepository.findAll();
    }

    @Override
    public String insertBoard(BoardDTO dto, Member member) {
        Board board = dto.toEntity(member);
        boardRepository.save(board);
        return "ok";
    }

    @Override
    public String updateBoard(BoardDTO dto, Long id) {
        validateBoard(dto);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        board.updateBoard(dto.getTitle(), dto.getBody(), dto.getCategory());
        return "ok";
    }

    @Override
    public String deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return "ok";
    }



    /****************************************************************************
     * 공통 로직
     ***************************************************************************/
    private void validateBoard(BoardDTO dto) {
        boardRepository.findById(dto.getBoardNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }
}
