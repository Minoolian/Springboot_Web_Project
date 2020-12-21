package org.zerock.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class BoardServiceTests {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;


    @After
    // auto increment로 인한 통합테스트 실패해결 방법 (reset)
    public void teardown() {
        this.boardRepository.deleteAll();
        this.entityManager
                .createNativeQuery("ALTER TABLE tbl_board ALTER COLUMN bno RESTART                                                                     WITH 1")
                .executeUpdate();
    }

    @Test
    public void registerTest(){

        Board e = boardService.saveBoard(
                Board.builder()
                        .title("Test Title")
                        .content("Test Content")
                        .writer("Test Writer")
                        .build()
        );

        assertThat(e.getTitle()).isEqualTo("Test Title");
        log.info("Bno : "+e.getBno());

    }

    @Test
    public void updateTest(){

        boardService.saveBoard(
                Board.builder()
                .title("Test Title")
                .content("Test Content")
                .writer("Test Writer")
                .build()
        );

        Board updateBoard= Board.builder()
                .title("Modified Title")
                .content("Modified Content")
                .build();

        boardService.updateBoard(1L, updateBoard);
        assertThat(boardService.findBoard(1L).get().getTitle()).isEqualTo(updateBoard.getTitle());
        assertThat(boardService.findBoard(1L).get().getContent()).isEqualTo(updateBoard.getContent());
    }

    @Test
    public void deleteTest(){

        boardService.saveBoard(
                Board.builder()
                        .title("Test Title")
                        .content("Test Content")
                        .writer("Test Writer")
                        .build()
        );

        assertThat(boardService.deleteBoard(1L)).isEqualTo(1);
    }
}
