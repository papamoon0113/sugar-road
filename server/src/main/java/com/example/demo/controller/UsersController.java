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

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersDAO usersDAO;

    //회원가입
    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    @ResponseBody
    public String createUsers(UsersDTO newbieDTO){
        //ModelAndView mav = new ModelAndView();

        if(usersDAO.createUser(newbieDTO)){ //회원 생성 성공 시
            return "회원가입에 성공했습니다";
        } else if(!(usersDAO.createUser(newbieDTO))){
            return "회원가입에 실패했습니다";
        } else {
            return "오류가 발생했습니다";
        }
    }

    //회원 로그인
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginUsers(UsersDTO unknownDTO,
                             HttpServletRequest servletRequest){

        List<UsersDTO> selectIdResult = usersDAO.readUserBy("user_id", unknownDTO.getUserId()); //입력한 아이디 검색 결과

        if(selectIdResult.isEmpty()){
            System.out.println("존재하지 않는 아이디입니다");
            return "존재하지 않는 아이디입니다";
        } else {

            String selectIdPwd = (selectIdResult.get(0)).getUserPassword(); //조회된 아이디의 패스워드

            //System.out.println("조회된 ID의 PW : " + selectIdPwd);

            if (selectIdPwd.compareTo(unknownDTO.getUserPassword()) != 0) { //입력한 패스워드와 계정의 패스워드가 일치하지 않으면
                System.out.println("패스워드가 일치하지 않습니다");
                return "패스워드가 일치하지 않습니다";
            }

            System.out.println("로그인에 성공했습니다");

            //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
            HttpSession session = servletRequest.getSession();

            //로그인 성공한 유저의 ID 정보를 값으로 갖는 "nowLogin" 세션 생성
            session.setAttribute("nowLogin", selectIdResult.get(0).getUserId());

            return "redirect:/mypage"; //로그인에 성공하면 마이페이지로 이동
            //추후 홈.html으로 수정예정
        }

    }

}
