package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VideoCourseController {
    @GetMapping("/video-course")
    public String videoCourse() {
        return "video-course/video_course";
    }
    
}
