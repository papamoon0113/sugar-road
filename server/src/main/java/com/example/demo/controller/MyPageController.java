package com.example.demo.controller;

import com.example.demo.dao.UsersDAO;
import com.example.demo.domain.UsersDTO;
import com.example.demo.util.ImageUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MyPageController {

    @Autowired
    UsersDAO usersDAO;
    @Autowired
    ImageUtil imageUtil;

    @RequestMapping({"/mypage", "/mypage/edit", "/mypage/delete"})
    public ModelAndView userInfo(HttpSession session,
                                 HttpServletRequest request){
     ModelAndView mav = new ModelAndView();
     String requestUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        //리퀘스트 받은 URL 정보
     if(requestUrl.equals("/mypage")){ //mypage에서 리퀘스트 받으면
         mav.setViewName("/mypage/index"); //mypage/index로 전달
     }

     if(requestUrl.equals("/mypage/edit")){ //mypage/edit에서 리퀘스트 받으면
         mav.setViewName("/mypage/edit"); //mypage/edit로 전달
     }

     if(requestUrl.equals("/mypage/delete")){ //mypage/delete에서 리퀘스트 받으면
         mav.setViewName("/mypage/delete"); //mypage/delete로 전달
     }

     //현재 유저 정보를 DB조회하는 코드
     String nowLoginId = (String) session.getAttribute("nowLogin");
     System.out.println("현재 로그인 중인 ID : " + nowLoginId); //현재 로그인 중인 ID
     List<UsersDTO> nowUserSelect = usersDAO.readUserBy("user_id", nowLoginId);

     if(nowUserSelect.isEmpty()){
         mav.addObject("msg", "유저 정보를 불러오지 못했습니다");
     } else {
         mav.addObject("userInfo", nowUserSelect.get(0));
     }
     return mav;
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    @ResponseBody
    public String usersEdit(UsersDTO editUser){ //id, pw, 이름, 별명, 이메일 정보 받고

        MultipartFile imageFile = editUser.getImage();
        String userImagePath = imageUtil.writeImage(imageFile);
        editUser.setUserImagePath(userImagePath);

        if(usersDAO.updateUserAll(editUser)){
            return editUser.getUserId() + "회원의 정보가 수정되었습니다";
        } else {
            return "회원정보 수정에 실패했습니다";
        }

    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request){
        //세션 객체가 있으면 새로 생성하지 않고 세션을 가져옴
        HttpSession session = request.getSession(false);

        if(session != null){ //세션이 존재하면
            session.invalidate(); //세션 초기화 (종료 아님)
        }
        System.out.println("로그아웃");
        return "redirect:/users/login.html"; //로그인 화면으로 돌아감
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    @ResponseBody
    public String usersDelete(UsersDTO deleteUser,
                              HttpServletRequest request){
        System.out.println("탈퇴하려는 회원 ID : " + deleteUser.getUserId());

        List<UsersDTO> selectDeleteId = usersDAO.readUserBy("user_id", deleteUser.getUserId());
        //삭제하려는 회원정보 조회

        if(!(selectDeleteId.get(0).getUserPassword().equals(deleteUser.getUserPassword()))){
            //입력한 패스워드와 일치하지 않으면
            System.out.println("패스워드를 다시 입력해주세요");
            return "패스워드를 다시 입력해주세요";
        } else {
            if(usersDAO.deleteUser(deleteUser.getUserId())){ //삭제하려는 유저의 정보를 삭제
                //세션 객체가 있으면 새로 생성하지 않고 세션을 가져옴
                HttpSession session = request.getSession(false);

                if(session != null){ //세션이 존재하면
                    session.invalidate(); //세션 초기화
                }

               System.out.println("탈퇴되었습니다");
                return "redirect:/users/login.html"; //로그인 화면으로 이동
                //@ResponseBody 어노테이션 제거 예정
            }
        }
        return "오류가 발생했습니다";
    }

}
