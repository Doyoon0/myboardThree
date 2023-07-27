package com.myboard3.myboard3.service;

import com.myboard3.myboard3.entity.Board;
import com.myboard3.myboard3.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }

    public Page<Board> boardList(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        List<Board> boardList = boardPage.getContent();
        reassignIds(boardList, pageable);
        return new PageImpl<>(boardList, pageable, boardPage.getTotalElements());
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        Page<Board> boardPage;

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            boardPage = boardRepository.findByTitleContainingAndPasswordIsNull(searchKeyword, pageable);
        } else {
            boardPage = boardRepository.findAll(pageable);
        }
        List<Board> boardList = boardPage.getContent();
        reassignIds(boardList, pageable);
        return new PageImpl<>(boardList, pageable, boardPage.getTotalElements());
    }

    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id) { boardRepository.deleteById(id); }

    private void reassignIds(List<Board> boardList, Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        for (int i = 0; i < boardList.size(); i++) {
            Board board = boardList.get(i);
            int virtualId = offset + i + 1;
            board.setVirtualId(virtualId);
        }
    }
}
