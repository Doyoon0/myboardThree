package com.myboard3.myboard3.service;

import com.myboard3.myboard3.entity.Comment;
import com.myboard3.myboard3.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void write(Comment comment) { commentRepository.save(comment); }

    public List<Comment> commentList(Integer boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        System.out.println("댓글 리스트 조회 결과: " + comments);

        return comments; // 해당 게시글에 속하는 댓글만 조회
    }

}
