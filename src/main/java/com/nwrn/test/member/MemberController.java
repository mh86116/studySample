package com.nwrn.test.member;

import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import com.nwrn.test.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원목록조회
    @GetMapping("/members")
    public List<Member> getMembers(MemberDTO dto) {
        return memberService.getMembers(dto);
    }

    //회원단건조회
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다."));
        return "ok";
    }

    //회원등록
    @PostMapping("/newMember")
    public String insertMember(MemberDTO dto) {
        return memberService.insertMember(dto);
    }

    //회원수정
    @ResponseBody
    @PatchMapping("/members")
    public String updateMember(@RequestBody MemberDTO dto) {
        Member member = memberRepository.findById(dto.getMemberNo())
                .orElseThrow(() -> new IllegalArgumentException("회원 id가 없습니다."));
        member.updateMember(dto);
        memberRepository.save(member);
        return "ok";
    }

    //회원탈퇴
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return "ok";
//        memberRepository.deleteById(id);
    }
}
