package com.nwrn.test.board.model.entity;

import com.nwrn.test.board.model.enums.CategoryType;
import com.nwrn.test.member.model.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_SN", nullable = false)
    private Long boardNo;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @Column(name = "BOARD_TITLE")
    private String title;

    @Column(name = "BOARD_BODY")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SN")
    private Member writer;

    @Column(name = "BOARD_STATUS")
    private boolean status;

    public void updateBoard(String title, String body, CategoryType category) {
        this.title = (title != null) ? title : this.getTitle();
        this.body = (body != null) ? body : this.getBody();
        this.category = (category != null) ? category : this.getCategory();
    }
}
