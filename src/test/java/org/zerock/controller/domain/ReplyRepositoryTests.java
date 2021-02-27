package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.domain.board.ReplyRepository;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Transactional
public class ReplyRepositoryTests {

    private Long[] bnoArr={200L, 201L, 202L, 203L, 204L};

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Before
    @Transactional
    public void insert(){

        Board board=Board.builder()
                .title("test title")
                .content("test contet")
                .content("Test wirter")
                .build();

        Board board2=Board.builder()
                .title("test title2")
                .content("test contet2")
                .content("Test wirter2")
                .build();

        boardRepository.save(board);
        boardRepository.save(board2);

        IntStream.rangeClosed(1, 10).forEach(i->{
            if(i<6){
                Reply build = Reply.builder()
                        .board(board)
                        .reply("test reply" + i)
                        .replyer("test replyer" + i)
                        .build();

                replyRepository.save(build);
            }else{
                replyRepository.save(Reply.builder()
                        .board(board2)
                        .reply("test reply"+i)
                        .replyer("test replyer"+i)
                        .build()
                );
            }

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
    public void Nplus1_Test(){
        List<Board> boards=boardRepository.findAll();
        List<Reply> replies=replyRepository.findAll();

        for(Reply reply : replies){
            log.info(""+reply.getBoard().getTitle());
        }

        for(Board board : boards){
            log.info(""+board.getReplies().size());
        }

    }
}
