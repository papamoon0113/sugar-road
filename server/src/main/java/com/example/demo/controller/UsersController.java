package com.example.demo.controller;

import com.example.demo.dao.UsersDAO;
import com.example.demo.domain.UsersDTO;
import com.example.demo.util.ImageUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class UsersController {
    @Autowired
    UsersDAO usersDAO;
    @Autowired
    ImageUtil imageUtil;

    @GetMapping({"/users/login", "/users/login.html"})
    public String loginPage(){
        return "users/login";
    }

    @GetMapping("/users/signup")
    public String signupPage(){
        return "users/signup";
    }

    //회원가입
    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String createUsers(UsersDTO newbieDTO,
                              Model model){

        MultipartFile imageFile = newbieDTO.getImage();

        if(!(imageFile.isEmpty())) { //입력된 파일이 없지 않으면 이미지 삽입 실행
            String userImagePath = imageUtil.writeImage(imageFile);
            newbieDTO.setUserImagePath(userImagePath);
        }

        //입력한 아이디 중복 확인
        List<UsersDTO> searchId = usersDAO.readUserBy("user_id", newbieDTO.getUserId());
        if(searchId.isEmpty()){ //중복된 아이디 조회 결과가 없으면
            if(usersDAO.createUser(newbieDTO)) { //회원 생성
                System.out.println("회원가입에 성공했습니다");
                return "redirect:/users/login";
            }
        } else {
            System.out.println("입력된 아이디와 중복되는 아이디가 있습니다");
            model.addAttribute("msg", "중복되는 아이디가 존재합니다");

            return "/users/signup";
        }
        System.out.println("오류가 발생했습니다");
        return "redirect:/users/signup";
    }

    //회원 로그인
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String loginUsers(UsersDTO unknownDTO,
                           HttpServletRequest servletRequest,
                           Model model){
        List<UsersDTO> selectIdResult = usersDAO.readUserBy("user_id", unknownDTO.getUserId()); //입력한 아이디 검색 결과

        if(selectIdResult.isEmpty()){
            System.out.println("존재하지 않는 아이디입니다");
            //session.setAttribute("statusMsg", "존재하지 않는 아이디입니다");
            model.addAttribute("msg", "존재하지 않는 아이디입니다");
            return "/users/login";
        }

            String selectIdPwd = (selectIdResult.get(0)).getUserPassword(); //조회된 아이디의 패스워드

            //System.out.println("조회된 ID의 PW : " + selectIdPwd);

            if (selectIdPwd.compareTo(unknownDTO.getUserPassword()) != 0) { //입력한 패스워드와 계정의 패스워드가 일치하지 않으면
                System.out.println("패스워드가 일치하지 않습니다");
                //session.setAttribute("statusMsg", "패스워드가 일치하지 않습니다");
                model.addAttribute("msg", "패스워드가 일치하지 않습니다");
                return "/users/login";
            } else {

                System.out.println("로그인에 성공했습니다");

                //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
                HttpSession session = servletRequest.getSession();

                //로그인 성공한 유저의 ID 정보를 값으로 갖는 "nowLogin" 세션 생성
                session.setAttribute("nowLogin", selectIdResult.get(0).getUserId());
                //session.setAttribute("statusMsg", "로그인에 성공하였습니다");
                session.setAttribute("status", "loging");

                //로그인에 성공하면 마이페이지로 이동
                //추후 홈.html으로 수정예정
                return "redirect:/mypage";
            }
    }

}
