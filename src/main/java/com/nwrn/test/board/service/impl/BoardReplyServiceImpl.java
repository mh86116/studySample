package com.nwrn.test.board.service.impl;

import com.nwrn.test.board.BoardReplyController;
import com.nwrn.test.board.model.dto.BoardReplyDTO;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.board.model.entity.BoardReply;
import com.nwrn.test.board.repository.BoardReplyRepository;
import com.nwrn.test.board.repository.BoardRepository;
import com.nwrn.test.board.service.BoardReplyService;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService {

    private final BoardReplyRepository boardReplyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<BoardReply> replies(BoardReplyDTO dto) {
        return boardReplyRepository.findAll();
    }

    @Override
    public BoardReply getReply(Long id) {
        return boardReplyRepository.getById(id);
    }

    @Override
    public String insertReply(BoardReplyDTO dto, Long boardNo, Long memberNo) {
        Board board = boardRepository.getById(boardNo);

        Member member = memberRepository.getById(memberNo);

        BoardReply boardReply = dto.toEntity(board, member);
        boardReplyRepository.save(boardReply);

        return "ok";
    }

    @Override
    public String updateReply(BoardReplyDTO dto, Long id) {
        BoardReply reply = boardReplyRepository.getById(id);

        reply.updateReply(dto.getBody());
        boardReplyRepository.save(reply);
        return "ok";
    }

    @Override
    public String deleteReply(Long id) {
        boardReplyRepository.deleteById(id);
        return null;
    }
}
