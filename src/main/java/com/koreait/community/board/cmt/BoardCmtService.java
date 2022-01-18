package com.koreait.community.board.cmt;

import com.koreait.community.UserUtils;
import com.koreait.community.board.model.BoardCmtEntity;
import com.koreait.community.board.model.BoardCmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {

    @Autowired private UserUtils userUtils;
    @Autowired private BoardCmtMapper mapper;

    public int insBoardCmt(BoardCmtEntity entity){ // iboard, ctnt 만 담겨있다가
        entity.setIuser(userUtils.getLoginUserPk()); // iboard, ctnt, iuser 로 담겼다가
        mapper.insBoardCmt(entity); // icmt, iboard, ctnt, iuser 로 담긴다
        return entity.getIcmt();
    }

    public List<BoardCmtVO> selBoardCmtList(int iboard){
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        return mapper.selBoardCmtList(entity);
    }

    public int delBoardCmt(int icmt){
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIcmt(icmt);
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.delBoardCmt(entity);
    }
}
