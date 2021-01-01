package org.zerock.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    public List<Board> findAll(){
        return boardRepository.findAll();
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
