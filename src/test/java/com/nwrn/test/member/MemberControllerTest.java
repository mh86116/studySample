package com.nwrn.test.member;

import com.nwrn.test.member.model.entity.Member;
import com.nwrn.test.member.repository.MemberRepository;
import com.nwrn.test.member.repository.MemoryMemberRepository;
import com.nwrn.test.member.repository.MemoryMemberRepositoryImpl;
import com.nwrn.test.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        memberRepository.save(member1);
    }

    @AfterEach //Test 끝날 때마다
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void getMembers() {
//        Member member1 = Member.builder()
//                .memberNo(null)
//                .name("memberA")
//                .build();
//        memberRepository.save(member1);

        List<Member> members = memberRepository.findAll();

        assertFalse(members.isEmpty());
    }

    @Test
    void getMember() {
    }

    @Test
    void insertMember() {
    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }
}