package com.nwrn.test.member.model.entity;

import com.nwrn.test.member.model.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_SN")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(name = "MEMBER_NAME")
    private String name;

    public void updateMember(String name) {
            this.name = (name != null) ? name :  this.getName();

    }
}
