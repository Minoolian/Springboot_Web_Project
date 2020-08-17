package org.zerock.controller.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    public void RegisterTest(){

        Board e = boardRepository.save(
                Board.builder()
                        .title("Test Title")
                        .content("Test Content")
                        .writer("Test Writer")
                        .build()
        );

        assertEquals("Test Title",e.getTitle());
        assertEquals("Test Content",e.getContent());
        assertEquals("Test Writer",e.getWriter());
        assertNotNull(e.getCreateDate());


    }

    @Test
    public void UpdateTest(){

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
}
