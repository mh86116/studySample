package com.nwrn.test.member;

import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import com.nwrn.test.member.repository.MemoryMemberRepositoryImpl;
import com.nwrn.test.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    }

    @Test
    void 회원_단건_조회() {
        List<Member> members = memberRepository.findAll();
        Member member = memberRepository.findById(members.get(1).getMemberNo())
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 회원이 없습니다."));

        List<Member> dto = memberRepository.findAll();

        assertThat(dto).isEqualTo(members);

    }

    @Test
    void 회원_등록() {
        List<Member> members = memberRepository.findAll();

        assertThat(members).extracting(Member::getName)
                .contains("memberA", "memberB", "memberC", "memberD");
    }

    @Test
    void 회원_수정() {

    }

    @Test
    void 회원_삭제() {
        List<Member> members = memberRepository.findAll();

        Long member = members.get(2).getMemberNo();

        memberService.deleteMember(member);

        List<Member> findMember = memberRepository.findAll();

        assertEquals(3, findMember.size());


    }
}