package web;

import domain.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//spring MVC的Controller
@Controller
public class LoginController {
    private UserService userService;

    //处理静态网页/index.html请求
    @RequestMapping(value = "/index.html")
    public String loginPage(){ return "login"; }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser){
//            return new ModelAndView("login","error","用户名或密码错误。");
            return new ModelAndView("logError");
        }
        else{
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIP(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
