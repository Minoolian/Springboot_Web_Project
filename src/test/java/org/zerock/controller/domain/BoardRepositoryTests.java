package org.zerock.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;
import org.zerock.controller.domain.board.BoardRepositorySupport;
import org.zerock.controller.dto.board.BoardSpecs;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardRepositorySupport boardRepositorySupport;


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
    public void BoardDeleteTest(){
        int[] a={1, 2, 3, 4, 5};
        for(int v:a) {
            boardRepository.save(Board.builder()
                    .title("테스트게시글" + v)
                    .content("테스트본문")
                    .writer("minoolian")
                    .build());
        }

        assertThat(boardRepository.deleteByBno(4L), is("2L"));




//        Optional<Board> board = boardRepository.findById(1L);
//        assertTrue(board.isPresent());
//        board.ifPresent(selectBoard->{
//            boardRepository.deleteById(selectBoard.getBno());
//        });
//
//        Optional<Board> deleteboard = boardRepository.findById(1L);
//
//        assertFalse(deleteboard.isPresent());

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
        int[] a={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int v:a) {
            boardRepository.save(Board.builder()
                    .title("테스트게시글" + v)
                    .content("테스트본문" + (v+1))
                    .writer("minoolian")
                    .build());
        }


        Map<String, Object> map=new HashMap<>();

        map.put("title", "3");
//        map.put("content", "3");
//        map.put("c", "content");


        List<Board> board=boardRepository.findAll(BoardSpecs.searchWith(map));

        for(Board b:board){
            log.info(b.getTitle());
            log.info(b.getContent());
            log.info(b.getWriter());
        }

    }

    @Test
    @Transactional
    public void nplus1_Test(){
        int[] a={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int v:a) {
            boardRepository.save(Board.builder()
                    .title("테스트게시글" + v)
                    .content("테스트본문" + (v+1))
                    .writer("minoolian")
                    .build());
        }

        List<Board> boards = boardRepository.findAll();

        for(Board board : boards){
            System.out.println(board.getReplies().size());
        }
    }

    @Test
    public void querydslTests(){
        int[] a={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for(int v:a) {
            boardRepository.save(Board.builder()
                    .title("테스트게시글" + v)
                    .content("테스트본문" + (v+1))
                    .writer("minoolian")
                    .build());
        }

        List<Board> boards = boardRepositorySupport.findByTitle("테스트게시글4");

        assertThat(boards.size(), is(1));
        assertThat(boards.get(0).getContent(), is("테스트본문5"));
    }
}
