package com.nwrn.test.board.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BRD_CATEGORY")
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRD_CATEGORY")
    private Long categoryNo;

    @Column(name = "CATEGORY_NAME")
    private String name;

    @Column(name = "CATEGORY_STATUS")
    private boolean status;
}
