package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.controller.domain.board.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ReplyRepositoryTests {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepositorySupport support;

    @Before
    @Transactional
    public void insert(){

        Board board = boardRepository.save(Board.builder()
        .title("test title")
        .content("test contet")
        .writer("Test wirter")
        .build());


        Board board2 = boardRepository.save(Board.builder()
                .title("test title2")
                .content("test contet2")
                .writer("Test wirter2")
                .build());

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
    @Transactional
    public void Nplus1_Test(){

        List<Board> boards=boardRepository.findAllWithFetch();
//        List<Board> boards=boardRepository.findAllWithEntityGraph();
//        List<Reply> replies=replyRepository.findAll();


//        for(Reply r:replies){
//            System.out.println(r.getBoard().getContent());
//        }

        List<String> collect = boards.stream()
                .map(board -> board.getReplies().get(0).getReply())
                .collect(Collectors.toList());


    }

    @Test
    public void queryDslTest(){

//        Criteria cri=new Criteria();
//        List<Reply> replies = support.findReplyByBoard(13L);
//
//        assertThat(replies.size(), is(2));
//        for(Reply r : replies){
//            log.info(r.getRno() + " " + r.getReply() + " " + r.getReplyer());
//        }

    }


}
