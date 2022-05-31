package nuc.ss.controller;

import nuc.ss.mapper.AdminUserMapper;
import nuc.ss.mapper.HumanResourceMapper;
import nuc.ss.pojo.AdminHumanResource;
import nuc.ss.pojo.AdminPayment;
import nuc.ss.pojo.AdminUser;
import nuc.ss.pojo.HumanResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.sql.Blob;
import java.util.List;


@RestController
@CrossOrigin
public class HumanResourceController {

    @Autowired
    private HumanResourceMapper humanResourceMapper;

    //由于前后端分离，记得解决跨域问题

    //增加人事管理具体内容
    @PostMapping("/addHR")
    ResponseEntity<String> addHR(
            @RequestParam("AHRid") int AHRid,
            @RequestParam("name") String name,
            @RequestParam("sex") String sex,
            @RequestParam("tel") String tel,
            @RequestParam("Email") String Email,
            @RequestParam("QQ") String QQ,
            @RequestParam("Phone") String Phone,
            @RequestParam("Location") String Location,
            @RequestParam("ZipCode") String ZipCode,
            @RequestParam("Nationality") String Nationality,
            @RequestParam("Birthplace") String Birthplace,
            @RequestParam("Birthday") String Birthday,
            @RequestParam("Nation") String Nation,
            @RequestParam("Belief") String Belief,
            @RequestParam("PoliticalOutlook") String PoliticalOutlook,
            @RequestParam("IDNumber") String IDNumber,
            @RequestParam("SocialSecurityNumber") String SocialSecurityNumber,
            @RequestParam("Age") String Age,
            @RequestParam("Education") String Education,
            @RequestParam("Major") String Major,
            @RequestParam("SalaryStandard") String SalaryStandard,
            @RequestParam("AccountOpening") String AccountOpening,
            @RequestParam("AccountNumber") String AccountNumber,
            @RequestParam("Specialty") String Specialty,
            @RequestParam("Hobbies") String Hobbies,
            @RequestParam("PersonalResume") String PersonalResume,
            @RequestParam("FamilyRelationshipInformation") String FamilyRelationshipInformation,
            @RequestParam("Note") String Note,
            @RequestParam("Registrant") String Registrant,
            @RequestParam("RegistrationTime") String RegistrationTime,
            @RequestParam("State") int State,
            @RequestParam("Image") String Image,
            HttpSession session
    ){
        System.out.println(session);
        if(session==null){
            return new ResponseEntity<>("没有权限！", HttpStatus.NOT_FOUND);
        }
        HumanResource data = new HumanResource(1,AHRid,name,sex,Email,tel,QQ,Phone,Location,ZipCode,Nationality,Birthplace,Birthday,Nation,Belief,PoliticalOutlook,IDNumber,SocialSecurityNumber,Age,Education,Major,SalaryStandard,AccountOpening,AccountNumber,Specialty,Hobbies,PersonalResume,FamilyRelationshipInformation,Note,Registrant,RegistrationTime,State,Image);
        System.out.println(data);
        int result = humanResourceMapper.addHR(data);
        if (result==1){
            return new ResponseEntity<>("添加成功！", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("添加失败", HttpStatus.NOT_FOUND);
        }
    }

    //查
    //查询人事管理具体表
    //查找所有管理员定义的人事资源档案
    @GetMapping("/getAllHR")
    ResponseEntity<Object> getAllHR(){
        List<HumanResource> humanResources = humanResourceMapper.queryHRList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(humanResources);
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    }

    //按id查询人事管理
    @GetMapping("/getHR")
    ResponseEntity<Object> getHR(Integer id){
        HumanResource humanResource = humanResourceMapper.queryHRById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(humanResource);
        System.out.println(humanResource);
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    }

    //改
    //修改人事管理表
    @PostMapping("/updateHR")
    ResponseEntity<String> updateHR(
            @RequestParam("id") int id,
            @RequestParam("AHRid") int AHRid,
            @RequestParam("name") String name,
            @RequestParam("sex") String sex,
            @RequestParam("tel") String tel,
            @RequestParam("Email") String Email,
            @RequestParam("QQ") String QQ,
            @RequestParam("Phone") String Phone,
            @RequestParam("Location") String Location,
            @RequestParam("ZipCode") String ZipCode,
            @RequestParam("Nationality") String Nationality,
            @RequestParam("Birthplace") String Birthplace,
            @RequestParam("Birthday") String Birthday,
            @RequestParam("Nation") String Nation,
            @RequestParam("Belief") String Belief,
            @RequestParam("PoliticalOutlook") String PoliticalOutlook,
            @RequestParam("IDNumber") String IDNumber,
            @RequestParam("SocialSecurityNumber") String SocialSecurityNumber,
            @RequestParam("Age") String Age,
            @RequestParam("Education") String Education,
            @RequestParam("Major") String Major,
            @RequestParam("SalaryStandard") String SalaryStandard,
            @RequestParam("AccountOpening") String AccountOpening,
            @RequestParam("AccountNumber") String AccountNumber,
            @RequestParam("Specialty") String Specialty,
            @RequestParam("Hobbies") String Hobbies,
            @RequestParam("PersonalResume") String PersonalResume,
            @RequestParam("FamilyRelationshipInformation") String FamilyRelationshipInformation,
            @RequestParam("Note") String Note,
            @RequestParam("Registrant") String Registrant,
            @RequestParam("RegistrationTime") String RegistrationTime,
            @RequestParam("State") int State,
            @RequestParam("Image") String Image
    ){
        HumanResource data = new HumanResource(id,AHRid,name,sex,Email,tel,QQ,Phone,Location,ZipCode,Nationality,Birthplace,Birthday,Nation,Belief,PoliticalOutlook,IDNumber,SocialSecurityNumber,Age,Education,Major,SalaryStandard,AccountOpening,AccountNumber,Specialty,Hobbies,PersonalResume,FamilyRelationshipInformation,Note,Registrant,RegistrationTime,State,Image);
        int result = humanResourceMapper.updateHR(data);
        if (result==1){
            return new ResponseEntity<>("修改成功！", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("修改失败", HttpStatus.NOT_FOUND);
        }
    }

    //删
    //删除人事管理表
    @DeleteMapping("/deleteHR")
    ResponseEntity<String> deleteHR(Integer id){
        int result = humanResourceMapper.deleteHR(id);
//        int result = humanResourceMapper.updateHR()
        if (result==1){
            return new ResponseEntity<>("删除成功！", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("删除失败", HttpStatus.NOT_FOUND);
        }
    }
}
