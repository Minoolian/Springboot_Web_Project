package org.zerock.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.controller.domain.board.Reply;
import org.zerock.controller.domain.board.ReplyRepository;
import org.zerock.controller.domain.board.ReplyRepositorySupport;
import org.zerock.controller.dto.board.Criteria;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ReplyService {

    private ReplyRepository replyRepository;
    private ReplyRepositorySupport support;

    public Long register(Reply reply){
        Reply save = replyRepository.save(reply);
        return save.getRno();
    }

    public Optional<Reply> get(Long rno){
        return replyRepository.findById(rno);
    }

    public List<Reply> getList(Long bno, Criteria cri){

        PageRequest pageRequest = PageRequest.of(cri.getPageNum()-1, cri.getAmount(), Sort.by("rno").descending());

        List<Reply> replyByBoard = support.findReplyByBoard(bno, pageRequest);

        return replyByBoard;

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
