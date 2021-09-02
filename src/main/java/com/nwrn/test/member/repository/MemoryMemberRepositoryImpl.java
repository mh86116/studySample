package com.nwrn.test.member.repository;

import com.nwrn.test.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemoryMemberRepositoryImpl implements MemoryMemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    public void clearStore() {
        store.clear();
    }

}
