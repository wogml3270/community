package com.koreait.community.board.cmt;

import com.koreait.community.board.model.BoardCmtEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity entity);
}
