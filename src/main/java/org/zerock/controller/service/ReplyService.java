package org.zerock.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.domain.board.ReplyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ReplyService {

    private ReplyRepository replyRepository;
    private BoardRepository boardRepository;

    public Long register(Reply reply){
        Reply save = replyRepository.save(reply);
        return save.getRno();
    }

    public Optional<Reply> get(Long rno){
        return replyRepository.findById(rno);
    }

    public List<Reply> getList(Long bno){

        Optional<Board> thatBoard = boardRepository.findById(bno);

        return thatBoard.get().getReplies();

    }

    @Transactional
    public boolean updateReply(Reply reply, Long rno){
        Optional<Reply> r = replyRepository.findById(rno);

        if(r.isPresent()){
            r.get().setReply(reply.getReply());
            r.get().setReplyer(reply.getReplyer());

            return true;
        }else{
            return false;
        }

    }

    public boolean remove(Long rno){

        return replyRepository.deleteByRno(rno)==1;
    }

//    public List<Reply> getList(Criteria cri){
//        return replyRepository.
//    }

}
