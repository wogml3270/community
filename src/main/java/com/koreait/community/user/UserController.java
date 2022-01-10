package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value="/login")
    public void login() {}

    @PostMapping(value="/login")
    public String loginProc(UserEntity entity, RedirectAttributes reAttr) {
        int result = service.login(entity);
        if(result != 1) {
            reAttr.addFlashAttribute(Const.TRY_LOGIN, entity);
            switch(result) {
                case 0:
                    reAttr.addFlashAttribute(Const.MSG, Const.ERR_A);
                    break;
                case 2:
                    reAttr.addFlashAttribute(Const.MSG, Const.ERR_ID);
                    break;
                case 3:
                    reAttr.addFlashAttribute(Const.MSG, Const.ERR_PW);
                    break;
            }
            return "redirect:/user/login";
        }
        return "redirect:/board/list";
    }

    @GetMapping(value="/logout")
    public void logout(){}

    @GetMapping(value="/join")
    public void join() {}

    @PostMapping(value="/join")
    public String joinProc(UserEntity entity, RedirectAttributes reAttr) {
        int result = service.join(entity);
        if(result == 0) {
            reAttr.addFlashAttribute(Const.MSG, Const.ERR_JOIN);
            return "redirect:/user/join";
        }
        // TODO 회원가입 성공하면 로그인 처리
        service.login(entity);
        return "redirect:/board/list";
    }

    @GetMapping(value="/idChk/{uid}")
    @ResponseBody
    public Map<String, Integer> idChk(@PathVariable String uid) {
        Map<String, Integer> res = new HashMap();
        res.put("result", service.idChk(uid));
        return res;
    }
}