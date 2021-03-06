package org.zerock.controller.domain.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.zerock.controller.domain.board.QBoard.board;

@Repository
public class BoardRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public BoardRepositorySupport(JPAQueryFactory queryFactory){
        super(Board.class);
        this.queryFactory=queryFactory;
    }

    public List<Board> findByTitle(String title){
        return queryFactory
                .selectFrom(board)
                .where(board.title.eq(title))
                .fetch();
    }

    public List<Board> findByleftJoinBoards(){
        QReply reply = QReply.reply1;
        QBoard board = QBoard.board;
        return from(board)
                .distinct()
                .leftJoin(board.replies, reply).fetchJoin()
                .orderBy(board.bno.asc(), reply.rno.asc())
                .fetch();
    }
}
