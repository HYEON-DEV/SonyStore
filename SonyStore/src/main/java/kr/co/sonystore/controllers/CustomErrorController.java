package kr.co.sonystore.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {
    private String VIEW_PATH = "/error/";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());
            log.debug("아아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ : " + statusCode);

            if(statusCode == 404){
                return VIEW_PATH + "error_404";
            }
            if(statusCode == 500){
                return VIEW_PATH + "error_404";
            }
            if(statusCode == 400){
                return VIEW_PATH + "error_404";
            }
        }
        return "error";
    }
}