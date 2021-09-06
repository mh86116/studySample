package com.nwrn.test.board.repository;

import com.nwrn.test.board.model.dto.BoardDTO;
import com.nwrn.test.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {


}
