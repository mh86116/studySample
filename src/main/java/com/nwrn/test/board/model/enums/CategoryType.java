package com.nwrn.test.board.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {
        NOTICE(1, "공지사항"),
        POPULAR_BOARD(2, "인기게시판"),
        FREE_BOARD(3, "자유게시판");

        private int code;
        private String title;

}