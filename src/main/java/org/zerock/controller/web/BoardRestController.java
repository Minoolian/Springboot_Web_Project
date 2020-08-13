package org.zerock.controller.web;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/board")
public class BoardRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    BoardService boardService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Board>> getAllboards(){
        List<Board> board = boardService.findAll();
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping(value = "/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Board> getTitle(@PathVariable("bno") Long bno){
        Optional<Board> board = boardService.findByTitle(bno);
        return new ResponseEntity<>(board.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteTitle(@PathVariable("bno") Long bno){
        boardService.deleteByTitle(bno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Board> updateBoard(@PathVariable("bno") Long bno, Board board){
        boardService.updateBoard(bno,board);
        return new ResponseEntity<>(board,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> save(Board board){
        return new ResponseEntity<>(boardService.save(board),HttpStatus.OK);
    }

    @RequestMapping(value = "/saveBoard", method = RequestMethod.GET)
    public ResponseEntity<Board> save(HttpServletRequest req, Board board){
        return new ResponseEntity<>(boardService.save(board), HttpStatus.OK);
    }

    
}
