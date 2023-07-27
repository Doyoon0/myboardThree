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

    public List<Comment> commentList(Integer boardid) {
        return commentRepository.findByBoardid(boardid);
    }

}
