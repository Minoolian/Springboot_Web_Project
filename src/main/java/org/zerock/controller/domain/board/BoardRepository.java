package org.zerock.controller.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
// 메서드 명을 조합하여 쿼리를 처리할 수 있다.

    @Transactional
    int deleteByBno(Long bno);
    // custom delete에는 @Transactional 어노테이션 부여

    List<Board> findByTitleContainingOrWriterContaining(String title, String content);

}
