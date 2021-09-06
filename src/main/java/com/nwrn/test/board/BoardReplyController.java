package com.nwrn.test.board;

import com.nwrn.test.board.model.dto.BoardReplyDTO;
import com.nwrn.test.board.model.entity.BoardReply;
import com.nwrn.test.board.service.BoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardReplyController {

    private final BoardReplyService boardReplyService;

    //전체조회
    @GetMapping("/replies")
    public List<BoardReply> replies(BoardReplyDTO dto) {
        return boardReplyService.replies(dto);
    }

    //단건조회
    @GetMapping("/reply/{id}")
    public BoardReply getReply(@PathVariable Long id) {
        return boardReplyService.getReply(id);
    }

    //작성
    @PostMapping("/reply")
    public String insertReply(BoardReplyDTO dto,
                              @RequestParam Long boardNo,
                              @RequestParam Long memberNo) {
        return boardReplyService.insertReply(dto, boardNo, memberNo);
    }

    //수정
    @PatchMapping("/reply/{id}")
    public String updateReply(BoardReplyDTO dto,
                             @PathVariable Long id) {
        return boardReplyService.updateReply(dto, id);
    }

    //삭제
    @DeleteMapping("/reply/{id}")
    public String deleteReply(@PathVariable Long id) {
        return boardReplyService.deleteReply(id);
    }

}