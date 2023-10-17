package com.example.demo.controller;

import com.example.demo.dao.MenuDAO;
import com.example.demo.dao.StoreDAO;
import com.example.demo.domain.MenuDTO;
import com.example.demo.domain.StoreDTO;
import com.example.demo.util.ImageUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class StoreController {
    @Autowired
    StoreDAO dao;
    @Autowired
    MenuDAO mdao;
    @Autowired
    ServletContext context;
    @Autowired
    ImageUtil imageUtil;
    ModelAndView mav = new ModelAndView();

    @GetMapping("/store") // 글목록 출력
    public ModelAndView readStore() {
        // 게시물 제목, (작성자 아이디), 조회수, 댓글수, 좋아요, 이미지가 담기고 최신순으로 3개가 담긴 storeDTO배열
        List<StoreDTO> list = dao.readStore();
        if (list.size() != 0) {
            mav.addObject("list", list);
        } else {
            mav.addObject("msg", "추출된 결과가 없습니다.");
        }
        mav.setViewName("store/store");
        return mav;
    }

    @GetMapping("/store/detail") // 가게 상세정보 출력
    public ModelAndView readSelectStore(int storeId) {
        // store , menu 따로 불러와서 내보내기
       StoreDTO list = dao.readSelectStoreBy(storeId);
       List<MenuDTO> mlist = mdao.readMenuBy(storeId);
        mav.addObject("mlist", mlist);
        mav.addObject("list", list);
        mav.setViewName("store/detail");
        return mav;
    }
    @GetMapping("/store/write") // 글작성 페이지 출력
    public String writePage() {
        return "/store/write";}
    @PostMapping("/store/write") // 가게 등록
    public ModelAndView createStore(StoreDTO dto, String[] menuName, MultipartRequest mreq ) {
        String menuImagePath="";
        MenuDTO menuDto;
        MultipartFile storeFile = mreq.getFile("file"); // 가게 대표 이미지
        List<MultipartFile> menuList = mreq.getFiles("menuImages"); // 메뉴 이미지들
        String storeImagePath  = imageUtil.writeImage(storeFile);
        dto.setStoreImagePath(storeImagePath);
        int result = dao.createStore(dto);
        if (result>0 && menuName.length>0) {
            for (int i =0; i < menuList.size(); i++) {
                menuImagePath  = imageUtil.writeImage(menuList.get(i));
                menuDto = new MenuDTO();
                menuDto.setMenuImagePath(menuImagePath);
                menuDto.setStoreId(dto.getStoreId());// 앞서 저장한 가게 ID를 설정
                menuDto.setMenuName(menuName[i]); // 메뉴 이름 저장
                boolean menuResult = mdao.createMenu(menuDto); // 메뉴 정보를 저장
                if (!menuResult ) {
                    mav.addObject("msg", "메뉴 정보 저장에 실패했습니다.");
                    mav.setViewName("store/write");
                    return mav;
                }
            }
            mav.addObject("msg", "가게 및 메뉴가 정상적으로 등록되었습니다.");
        } else {
            mav.addObject("msg", "가게 정보 저장에 실패했습니다.");
        }
        mav.addObject("list", dao.readStore());
        mav.setViewName("store/store");
        return mav;
    }
    @GetMapping("/store/editView") // 수정할 가게 내용 내보내기
    public ModelAndView ViewEditStore(int storeId) {
        StoreDTO list = dao.readSelectStoreBy(storeId);
        List<MenuDTO> menuList  = mdao.readMenuBy(storeId);
        list.setMenuList(menuList);
        mav.addObject("list", list);
        mav.setViewName("store/edit");
        return mav;
    }
// 이전
    @PostMapping("/store/edit") // 가게 수정
    public ModelAndView updateStore(StoreDTO dto) {
        boolean result = dao.updateStore(dto);
        if (result) {
            mav.addObject("list", dao.readStore());
        } else {
            mav.addObject("msg", "가게 수정을 실패했습니다.");
        }
        mav.setViewName("store/store");
        return mav;
    }
    @GetMapping("/store/delete") // 가게 삭제(모달창으로 확인받기)
    public ModelAndView deleteStore(int storeId) {
        boolean result = dao.deleteStore(storeId);
        if (result) {
            mav.addObject("list", dao.readStore());
        } else {
            mav.addObject("msg", "추출된 결과가 없습니다.");
        }
        mav.setViewName("store/store");
        return mav;
    }
}