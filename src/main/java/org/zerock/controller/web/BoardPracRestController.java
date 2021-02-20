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
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/tempboard")
public class BoardPracRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    BoardService boardService;

//    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<List<Board>> getAllboards(){
//        List<Board> board = boardService.findAll();
//        return new ResponseEntity<>(board, HttpStatus.OK);
//    }

    @GetMapping(value = "/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Board> getTitle(@PathVariable("bno") Long bno){
        Optional<Board> board = boardService.findBoard(bno);
        return new ResponseEntity<>(board.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteTitle(@PathVariable("bno") Long bno){
        boardService.deleteBoard(bno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Board> updateBoard(@PathVariable("bno") Long bno, Board board){
        boardService.updateBoard(board);
        return new ResponseEntity<>(board,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> save(Board board){
        return new ResponseEntity<>(boardService.saveBoard(board),HttpStatus.OK);
    }

    @RequestMapping(value = "/saveBoard", method = RequestMethod.GET)
    public ResponseEntity<Board> save(HttpServletRequest req, Board board){
        return new ResponseEntity<>(boardService.saveBoard(board), HttpStatus.OK);
    }

    
}
