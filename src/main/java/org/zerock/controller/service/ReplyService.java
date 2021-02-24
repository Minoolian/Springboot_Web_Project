package org.zerock.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.domain.board.ReplyRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReplyService {

    private ReplyRepository replyRepository;

    public Reply register(Reply reply){
        return replyRepository.save(reply);
    }

    public Optional<Reply> get(Long rno){
        return replyRepository.findById(rno);
    }

    public boolean remove(Long rno){

        return replyRepository.deleteByRno(rno)==1;
    }

//    public List<Reply> getList(Criteria cri){
//        return replyRepository.
//    }

}
