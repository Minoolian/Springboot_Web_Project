package org.zerock.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.dto.board.Criteria;
import org.zerock.controller.service.ReplyService;

import java.util.List;

@RequestMapping("/replies")
@RestController
@Slf4j
public class ReplyRestController {

    @Autowired
    private ReplyService service;

    @PostMapping(value="/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> register(@RequestBody Reply reply){

        Long check = service.register(reply);

        return check==reply.getRno() ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value="/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Reply>> List(@PathVariable("page") int page, @PathVariable("bno") Long bno){
        Criteria cri=new Criteria(page, 10);
        return new ResponseEntity<>(service.getList(bno, cri),HttpStatus.OK);
    }

    @GetMapping(value="/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Reply> get(@PathVariable("rno") Long rno){
        return new ResponseEntity<>(service.get(rno).get(), HttpStatus.OK);
    }

    @DeleteMapping(value="/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        return service.remove(rno)==true ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT}, value="/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody Reply reply, @PathVariable("rno") Long rno){
        return service.updateReply(reply, rno)==true ?
                new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
