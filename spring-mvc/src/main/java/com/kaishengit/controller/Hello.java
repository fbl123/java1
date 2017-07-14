package com.kaishengit.controller;

import com.kaishengit.entity.Student;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.plaf.multi.MultiMenuItemUI;
import java.io.*;
import java.util.UUID;


/**
 * Created by Administrator on 2017/7/13.
 */

@Controller
@RequestMapping("/hello")
public class Hello {
    //get请求来的方法
    @RequestMapping(method = RequestMethod.GET)

    public String say(RedirectAttributes redirectAttributes){
        //重定向 RedirectAttributes对象可以将重定向后的数据带到请求转发的页面上
        redirectAttributes.addFlashAttribute("message","成功");
        return "redirect:/hello/list";
    }

    @RequestMapping("/list/{id}")
    public String hhh(@PathVariable String id){
        System.out.println(id);
        return "list";
    }
    @RequestMapping(value = "/list")

    public String hhh(Model model){
        //等同于request.setAttribute();
        model.addAttribute("name","jick");
        return "list";
    }


    //获取参数
    @GetMapping("/where")
    //参数名需要和URL的参数名一致
    public ModelAndView find( Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("list");
        modelAndView.addObject("name","131");
        return  modelAndView;
    }

    public String save(){


        return "list";
    }

    //produces设置响应类型
    @RequestMapping(value="/stu",produces = "application/json;charset=utf-8")
    @ResponseBody//说明是返回值，不是跳转网页
    public Object find(){

        Student stu=new Student();
        stu.setName("jick");
        stu.setAge(18);

        return stu;
    }




    /**
     *   文件上传
     * @return
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    //MultipartFile 可获取文件的信息 参数名与name属性一致
    public String upload(MultipartFile doc)  {

        File file=new File("d:/upload");
        if(!file.exists()){
            file.mkdir();
        }
          if(!doc.isEmpty()){
              //获得文件名
             String name= doc.getOriginalFilename();
              //获得文件大小
              doc.getSize();
              //获得文件类型
              doc.getContentType();
            String token= UUID.randomUUID().toString();
              //获得输入流
              try{

                  file=new File(file,name+token);
                  InputStream in=doc.getInputStream();
                 OutputStream outputStream=new FileOutputStream(file);
                  IOUtils.copy(in,outputStream);
                  outputStream.flush();
                  outputStream.close();
                  in.close();
              }catch (IOException e){

              }

      }


        return "";
    }

}


