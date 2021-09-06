package com.nwrn.test.board;

import com.nwrn.test.board.model.dto.BoardReplyDTO;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.board.model.entity.BoardReply;
import com.nwrn.test.board.model.enums.CategoryType;
import com.nwrn.test.board.repository.BoardReplyRepository;
import com.nwrn.test.board.repository.BoardRepository;
import com.nwrn.test.board.service.BoardReplyService;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class BoardReplyControllerTest {

    @Autowired
    BoardReplyService boardReplyService;
    @Autowired
    BoardReplyRepository boardReplyRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        Member member1 = new Member();
        member1 = new Member(null, "memberA");
        Member member2 = new Member();
        member2 = new Member(null, "memberB");
        Member member3 = new Member();
        member3 = new Member(null, "memberC");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Board board1 = Board.builder()
                .boardNo(null)
                .category(CategoryType.NOTICE)
                .title("게시글1")
                .body("내용무")
                .writer(member1)
                .status(true)
                .build();
        Board board2 = Board.builder()
                .boardNo(null)
                .category(CategoryType.NOTICE)
                .title("게시글2")
                .body("내용무")
                .writer(member2)
                .status(true)
                .build();
        Board board3 = Board.builder()
                .boardNo(null)
                .category(CategoryType.NOTICE)
                .title("게시글3")
                .body("내용무")
                .writer(member3)
                .status(true)
                .build();
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);

        BoardReply reply1 = BoardReply.builder()
                .replyNo(null)
                .boardNo(board3)
                .replyBody("정보 감사합니다.1")
                .writer(member1)
                .status(true)
                .build();
        boardReplyRepository.save(reply1);

        BoardReply reply2 = BoardReply.builder()
                .replyNo(null)
                .boardNo(board1)
                .replyBody("정보 감사합니다.2")
                .writer(member2)
                .status(true)
                .build();
        boardReplyRepository.save(reply2);
    }

    @Test
    void replies() {
        List<BoardReply> dto = boardReplyRepository.findAll();

        assertEquals(2, dto.size());
        assertNotNull(dto.get(1).getWriter().getMemberNo());
        assertNotNull(dto.get(1).getBoardNo().getBoardNo());
    }

    @Test
    void getReply() {
        List<BoardReply> dto = boardReplyRepository.findAll();
        BoardReply reply = boardReplyRepository.getById(dto.get(0).getReplyNo());
        assertEquals(reply, dto.get(0));
    }

    @Test
    void insertReply() {
        List<Member> members = memberRepository.findAll();
        List<Board> boards = boardRepository.findAll();

        BoardReply reply3 = BoardReply.builder()
                .replyNo(null)
                .boardNo(boardRepository.getById(boards.get(0).getBoardNo()))
                .replyBody("정보 무지무지 감사합니다.")
                .writer(members.get(1))
                .status(true)
                .build();
        boardReplyRepository.save(reply3);

        BoardReplyDTO replyDTO = new BoardReplyDTO(reply3);

        assertEquals(replyDTO.getReplyNo(), reply3.getReplyNo());
    }

    @Test
    void updateReply() {
        List<BoardReply> dto = boardReplyRepository.findAll();
        BoardReply reply = dto.get(1);

        BoardReplyDTO replyDTO = new BoardReplyDTO(reply);

        Long no = dto.get(1).getReplyNo();

        boardReplyService.updateReply(replyDTO, no);

        assertEquals(replyDTO.getBody(), reply.getReplyBody());
    }

    @Test
    void deleteReply() {
        List<BoardReply> dto = boardReplyRepository.findAll();

        Long reply = dto.get(0).getReplyNo();

        boardReplyService.deleteReply(reply);

        boardReplyRepository.findById(reply)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        List<BoardReply> list = boardReplyRepository.findAll();
        assertEquals(1, list.size());

    }


}