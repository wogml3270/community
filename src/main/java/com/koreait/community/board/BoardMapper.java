package com.koreait.community.board;

import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> selBoardList(BoardDTO dto);
}
