package com.nwrn.test.board.model.entity;

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

    @Column(name = "REPLY_ORDER")
    private String replyOrder;

    @Column(name = "REPLY_STATUS")
    private boolean status;
}
