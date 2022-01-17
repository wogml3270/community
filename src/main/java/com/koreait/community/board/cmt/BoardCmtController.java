package com.koreait.community.board.cmt;

import com.koreait.community.board.model.BoardCmtEntity;
import com.koreait.community.board.model.BoardCmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/board/cmt")
public class BoardCmtController {

    @Autowired private BoardCmtService service;

    @PostMapping
    public Map<String, Integer> insBoardCmt(@RequestBody BoardCmtEntity entity){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insBoardCmt(entity));
        return result;
    }
    @GetMapping(value="/{iboard}")
    public List<BoardCmtVO> selBoardCmtList(@PathVariable int iboard){
        System.out.println("iboard : " + iboard);
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        return service.selBoardCmtList(entity);
    }
}
