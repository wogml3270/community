package com.koreait.community;

import com.koreait.community.board.model.BoardCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<BoardCategoryEntity> selMenuCategoryList();

}
