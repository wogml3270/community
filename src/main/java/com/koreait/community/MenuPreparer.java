package com.koreait.community;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import java.util.*;
@Component("MenuPreparer")
public class MenuPreparer implements ViewPreparer {
    @Override
    public void execute(Request request, AttributeContext attributeContext) {
        System.out.println(" --- Called MenuPreparer-execute method --- ");
        List<String> menuList = new ArrayList();
        menuList.add("게임");
        menuList.add("연예인");
        menuList.add("뉴스");
        menuList.add("정치");
        menuList.add("스포츠");
        attributeContext.putAttribute(Const.MENU_LIST, new Attribute(menuList), true);
    }
}
