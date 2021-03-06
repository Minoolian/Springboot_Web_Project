package org.zerock.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.service.BoardService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(BoardController.class)
////단위테스트용
@Slf4j
//@EnableJpaAuditing
//단위테스트용
public class BoardControllerTests {

    @Autowired
//    @MockBean
//    단위테스트용
    private BoardService boardService;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    public void bootListTest() throws Exception{

        log.info("=============MVC TEST START=====================");

        mockMvc.perform(get("/board/list").param("pageNum","2").param("amount","3"))
                .andExpect(status().isOk())
                .andDo(print());

        log.info("=============MVC TEST END=====================");

    }

//    단위테스트용
//    단위테스트를 위해서 임시의 Service 클래스를 이용해 JPA를 사용하지 않는 방향...
//    @Test
//    public void unitListTest() throws Exception{
//        Board board = Board.builder()
//                .title("test")
//                .writer("testwriter")
//                .content("testcontent")
//                .build();
//
//        boardService.saveBoard(board);
//
//        given(boardService.findBoard(1L).get())
//        .willReturn(board);
//
//        final ResultActions actions = mockMvc.perform(get("/board"))
//                .andDo(print());
//
//        actions
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }

    @Test
    public void registerTest() throws Exception {
//        Board board = boardService.saveBoard(
//                Board.builder()
//                        .bno(1L)
//                .writer("test writer")
//                .content("test content")
//                .title("test title1")
//                .build()
//        );

        mockMvc.perform(post("/board/register")
                .param("title", "test title")
                .param("writer","test writer")
                .param("content", "test content")

        )
                .andExpect(status().is3xxRedirection())
                // Redirection 되므로 302 error 예상
                .andDo(print());
    }

    @Test
    public void getTest() throws Exception {
        boardService.saveBoard(
                Board.builder()
                .title("test title")
                .content("test contetn")
                .writer("test writer")
                .build()
        );

        mockMvc.perform(get("/board/get")
        .param("bno", "1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void modifyTest() throws Exception {
        boardService.saveBoard(
                Board.builder()
                        .title("test title")
                        .content("test content")
                        .writer("test writer")
                        .build()
        );

        mockMvc.perform(post("/board/modify")
                .param("bno", "1")
                .param("title", "modifyTitle")
        )
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }


    @Test
    public void removeTest() throws Exception {
        boardService.saveBoard(
                Board.builder()
                        .title("test title")
                        .content("test content")
                        .writer("test writer")
                        .build()
        );

        Optional<Board> board = boardService.findBoard(1L);

        if(board.isPresent()) {
            mockMvc.perform(post("/board/remove")
                    .param("bno", "1"))
                    .andExpect(status().is3xxRedirection())
                    .andDo(print());
        }

    }

}
