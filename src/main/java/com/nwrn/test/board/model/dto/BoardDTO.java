package com.nwrn.test.board.model.dto;

import com.nwrn.test.board.model.enums.CategoryType;
import com.nwrn.test.board.model.entity.Board;
import com.nwrn.test.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDTO {
    private Long boardNo;
    private CategoryType category;
    private String title;
    private String body;
    private int writerMemberNo;
    private WriterDTO writer;
    private boolean status;

    public BoardDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.body = board.getBody();
    }

    public Board toEntity(Member member) {
        Board.BoardBuilder builder = Board.builder();

        return builder
                .category(this.category)
                .title(this.title)
                .body(this.body)
                .writer(member)
                .build();
    }
}

//작성자
@Getter
@AllArgsConstructor
class WriterDTO {
    private Long memberNo;
    private String name;
}
