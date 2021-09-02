package com.nwrn.test.member.service;

import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;

import java.util.List;

public interface MemberService {

    //회원목록조회
    List<Member> getMembers(MemberDTO dto);
    //회원등록
    String insertMember(MemberDTO dto);
    //정보수정
    String updateMember(MemberDTO dto, Long id);
    //회원탈퇴
    String deleteMember(Long id);
}
