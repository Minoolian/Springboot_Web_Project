package org.zerock.controller.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
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
}
