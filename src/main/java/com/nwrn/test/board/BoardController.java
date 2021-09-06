package com.nwrn.test.board;

import com.nwrn.test.board.model.dto.BoardDTO;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.board.repository.BoardRepository;
import com.nwrn.test.board.service.BoardService;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //전체조회
    @GetMapping("/boards")
    public List<Board> getBoards(BoardDTO dto) {
        return boardService.getBoards(dto);
    }

    //단건조회
    @GetMapping("/baord/{id}")
    public String getBoard(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return "ok";
    }

    //게시글 등록
    @PostMapping("/newBoard")
    public String insertBoard(BoardDTO dto, @RequestParam Long memberNo) {
        //회원정보
        Member member = memberRepository.getById(memberNo);

        return boardService.insertBoard(dto, member);
    }

    //게시글 수정
    @ResponseBody
    @PatchMapping("/newBoard/{id}")
    public String updateBoard(@RequestBody BoardDTO dto, @PathVariable Long id) {
        return boardService.updateBoard(dto, id);
    }

    //게시글 삭제
    @ResponseBody
    @DeleteMapping("/board/{id}")
    private String deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }
}
