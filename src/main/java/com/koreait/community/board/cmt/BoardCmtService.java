package com.koreait.community.board.cmt;

import com.koreait.community.UserUtils;
import com.koreait.community.board.model.BoardCmtEntity;
import com.koreait.community.board.model.BoardCmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {

    @Autowired private UserUtils userutils;
    @Autowired private BoardCmtMapper mapper;

    public int insBoardCmt(BoardCmtEntity entity){
        entity.setIuser(userutils.getLoginUserPk());
        return mapper.insBoardCmt(entity);
    }

    public List<BoardCmtVO> selBoardCmtList(BoardCmtEntity entity){
        return mapper.selBoardCmtList(entity);
    }
}
