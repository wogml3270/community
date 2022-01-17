package com.koreait.community.board;

import com.koreait.community.UserUtils;
import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardPrevNextVO;
import com.koreait.community.board.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private UserUtils userUtils;

    public int insBoard(BoardEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoard(entity);
    }

    public List<BoardVO> selBoardList(BoardDTO dto){
        return mapper.selBoardList(dto);
    }
    public BoardVO selBoard(BoardDTO dto){ // iboard, lastip
        BoardVO detail = mapper.selBoard(dto);
        if(dto.getLastip() != null && !Objects.equals(dto.getLastip(), detail.getLastip())){
            int hitsResult = mapper.addHits(dto);
            if(hitsResult == 1){
                detail.setHits(detail.getHits() + 1);
            }
        }
        return detail;
    }
    public BoardPrevNextVO selPrevNext(BoardVO vo){
        return mapper.selPrevNext(vo);
    }

    public int updBoard(BoardEntity entity){
        try{
            entity.setIuser(userUtils.getLoginUserPk());
            return mapper.updBoard(entity);
        }catch(Exception e){
            e.printStackTrace();
            return 2;
        }
    }

    public int delBoard(BoardEntity entity){ // icategory, iboard
        entity.setIuser(userUtils.getLoginUserPk());
        entity.setIsdel(1);
        return mapper.updBoard(entity); // icategory, iboard, iuser, isdel
    }
}
