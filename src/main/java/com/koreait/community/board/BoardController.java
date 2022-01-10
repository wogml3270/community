package com.koreait.community.board;

import com.koreait.community.Const;
import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping(value="/list")
    public void list(){}

    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, BoardDTO dto, Model model) {
        model.addAttribute(Const.I_CATEGORY, icategory);
        model.addAttribute(Const.LIST, service.selBoardList(dto));
        dto.setIcategory(icategory);
        return "board/list";
    }

    @GetMapping(value="/write")
    public void write(){}

    @PostMapping(value="/write")
    public String writeProc(BoardEntity entity) {
        int result = service.insBoard(entity);
        return "redirect:/board/list/" + entity.getIcategory();
    }

    @GetMapping(value="/detail")
    public void detail(BoardDTO dto, Model model, HttpServletRequest req){
        String lastIp = req.getHeader(Const.IP_FORWARD);
        if(lastIp == null){
            lastIp = req.getRemoteAddr();
        }
        dto.setLastip(lastIp);
        model.addAttribute(Const.DATA, service.selBoard(dto));
    }

    @GetMapping(value="/del")
    public String delProc(BoardEntity entity){
        int result = service.delBoard(entity);
        return "redirect:/board/list/" + entity.getIcategory();
    }
}
