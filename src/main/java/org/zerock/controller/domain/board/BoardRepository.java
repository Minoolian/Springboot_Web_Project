package org.zerock.controller.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
// 메서드 명을 조합하여 쿼리를 처리할 수 있다.

    Long deleteByBno(Long bno);

}
