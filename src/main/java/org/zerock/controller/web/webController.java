package org.zerock.controller.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.zerock.controller.service.PostsService;

@Controller
@AllArgsConstructor
public class webController {

    private PostsService postsService;

    //@GetMapping("/")
    public String main(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "main";
    }
}
