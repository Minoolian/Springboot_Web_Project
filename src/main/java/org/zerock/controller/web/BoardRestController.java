package org.zerock.controller.web;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.service.BoardService;

import javax.xml.ws.Response;
import java.util.List;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
public class BoardRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    BoardService boardService;

    public ResponseEntity<List<Board>> getAllboards(){
        List<Board> board = boardService.findAll();
        return new ResponseEntity<>(board, HttpStatus.OK)
    }

    
}
