package ido.style.controller;

import ido.style.dto.ProductDTO;
import ido.style.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/upload")
    public void get_upload(){}

    @PostMapping("/upload")
    public String post_upload(ProductDTO productDTO){
        adminService.add_product(productDTO);
        return "redirect:/admin/upload";
    }

}
