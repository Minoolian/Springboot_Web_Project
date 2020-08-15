package org.zerock.controller.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.domain.board.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    public List<Board> findAll(){
        List<Board> boards = new ArrayList<>();
        boardRepository.findAll().forEach(a->boards.add(a));
        return boards;
    }

    public Optional<Board> findBoard(Long bno){
        Optional<Board> board = boardRepository.findById(bno);
        return board;
    }

    public void deleteBoard(Long bno){
        boardRepository.deleteById(bno);
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
