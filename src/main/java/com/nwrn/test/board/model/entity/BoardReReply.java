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
@Table(name = "BRD_RE_REPLY")
public class BoardReReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RE_REPLY_SN", nullable = false)
    private Long reReplyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_SN")
    private Board boardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLY_SN")
    private BoardReply replyNo;

    @Column(name = "RE_REPLY_BODY")
    private String reBody;

    @Column(name = "RE_REPLY_ORDER")
    private String reReOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SN")
    private Member memberNo;

    @Column(name = "RE_REPLY_STATUS")
    private boolean status;
}
