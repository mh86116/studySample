package com.nwrn.test.member.service;

import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //회원목록조회
    @Override
    public List<Member> getMembers(MemberDTO dto) {
        validateMember(dto);
        return memberRepository.findAll();
    }
    //회원등록
    @Override
    public String insertMember(MemberDTO dto) {
            validateDuplicateMember(dto);
            Member member = Member.builder()
                    .name(dto.getName())
                    .build();
            memberRepository.save(member);
        return "ok";
    }
    //정보수정
    @Override
    public String updateMember(MemberDTO dto, Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.updateMember(dto.getName());
        return "ok";
    }

    @Override
    public String deleteMember(Long id) {
        memberRepository.deleteById(id);
        return "ok";
    }


    /****************************************************************************
     * 공통 로직
     ***************************************************************************/
    private void validateDuplicateMember(MemberDTO dto) {
        memberRepository.findById(dto.getMemberNo())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    private void validateMember(MemberDTO dto) {
        memberRepository.findById(dto.getMemberNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
