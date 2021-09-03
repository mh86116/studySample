package com.nwrn.test.member.model.dto;

import com.nwrn.test.member.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDTO {
    private Long memberNo;
    private String name;

    public MemberDTO(Member member) {
        this.name = member.getName();
    }
}
