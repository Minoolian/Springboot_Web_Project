package org.zerock.controller.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.service.BoardService;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", boardService.findAll());
        return "list";
    }

    @PostMapping("/register")
    public String register(Board board) {
        boardService.saveBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/get")
    public String get(@RequestParam("bno") Long bno, Model model) {
        model.addAttribute("board", boardService.findBoard(bno));
        return "get";
    }

    @PostMapping("/modify")
    public String modify(Board board) {
        if (boardService.updateBoard(board)) {

        }
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno) {
        if (boardService.deleteBoard(bno)) {

        }
        return "redirect:/board/list";
    }
}
