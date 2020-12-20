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

    public Long deleteBoard(Long bno){

        return boardRepository.deleteByBno(bno);
    }

    public Board saveBoard(Board board){
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public void updateBoard(Long bno, Board board){
        Optional<Board> e = boardRepository.findById(bno);

        if(e.isPresent()){
            e.get().updateBoard(board);
        }
    }
}
