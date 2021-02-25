package org.zerock.controller.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //
    // List<Reply> findByBno(Long bno, Pageable pageable);

    @Transactional
    int deleteByRno(Long rno);

}
