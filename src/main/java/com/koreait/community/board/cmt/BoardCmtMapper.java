package com.koreait.community.board.cmt;

import com.koreait.community.board.model.BoardCmtEntity;
import com.koreait.community.board.model.BoardCmtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity entity);
    List<BoardCmtVO> selBoardCmtList(BoardCmtEntity entity);
}
