package org.zerock.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.Reply;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    public void register(){
        boardService.saveBoard(Board.builder().title("Test Title").build());

        replyService.register(
                Reply.builder()
                .replyer("test REplyer")
                .reply("test reply")
                .board(boardService.findBoard(1L).get())
                .build()
        );

        assertThat(replyService.get(1L).get().getReply(),is("test reply"));
        assertThat(replyService.get(1L).get().getBoard().getReplies().size(),is(1));


    }


}