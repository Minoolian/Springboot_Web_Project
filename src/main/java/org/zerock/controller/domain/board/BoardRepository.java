package org.zerock.controller.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
// 메서드 명을 조합하여 쿼리를 처리할 수 있다.

    public List<Board> findByWriter(String writer);

    public List<Board> findByTitle(String title);

    public List<Board> findByTitleLike(String keyword);

}
