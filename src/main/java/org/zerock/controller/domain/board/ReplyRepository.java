package org.zerock.controller.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //
    // List<Reply> findByBno(Long bno, Pageable pageable);

    @Transactional
    int deleteByRno(Long rno);

}
