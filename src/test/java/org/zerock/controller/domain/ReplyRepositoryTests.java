package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.domain.board.ReplyRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insert(){

        replyRepository.save(Reply.builder()
        .reply("test reply")
        .replyer("test replyer")
        .build()
        );

    }

    @Test
    public void testEntity(){
        log.info(""+replyRepository);
    }
}
