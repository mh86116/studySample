package com.nwrn.test.board.repository;

import com.nwrn.test.board.model.entity.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {
}
