package nuc.ss.controller;

import nuc.ss.mapper.AdminHumanResourceMapper;
import nuc.ss.pojo.AdminHumanResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.Data;
import java.util.List;

//前后端分离，记得解决跨域
@RestController
@CrossOrigin
public class AdminHumanResourceController {

    @Autowired
    private AdminHumanResourceMapper adminHumanResourceMapper;

    //增
    //实现管理员设置的增加人力资源档案管理
    @PostMapping("/admin/addAHR")
    ResponseEntity<Object> addAHR(
            @RequestParam("ILevel") String ILevel,
            @RequestParam("IILevel") String IILevel,
            @RequestParam("IIILevel") String IIILevel,
            @RequestParam("PositionType") String PositionType,
            @RequestParam("JobTitle") String JobTitle

    ){
        AdminHumanResource data = new AdminHumanResource(1,ILevel,IILevel,IIILevel,PositionType,JobTitle);
        int adminHumanResource = adminHumanResourceMapper.addAHR(data);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data",adminHumanResource);
        if (adminHumanResource==1){
            modelAndView.addObject("code",200);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        }else {
            modelAndView.addObject("code",404);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    }

    //查
    //根据id获取对应的人事资源档案
    @GetMapping("/admin/getAHR")
    ResponseEntity<Object> getAHR(Integer id){
        AdminHumanResource adminHumanResource = adminHumanResourceMapper.queryAHRById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data",adminHumanResource);
        modelAndView.addObject("code",200);
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    }

    //查找所有管理员定义的人事资源档案
    @GetMapping("/admin/getAllAHR")
    ResponseEntity<Object> getAllAHR(){
        List<AdminHumanResource> adminHumanResource = adminHumanResourceMapper.queryAHRList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(adminHumanResource);
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    }

    //改
    //修改管理员定义的人事资源档案
    @PostMapping("/admin/updateAHR")
    ResponseEntity<Object> updateAHR(
            @RequestParam("id") Integer id,
            @RequestParam("ILevel") String ILevel,
            @RequestParam("IILevel") String IILevel,
            @RequestParam("IIILevel") String IIILevel,
            @RequestParam("PositionType") String PositionType,
            @RequestParam("JobTitle") String JobTitle

    ){
        AdminHumanResource data = new AdminHumanResource(id,ILevel,IILevel,IIILevel,PositionType,JobTitle);
        System.out.println(data);
        int adminHumanResource = adminHumanResourceMapper.updateAHR(data);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data",adminHumanResource);
        if (adminHumanResource==1){
            modelAndView.addObject("code",200);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        }else {
            modelAndView.addObject("code",404);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    }

    //删
    //删除管理员定义的人事管理档案
    @DeleteMapping("admin/deleteAHR")
    ResponseEntity<Object> deleteAHR(Integer id){
        int adminHumanResource = adminHumanResourceMapper.deleteAHR(id);
        ModelAndView modelAndView = new ModelAndView();
        if (adminHumanResource==1){
            modelAndView.addObject("code",200);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        }else {
            modelAndView.addObject("code",404);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    }
}
