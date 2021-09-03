package com.nwrn.test.member;

import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import com.nwrn.test.member.repository.MemoryMemberRepositoryImpl;
import com.nwrn.test.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberControllerTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemoryMemberRepositoryImpl memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        Member member1 = Member.builder()
                .memberNo(null)
                .name("memberA")
                .build();
        Member member2 = Member.builder()
                .memberNo(null)
                .name("memberB")
                .build();
        Member member3 = Member.builder()
                .memberNo(null)
                .name("memberC")
                .build();
        Member member4 = Member.builder()
                .memberNo(null)
                .name("memberD")
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
    }

    @AfterEach //Test 끝날 때마다
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원_목록_조회() {
        List<Member> dto = memberRepository.findAll();

        assertEquals(4, dto.size());
        assertNotNull(dto.get(1).getMemberNo());
        System.out.println("회원목록조회");
    }

    @Test
    void 회원_단건_조회() {
        List<Member> members = memberRepository.findAll();
        Member member = memberRepository.findById(members.get(1).getMemberNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 회원이 없습니다."));

        assertEquals(member, members.get(1));
        System.out.println(" 회원단건조회 ");
    }

    @Test
    void 회원_등록() {//따로 build
        Member member =Member.builder()
                .name("memberAA")
                .build();
        memberRepository.save(member);

        MemberDTO dto = new MemberDTO(member);

        assertEquals(dto.getName(), member.getName());
        System.out.println("회원등록");
    }

    @Test
    void 회원_수정() {
    List<Member> members = memberRepository.findAll();

    Member member = members.get(3);
    member.updateMember("memberDD");

    MemberDTO dto = new MemberDTO(member);

    Long no = members.get(3).getMemberNo();

    memberService.updateMember(dto, no);

    assertEquals(dto.getName(), member.getName());
    System.out.println("회원수정");
    }

    @Test
    void 회원_삭제() {
        List<Member> members = memberRepository.findAll();

        Long member = members.get(2).getMemberNo();

        memberService.deleteMember(member);

        memberRepository.findById(member)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        List<Member> memberList = memberRepository.findAll();
        assertEquals(3, memberList.size());

        System.out.println("memberList = " + memberList);

        System.out.println("회원삭제");
    }
}