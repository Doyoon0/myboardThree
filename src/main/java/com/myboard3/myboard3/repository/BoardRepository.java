package com.myboard3.myboard3.repository;

import com.myboard3.myboard3.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    // 비밀번호가 없는 게시글 중에서 검색 기능
    Page<Board> findByTitleContainingAndPasswordIsNull(String searchKeyword, Pageable pageable);

}
