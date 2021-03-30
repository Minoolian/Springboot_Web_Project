package org.zerock.controller.domain.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.zerock.controller.dto.board.ReplyPageDTO;

import java.util.List;

import static org.zerock.controller.domain.board.QBoard.board;
import static org.zerock.controller.domain.board.QReply.reply1;

@Repository
public class ReplyRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ReplyRepositorySupport(JPAQueryFactory queryFactory) {
        super(Reply.class);
        this.queryFactory=queryFactory;
    }

    public List<Reply> findReplyByBoard(Long bno, Pageable pageable) {
        return queryFactory
                .selectFrom(reply1)
                .innerJoin(reply1.board, board).fetchJoin()
                .where(board.bno.eq(bno))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

    }

    public ReplyPageDTO findReplyByBoardNew(Long bno, Pageable pageable) {
        JPAQuery<Reply> find = queryFactory
                .selectFrom(reply1)
                .innerJoin(reply1.board, board).fetchJoin()
                .where(board.bno.eq(bno));

        Long count = find.fetchCount();
        List<Reply> fetch = find
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();


        return new ReplyPageDTO(count, fetch);

    }
}
