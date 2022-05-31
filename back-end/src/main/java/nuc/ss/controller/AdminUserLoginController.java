package nuc.ss.controller;

import nuc.ss.mapper.AdminUserMapper;
import nuc.ss.pojo.AdminUser;
import nuc.ss.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
public class AdminUserLoginController {

    @Autowired
    private AdminUserMapper adminUserMapper;

    //由于前后端分离，记得解决跨域问题

    //实现登录功能
    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestParam("username") String name,
                                 @RequestParam("password") String password,
                                 HttpSession session) {
        AdminUser adminUser = adminUserMapper.queryUserByName(name);
        ModelAndView modelAndView = new ModelAndView();
        if(adminUser!=null&&adminUser.getPassword().equals(password)){
            //把用户数据放到session中
            session.setAttribute("loginUser",name);
            session.setAttribute("role", adminUser.getRoleId());
            modelAndView.addObject("code",200);
            modelAndView.addObject("msg","登录成功");
            modelAndView.addObject("token",adminUser.getId());
            //返回成功
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        } else {
            //返回失败
            modelAndView.addObject("code",404);
            modelAndView.addObject("msg","登录登录失败");
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    };

    //请求token（id）返回用户数据
    @GetMapping("/getUserInfo")
    ResponseEntity<Object> login(@RequestParam("token") int id) {
        AdminUser adminUser = adminUserMapper.queryUserById(id);
        String rolePlay = new String();
        switch (adminUser.getRoleId()){
            case 0 : {
                rolePlay="admin";
                break;
            }
            case 1 : {
                rolePlay="manager";
                break;
            }
            case 2 : {
                rolePlay="commissioner";
                break;
            }
            case 3 : {
                rolePlay="payCommissioner";
                break;
            }
            case 4 : {
                rolePlay="payManager";
                break;
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        String[] role = {rolePlay};
        UserInfo userInfo = new UserInfo(role,adminUser.getId(),"I am a super administrator","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",adminUser.getName());
        //todo:用data封装数据
        modelAndView.addObject("code",200);
        modelAndView.addObject("data",userInfo);
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    };
}
