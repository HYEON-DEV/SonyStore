package kr.co.sonystore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EventController {
    
    @GetMapping("/events/event")
    public String event() {
        return "events/event";
    }

    @GetMapping("/events/event_detail")
    public String eventDetail() {
        return "events/event_detail";
    }
}
