package org.zerock.controller.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardController {

    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(sort={"bno"}, direction = Sort.Direction.DESC, size=5) Pageable pageable) {
        model.addAttribute("list", boardService.findAll(pageable));
        return "list";
    }

    @PostMapping("/register")
    public String register(Board board, RedirectAttributes rttr) {
        boardService.saveBoard(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping({"/get", "/modify"})
    public String get(@RequestParam("bno") Long bno, Model model, HttpServletRequest request) {
        Optional<Board> board=boardService.findBoard(bno);

        if(board.isPresent()){
            model.addAttribute("board", board.get());
        }

        String[] url=request.getRequestURI().split("/");
        String param=url[url.length-1];

        if(param.equals("get")){
            return "get";
        }else{
            return "modify";
        }


    }

    @PostMapping("/modify")
    public String modify(Board board, RedirectAttributes rttr) {
        if (boardService.updateBoard(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        if (boardService.deleteBoard(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
}
