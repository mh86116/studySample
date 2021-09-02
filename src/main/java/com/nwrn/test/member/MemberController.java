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
    @PatchMapping("/members/{id}")
    public String updateMember(@RequestBody MemberDTO dto, @PathVariable Long id) {
        return memberService.updateMember(dto, id);
    }

    //회원탈퇴
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }
}
