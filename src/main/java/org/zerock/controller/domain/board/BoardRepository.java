package org.zerock.controller.domain.board;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
// 메서드 명을 조합하여 쿼리를 처리할 수 있다.

    @Transactional
    int deleteByBno(Long bno);
    // custom delete에는 @Transactional 어노테이션 부여

    List<Board> findByTitleContainingOrWriterContaining(String title, String content);

    @Query(value="select DISTINCT c from Board c left join fetch c.replies")
    List<Board> findAllWithFetch();

    @EntityGraph(attributePaths = "replies")
    @Query("select b from Board b")
    List<Board> findAllWithEntityGraph();

}
