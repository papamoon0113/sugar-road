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
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    UsersDAO usersDAO;

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
        //임의로 test01으로 고정하여 테스트
     //List<UsersDTO> nowUserSelect = usersDAO.readUserBy("user_id", "test01");

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

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request){
        //세션 객체가 있으면 새로 생성하지 않고 세션을 가져옴
        HttpSession session = request.getSession(false);

        if(session != null){ //세션이 존재하면
            session.invalidate(); //세션 초기화 (종료 아님)
        }
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
