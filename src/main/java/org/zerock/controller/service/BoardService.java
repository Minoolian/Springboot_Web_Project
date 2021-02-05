package org.zerock.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;
import org.zerock.controller.dto.board.Criteria;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    public Page<Board> findAll(Criteria cri){

        PageRequest pageRequest = PageRequest.of(cri.getPageNum()-1, cri.getAmount(), Sort.by("bno").descending());

        return boardRepository.findAll(pageRequest);
    }

    public Optional<Board> findBoard(Long bno){
        return boardRepository.findById(bno);
    }

    public boolean deleteBoard(Long bno){

        return boardRepository.deleteByBno(bno)==1;
    }

    public Board saveBoard(Board board){
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public boolean updateBoard(Board board){
        Optional<Board> e = boardRepository.findById(board.getBno());

        if(e.isPresent()){
            e.get().setTitle(board.getTitle());
            e.get().setContent(board.getContent());
            e.get().setWriter(board.getWriter());

            return true;
        }else{
            return false;
        }

    }
}
