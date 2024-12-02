package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.serviceimpl.AdminServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    public ApiResult findAll(){
        System.out.println("查询全部");
        return ApiResultHandler.success(adminService.findAll());
    }

    @GetMapping("/admin/{adminId}")
    public ApiResult findById(@PathVariable("adminId") Integer adminId){
        System.out.println("根据ID查找");
        return ApiResultHandler.success(adminService.findById(adminId));
    }

    @DeleteMapping("/admin/{adminId}")
    public ApiResult deleteById(@PathVariable("adminId") Integer adminId){
        adminService.deleteById(adminId);
        return ApiResultHandler.success();
    }

    @PutMapping("/admin/{adminId}")
    public ApiResult update(@PathVariable("adminId") Integer adminId, Admin admin){
        return ApiResultHandler.success(adminService.update(admin));
    }

    @PostMapping("/admin")
    public ApiResult add(Admin admin){
        return ApiResultHandler.success(adminService.add(admin));
    }

    @GetMapping("/admin/resetPsw/{adminId}/{oldPsw}/{newPsw}")
    public ApiResult resetPsw(@PathVariable("adminId") Integer adminId, @PathVariable("newPsw") String newPsw, @PathVariable("oldPsw") String oldPsw) {
        return ApiResultHandler.success(adminService.resetPsw(adminId, newPsw, oldPsw));
    }
}
