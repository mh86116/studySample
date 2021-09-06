package com.nwrn.test.board.model.entity;

import com.nwrn.test.member.model.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BRD_REPLY")
public class BoardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO", nullable = false)
    private Long replyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_SN")
    private Board boardNo;

    @Column(name = "REPLY_BODY")
    private String replyBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SN")
    private Member writer;

    @Column(name = "REPLY_STATUS")
    private boolean status;

    public void updateReply(String body) {
        this.replyBody = (body != null) ? body : this.getReplyBody();
    }
}
