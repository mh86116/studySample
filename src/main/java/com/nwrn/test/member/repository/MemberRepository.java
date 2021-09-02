package com.nwrn.test.member.repository;

import com.nwrn.test.member.model.dto.MemberDTO;
import com.nwrn.test.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.name = : name")
    Optional<Member> findByName(@Param("name") String name);

    @Query("delete from Member m where m.memberNo = : member")
    Member delete(Long memberNo);

}
