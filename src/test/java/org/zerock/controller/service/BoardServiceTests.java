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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void cleanUp(){
        boardRepository.deleteAll();
    }

    @Test
    public void registerTest(){

        Board e = boardRepository.save(
                Board.builder()
                        .title("Test Title")
                        .content("Test Content")
                        .writer("Test Writer")
                        .build()
        );

        Optional <Board> a=boardRepository.findById(1L);

        assertEquals("Test Title",a.get().getTitle());
        assertEquals("Test Content",a.get().getContent());
        assertEquals("Test Writer",a.get().getWriter());
        assertNotNull(e.getCreateDate());


    }


    @Test
    public void updateTest(){

        boardRepository.save(
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

        Optional <Board> e=boardRepository.findById(1L);

        boardService.updateBoard(1L, updateBoard);
        assertThat(e.get().getTitle()).isEqualTo(updateBoard.getTitle());
        assertThat(e.get().getContent()).isEqualTo(updateBoard.getContent());
    }

    @Test
    public void deleteTest(){

        boardRepository.save(
                Board.builder()
                        .title("Test Title")
                        .content("Test Content")
                        .writer("Test Writer")
                        .build()
        );
        
        assertThat(boardRepository.deleteByBno(1L)).isEqualTo(1);
    }
}
