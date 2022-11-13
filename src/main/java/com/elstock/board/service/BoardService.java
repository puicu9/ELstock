package com.elstock.board.service;

import com.elstock.entity.Board;
import com.elstock.mapper.BoardMapperInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapperInterface boardMapperInterface ;

    public List<Board> SelectAll(){
        return this.boardMapperInterface.SelectAll() ;
    }

    public int Insert(Board board) {
        return this.boardMapperInterface.Insert(board) ;
    }

    public Board SelectOne(Integer no) {
        return this.boardMapperInterface.SelectOne(no) ;
    }

    public int Update(Board board) {
        return this.boardMapperInterface.Update(board) ;
    }

    public int Delete(Integer no) {
        return this.boardMapperInterface.Delete(no) ;
    }
}
