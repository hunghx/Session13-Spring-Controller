package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
// đường dẫn mapping là "/"
public class HomeController {
    private final String uploadPath ="C:\\Users\\hung1\\OneDrive\\Desktop\\Session13-Spring-Controller\\src\\main\\webapp\\WEB-INF\\upload\\";

//    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping
    public String index(){
        return "index";
    }
    @GetMapping("/upload/{id}/name/{username}") // người dùng gõ /upload/5/name/hunghx => id =5
    public String handleGetId(@PathVariable("id") Long id,@PathVariable("username") String name, Model model){
        model.addAttribute("id_detail",id);
        model.addAttribute("name",name);
        return "detail";
    }
    @GetMapping("/regex/{email:[a-z]{3,}}")
    public String regexPattern(@PathVariable("email") String email, Model model){
        model.addAttribute("email" ,email);
        return "regex";
    }
    @GetMapping("/param") // /param?name=hunghx&age=24
    public String handleParam(@RequestParam("name") String name,@RequestParam("age") int age,Model model){
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        return "param";
    }
    @PostMapping("/param")
    public String handlePost(@RequestParam("name") String name,@RequestParam("age") int age,Model model){
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        return "param";
    }
    @GetMapping("/upload")
    public String up(){
        return "upload";
    }
    @PostMapping("/upload-image")
    public String handleUpload(@RequestParam("mainimage")MultipartFile mainImage, @RequestParam("subimage")List<MultipartFile> listImage, Model model){
        // up load ảnh
        List<String> images = new ArrayList<>();
        // lấy ra tên ảnh của ảnh chính
        String fileName = mainImage.getOriginalFilename();
        images.add(fileName);
        try {
            FileCopyUtils.copy(mainImage.getBytes(),new File(uploadPath+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //upload nhiều ảnh phụ
        for (MultipartFile file: listImage
             ) {
            // lấy ra ten file
            String name = file.getOriginalFilename();
            images.add(name);
            try {
                FileCopyUtils.copy(file.getBytes(),new File(uploadPath+name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        model.addAttribute("list",images);
        return "image";
    }
}
