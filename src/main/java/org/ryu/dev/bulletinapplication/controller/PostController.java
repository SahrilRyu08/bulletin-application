package org.ryu.dev.bulletinapplication.controller;

import lombok.RequiredArgsConstructor;
import org.ryu.dev.bulletinapplication.model.Post;
import org.ryu.dev.bulletinapplication.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "post/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getById(id));
        return "post/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/form";
    }

    @PostMapping
    public String editForm(@ModelAttribute Post post) {
        postService.create(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("post", postService.getById(id));
        return "post/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @ModelAttribute Post post,
                       @RequestParam String inputPassword,
                       Model model) {
        try {
            postService.update(id, post, inputPassword);
            return "redirect:/posts/" + id;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("post", post);
            return "post/form";
        }
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id,
                         @RequestParam String inputPassword,
                         Model model) {
        try {
            postService.delete(id, inputPassword);
            return "redirect:/posts";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("post", postService.getById(id));
            return "post/detail";
        }
    }

}
