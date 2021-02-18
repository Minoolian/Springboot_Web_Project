package org.zerock.controller.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.controller.domain.board.Board;
import org.zerock.controller.dto.board.Criteria;
import org.zerock.controller.dto.board.PageDTO;
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
    //@RequestParam(value="pageNum") Integer pageNum, @RequestParam(value="amount") Integer amount
    public String list(Criteria cri, Model model) {
        model.addAttribute("list", boardService.findAll(cri).getContent());

        int total=boardService.getTotal();

        model.addAttribute("pageMaker", new PageDTO(cri,total));
        log.info("amount : "+cri.getAmount() + ", pageNum : "+cri.getPageNum());
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
    public String get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest request) {
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
    public String modify(Board board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        if (boardService.updateBoard(board)) {
            rttr.addFlashAttribute("result", "success");
        }

//        rttr.addAttribute("pageNum",cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        if (boardService.deleteBoard(bno)) {
            rttr.addFlashAttribute("result", "success");
        }

//        rttr.addAttribute("pageNum",cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list" + cri.getListLink();
    }
}
