package com.example.demo.controller;

import com.example.demo.dao.UsersDAO;
import com.example.demo.domain.UsersDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyPageController {
    @Autowired
    UsersDAO usersDAO;

    @RequestMapping("/mypage")
    public ModelAndView userInfo(HttpSession session){
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/mypage/index");

     //현재 유저 정보를 DB조회하는 코드
        //임의로 test01으로 고정하여 테스트
     //List<UsersDTO> nowUserSelect = usersDAO.readUserBy("user_id", "test01");

     String nowLoginId = (String) session.getAttribute("nowLogin");
     System.out.println(nowLoginId);
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
        String result = editUser.getUserId() + " 회원의<br>";

        if(editUser.getUserPassword() != ""){ //패스워드가 입력되어 있으면
            if(usersDAO.updateUserPassword(editUser.getUserPassword(), editUser.getUserId())){ //성공 시
                result += "패스워드<br>";
            }
        }

        if(editUser.getUserName() != ""){ //이름이 입력되어 있으면
            if(usersDAO.updateUserName(editUser.getUserName(), editUser.getUserId())){ //성공 시
                result += "이름<br>";
            }
        }

        if(editUser.getNickname() != ""){ //닉네임이 입력되어 있으면
            if(usersDAO.updateUserNick(editUser.getNickname(), editUser.getUserId())){ //성공 시
                result += "별명<br>";
            }
        }

        if(editUser.getUserEmail() != ""){ //메일이 입력되어 있으면
            if(usersDAO.updateUserEmail(editUser.getUserEmail(), editUser.getUserId())){ //성공 시
                result += "메일<br>";
            }
        }

        return result += "정보가 수정되었습니다";
    }

    @RequestMapping(value = "/logOut")
    public String logOut(HttpServletRequest servletRequest){
        //세션 불러오되 세션이 있으면 생성하지 않기 (false)
        HttpSession session = servletRequest.getSession(false);
        if(session != null){
            session.invalidate(); //세션 종료
        }

        return "/users/login.html"; //로그인 화면으로 돌아감
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    @ResponseBody
    public String usersDelete(UsersDTO deleteUser,
                              HttpServletRequest servletRequest){
        List<UsersDTO> selectPwdId = usersDAO.readUserBy("user_password", deleteUser.getUserPassword());

        if(selectPwdId.isEmpty()){ //입력한 패스워드와 일치하는 회원정보가 조회되지 않으면
            System.out.println("패스워드를 다시 입력해주세요");
            return "패스워드를 다시 입력해주세요";
        } else {
            String deleteId = selectPwdId.get(0).getUserId(); //조회된 리스트 중 첫번째행 유저ID 추출

            if(usersDAO.deleteUser(deleteId)){ //리스트 첫번째행 유저ID의 정보를 삭제
                //세션 불러오되 세션이 있으면 생성하지 않기 (false)
                HttpSession session = servletRequest.getSession(false);
                if(session != null){
                    session.invalidate(); //세션 종료
                }
               System.out.println("탈퇴되었습니다");
                return "redirect:/users/login.html"; //로그인 화면으로 이동
            }
        }
        return "오류가 발생했습니다";
    }

}
