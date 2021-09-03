package com.nwrn.test.board;

import com.nwrn.test.board.model.dto.BoardDTO;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.board.model.enums.CategoryType;
import com.nwrn.test.board.repository.BoardRepository;
import com.nwrn.test.board.service.BoardService;
import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardControllerTest {

    @Autowired
    BoardService boardService;
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
    }



    @Test
    void getBoards() {
        List<Board> dto = boardRepository.findAll();

        assertEquals(3, dto.size());
        assertNotNull(dto.get(2).getWriter());
    }

    @Test
    void getBoard() {
        List<Board> dto = boardRepository.findAll();
        Board board = boardRepository.findById(dto.get(1).getBoardNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("해당회원이 없습니다."));
        assertEquals(board, dto.get(1));
    }

    @Test
    void insertBoard() {
        List<Member> dto = memberRepository.findAll();

        Board board = Board.builder()
                .boardNo(null)
                .category(CategoryType.NOTICE)
                .title("게시글1")
                .body("내용무")
                .writer(dto.get(0))
                .status(true)
                .build();
        boardRepository.save(board);

        BoardDTO boardDTO = new BoardDTO(board);

        assertEquals(boardDTO.getBoardNo(), board.getBoardNo());
    }

    @Test
    void updateBoard() {
        List<Board> dto = boardRepository.findAll();

        Board board = dto.get(2);

        BoardDTO boardDTO = new BoardDTO(board);

        Long no = dto.get(2).getBoardNo();

        boardService.updateBoard(boardDTO, no);

        assertEquals(boardDTO.getTitle(), board.getTitle());


    }
}