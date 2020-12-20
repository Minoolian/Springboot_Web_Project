package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void BoardSaveTest() {
        //given : 테스트 기반환경 구축
        boardRepository.save(Board.builder()
                .title("테스트게시글")
                .content("테스트본문")
                .writer("minoolian")
                .build());

        //when : 테스트할 행위위
        List<Board> boardList = boardRepository.findAll();

        //then : 테스트 결과 검증
        Board board = boardList.get(0);
        assertThat(board.getTitle(), is("테스트게시글"));
        assertThat(board.getContent(), is("테스트본문"));
    }

    @Test
    public void BoardUpdateTest(){
        boardRepository.save(Board.builder()
                .title("테스트게시글")
                .content("테스트본문")
                .writer("minoolian")
                .build());

        Board e=Board.builder()
                .title("modified Title")
                .content("modified Content")
                .build();

        Optional<Board> board = boardRepository.findById(1L);
        if(board.isPresent()){
            board.get().updateBoard(e);
        }

        assertThat(board.get().getTitle(),is(e.getTitle()));
        assertThat(board.get().getContent(),is(e.getContent()));
    }

    @Test
    public void BoardDeleteTest(){
        boardRepository.save(Board.builder()
                .title("테스트게시글")
                .content("테스트본문")
                .writer("minoolian")
                .build());

        Optional<Board> board = boardRepository.findById(1L);
        assertTrue(board.isPresent());
        board.ifPresent(selectBoard->{
            boardRepository.deleteById(selectBoard.getBno());
        });

        Optional<Board> deleteboard = boardRepository.findById(1L);

        assertFalse(deleteboard.isPresent());

    }

    @Test
    public void BoardFindTest(){
        Board ex_board=Board.builder()
                .title("테스트게시글")
                .content("테스트본문")
                .writer("minoolian")
                .build();

        boardRepository.save(ex_board);

        Optional<Board> board = boardRepository.findById(1L);

        board.ifPresent(selectboard->{
            assertThat(selectboard.getBno(), is(ex_board.getBno()));
                }
        );
    }

    @Test
    public void BoardFindAllTest(){
        boardRepository.save(
                Board.builder()
                        .title("테스트게시글")
                        .content("테스트본문")
                        .writer("minoolian")
                        .build()
        );

        List<Board> board=boardRepository.findAll();

        board.forEach(a-> log.info("result="+a));

    }
}
