package com.myboard3.myboard3.controller;

import com.myboard3.myboard3.entity.Comment;
import com.myboard3.myboard3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/board/comment/write")
    public String insertComment(@RequestParam("boardid") Integer boardid, Comment comment) {
        comment.setBoardid(boardid); // Comment 객체에 boardId 설정

        commentService.write(comment);
        return "redirect:/board/view?id=" + boardid;
    }

    @GetMapping("/board/comment/list")
    @ResponseBody
    public List<Comment> getCommentList(@RequestParam("boardid") Integer boardid, Model model) {

        return commentService.commentList(boardid);
    }

    @GetMapping("/board/comment/delete/{id}")
    @ResponseBody // ajax 처리
    public String deleteComment(@PathVariable("id") Integer id) {
        commentService.commentDelete(id);
        return "success";
    }
}

