package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.dto.Result;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/disk")
public class DiskController {

    @Autowired
    private DiskService diskService;


    /**
     * 显示文件夹
     */
    @GetMapping
    public String show(Model model,@RequestParam(name = "_",defaultValue = "0") String pid){

        List<Disk> diskList=diskService.findByPid(pid);
        if(!pid.equals(0)){
            Disk disk=diskService.findById(pid);
            model.addAttribute("disk",disk);
        }
        model.addAttribute("diskList",diskList);
        return "disk/home";
    }

    /**
     * 添加文件夹
     */
    @PostMapping("/new/folder")
    @ResponseBody
    public Result save(Disk disk){
        Disk disk1=diskService.findByPidAndName(disk.getName(),disk.getPid());
        if(disk1!=null){
            return Result.error("该文件夹已存在");
        }
            disk.setType(Disk.File_DIR);
            diskService.save(disk);
            List<Disk> diskList=diskService.findByPid(disk.getPid().toString());
            return Result.success(diskList);
    }
    /**
     * 重命名
     */
    @PostMapping()
    public Result rename(Disk disk){
        disk.setUpdateTime(new Date());
        diskService.update(disk);
        List<Disk> diskList=diskService.findByPid(disk.getPid().toString());
        return Result.success(diskList);
    }
    /**
     * 删除
     */
    @GetMapping("/del/{id:\\d+}")
    public String  rename(@PathVariable(name = "id",required = true) Integer id){
        Disk disk=diskService.findById(id.toString());
        List<Disk> diskList=diskService.findByPid(disk.getId().toString());
        if(diskList==null){
            diskService.del(disk);
        }else{
            diskList.add(disk);
            diskService.del(diskList.toArray());
        }


        return "redirect:/disk?_="+disk.getPid();

//        List<Disk> diskList=diskService.findByPid(disk.getPid().toString());
//        return Result.success(diskList);
    }

    /**
     * 上传文件
     *
     */
    @PostMapping("upload")
    @ResponseBody
    public Result upload(MultipartFile file,Integer pid,Integer accountId) throws IOException {
        String name=file.getOriginalFilename();
        long size= file.getSize();
        InputStream input=file.getInputStream();
        String size1=FileUtils.byteCountToDisplaySize(size);
        Disk disk=new Disk();
        disk.setName(name);
        disk.setAccountId(accountId);
        disk.setPid(pid);
        disk.setSize(size1);
        diskService.upload(disk,input);
        List<Disk> diskList=diskService.findByPid(disk.getPid().toString());
        return Result.success(diskList);
    }
    /**
     * 下载显示
     */
    @GetMapping("/download/{id:\\d+}")
    public void download(@PathVariable(name = "id") Integer id, HttpServletResponse response) throws IOException {
        Disk disk=diskService.findById(id.toString());
        OutputStream outputStream=response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment; filename=\""+ disk.getSaveName());
        diskService.downLoad(disk,outputStream);


    }

}
