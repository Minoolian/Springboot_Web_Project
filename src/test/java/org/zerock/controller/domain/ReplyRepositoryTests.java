package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.domain.board.ReplyRepository;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ReplyRepositoryTests {

    private Long[] bnoArr={200L, 201L, 202L, 203L, 204L};

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Before
    public void insert(){

        Board board=Board.builder()
                .title("test title")
                .content("test contet")
                .content("Test wirter")
                .build();

        boardRepository.save(board);

        IntStream.rangeClosed(1, 10).forEach(i->{
            replyRepository.save(Reply.builder()
                    .board(board)
                    .reply("test reply"+i)
                    .replyer("test replyer"+i)
                    .build()
            );
        });

    }

    @Test
    public void testEntity(){
        log.info(""+replyRepository);
    }

    @Test
    public void findTest(){
        Reply reply = replyRepository.findAll().get(0);
        Board board=reply.getBoard();
        log.info("first reply : "+reply.getReply());

        for(Reply r : board.getReplies()){
            log.info("replies : " + r.getReply());
        }
    }



    @Test
    public void Test(){

    }
}
