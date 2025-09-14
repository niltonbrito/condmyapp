package com.bandampla.condmyapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        System.out.println(status);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
                case 400:
                    return "error/400";
                case 403:
                    return "error/403";
                case 404:
                    return "error/404";
                case 500:
                    return "error/404";
            }
        }
        return "error/error"; // fallback genérico
    }
}
