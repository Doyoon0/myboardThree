package com.myboard3.myboard3.controller;

import com.myboard3.myboard3.entity.Comment;
import com.myboard3.myboard3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/board/comment/write")
    public String insertComment(@RequestParam("boardId") Integer boardId, Comment comment) {
        comment.setBoardId(boardId); // Comment 객체에 boardId 설정

        commentService.write(comment);
        return "redirect:/board/view?id=" + boardId;
    }

    @GetMapping("/board/comment/list")
    public String commentList(@RequestParam("boardId") Integer boardId, Model model) {
        List<Comment> comments = commentService.commentList(boardId);
        model.addAttribute("commentList", comments); // 댓글 리스트를 모델에 추가

        System.out.println("댓글 리스트 조회 결과: " + comments);

        return "board/view"; // 리다이렉트 대신 해당 뷰로 바로 이동
    }
}
