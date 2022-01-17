package com.koreait.community.board;

import com.koreait.community.Const;
import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardPrevNextVO;
import com.koreait.community.board.model.BoardVO;
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
    // 유저 IP 를 가져왔을 때 IPv6문제, IPv4로 전환. (이클립스) - 퍼옴
    // TODO : https://postitforhooney.tistory.com/entry/WEBWAS-%EC%9C%A0%EC%A0%80-IP-%EB%A5%BC-%EA%B0%80%EC%A0%B8%EC%99%94%EC%9D%84-%EB%95%8C-IPv6%EB%AC%B8%EC%A0%9C-IPv4%EB%A1%9C-%EC%A0%84%ED%99%98-%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4
    public void detail(BoardDTO dto, Model model, HttpServletRequest req){
        String lastIp = req.getHeader(Const.IP_FORWARD);
        if(lastIp == null){
            lastIp = req.getRemoteAddr();
        }
        System.out.println("lastIp : " + lastIp);
        dto.setLastip(lastIp);
        BoardVO vo = service.selBoard(dto);
        BoardPrevNextVO pnVo = service.selPrevNext(vo);
        model.addAttribute(Const.DATA, vo);
        model.addAttribute(Const.PREV_NEXT, pnVo);
    }

    @GetMapping(value="/mod")
    public String mod(BoardDTO dto, Model model){
        model.addAttribute(Const.DATA, service.selBoard(dto));
        return "board/write";
    }

    @PostMapping(value="/mod")
    public String modProc(BoardEntity entity){
        int result = service.updBoard(entity);
        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }

    @GetMapping(value="/del")
    public String delProc(BoardEntity entity){
        int result = service.delBoard(entity);
        return "redirect:/board/list/" + entity.getIcategory();
    }

}
