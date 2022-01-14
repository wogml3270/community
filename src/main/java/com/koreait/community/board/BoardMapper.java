package com.koreait.community.board;

import com.koreait.community.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity entity);
    List<BoardVO> selBoardList(BoardDTO dto);
    BoardVO selBoard(BoardDTO dto);
    BoardPrevNextVO selPrevNext(BoardVO vo);
    int addHits(BoardDTO dto);
    int updBoard(BoardEntity dto);
}
