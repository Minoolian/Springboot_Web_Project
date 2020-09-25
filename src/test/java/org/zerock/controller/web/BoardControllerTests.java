package org.zerock.controller.web;

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
import org.zerock.controller.service.BoardService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@
public class BoardControllerTests {

    @Autowired
    private BoardService boardService;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(ctx)
                .build();
    }

    @Test
    public void testList() throws Exception{

        log.info("=============MVC TEST START=====================");

        mockMvc.perform(get("/board/list"))
                .andDo(print());

        log.info("=============MVC TEST END=====================");


    }

}
