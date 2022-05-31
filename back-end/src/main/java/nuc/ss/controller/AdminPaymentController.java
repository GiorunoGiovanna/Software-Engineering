package nuc.ss.controller;

import nuc.ss.mapper.AdminPaymentMapper;
import nuc.ss.pojo.AdminHumanResource;
import nuc.ss.pojo.AdminPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class AdminPaymentController {

    @Autowired
    private AdminPaymentMapper adminPaymentMapper;

    //由于前后端分离，记得解决跨域问题

    //增
    //实现管理员增加薪酬定制规则
    @PostMapping("/admin/addAP")
    ResponseEntity<Object> addAP(
            @RequestParam("AHRid") int AHRid,
            @RequestParam("AHRName") String AHRName,
            @RequestParam("Designee") String Designee,
            @RequestParam("DesigneeTime") String DesigneeTime,
            @RequestParam("Registrant") String Registrant,
            @RequestParam("Base") Float Base,
            @RequestParam("Transportation") Float Transportation,
            @RequestParam("Lunch") Float Lunch,
            @RequestParam("Communication") Float Communication,
            @RequestParam("PensionInsurance") Float PensionInsurance,
            @RequestParam("MedicalInsurance") Float MedicalInsurance,
            @RequestParam("UnemploymentInsurance") Float UnemploymentInsurance,
            @RequestParam("HousingProvidentFund") Float HousingProvidentFund,
            @RequestParam("Tatol") Float Tatol,
            @RequestParam("State") int State,
            @RequestParam("Suggestion") String Suggestion
    ){
        AdminPayment data = new AdminPayment(1,AHRid,AHRName,Designee,DesigneeTime,Registrant,Base,Transportation,Lunch,Communication,PensionInsurance,MedicalInsurance,UnemploymentInsurance,HousingProvidentFund,Tatol,State,Suggestion);
        int result = adminPaymentMapper.addAP(data);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data",result);
        if (result==1){
            modelAndView.addObject("code",200);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        }else {
            modelAndView.addObject("code",404);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    }

    //查
    //根据时间获取对应的薪酬规则
//    @PostMapping("/admin/getAPByTime")
//    ResponseEntity<Object> getAPByTime(
//            @RequestParam("start") Date start,
//            @RequestParam("end") Date end
//    ){
//        List<AdminPayment> adminPayment = adminPaymentMapper.queryAPByTime(start,end);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject(adminPayment);
//        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
//    }

    //根据id获取对应的薪酬规则
    @GetMapping("/admin/getAP")
    ResponseEntity<Object> getAP(
            String keyword,
            String type
    ){
        List<AdminPayment> adminPayment;
        ModelAndView modelAndView = new ModelAndView();
        switch (type){
            case "id":{
                adminPayment = adminPaymentMapper.queryAPById(Integer.parseInt(keyword));
                modelAndView.addObject(adminPayment);
                return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
            }
                case "keyword":{
                adminPayment = adminPaymentMapper.queryAPBykeyword(keyword);
                modelAndView.addObject(adminPayment);
                return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
            }
            case "time":{
                //拆分从前端传来的字符串
                String[] time = keyword.split("/");
                String start = time[0];
                String end = time[1];
                adminPayment = adminPaymentMapper.queryAPByTime(start,end);
                modelAndView.addObject(adminPayment);
                return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    }

    //获取全部薪酬规则
    @GetMapping("/admin/getAllAP")
    ResponseEntity<Object> getAllAP(){
        List<AdminPayment> adminPayment = adminPaymentMapper.queryAPList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(adminPayment);
        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
    }

    //改
    //修改薪酬规则
    @PostMapping("/admin/updateAP")
    ResponseEntity<Object> updateAHR(
            @RequestParam("id") int id,
            @RequestParam("AHRid") int AHRid,
            @RequestParam("AHRName") String AHRName,
            @RequestParam("Designee") String Designee,
            @RequestParam("DesigneeTime") String DesigneeTime,
            @RequestParam("Registrant") String Registrant,
            @RequestParam("Base") Float Base,
            @RequestParam("Transportation") Float Transportation,
            @RequestParam("Lunch") Float Lunch,
            @RequestParam("Communication") Float Communication,
            @RequestParam("PensionInsurance") Float PensionInsurance,
            @RequestParam("MedicalInsurance") Float MedicalInsurance,
            @RequestParam("UnemploymentInsurance") Float UnemploymentInsurance,
            @RequestParam("HousingProvidentFund") Float HousingProvidentFund,
            @RequestParam("Tatol") Float Tatol,
            @RequestParam("State") int State,
            @RequestParam("Suggestion") String Suggestion
    ){
        AdminPayment data = new AdminPayment(id,AHRid,AHRName,Designee,DesigneeTime,Registrant,Base,Transportation,Lunch,Communication,PensionInsurance,MedicalInsurance,UnemploymentInsurance,HousingProvidentFund,Tatol,State,Suggestion);
        System.out.println(data);
        int result = adminPaymentMapper.updateAP(data);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data",result);
        if (result==1){
            modelAndView.addObject("code",200);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        }else {
            modelAndView.addObject("code",404);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    }

    //删
    //删除管理员定义的薪酬规则
    @DeleteMapping("/admin/deleteAP")
    ResponseEntity<Object> deleteAP(Integer id){
        int result = adminPaymentMapper.deleteAP(id);
        ModelAndView modelAndView = new ModelAndView();
        if (result==1){
            modelAndView.addObject("code",200);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
        }else {
            modelAndView.addObject("code",404);
            return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.NOT_FOUND);
        }
    }

    //测试文件上传
//    @PostMapping("/admin/file")
//    ResponseEntity<Object> PostFile(
//            @RequestParam("Image") MultipartFile Image,
//            @RequestParam("data") String data
//    ){
//        System.out.println(data);
//        System.out.println(Image);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("code",Image);
//        return new ResponseEntity<>(modelAndView.getModel(), HttpStatus.OK);
//    }
}
