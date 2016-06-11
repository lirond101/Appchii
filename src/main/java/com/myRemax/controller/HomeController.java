package com.myRemax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liron_d on 17/03/2016.
 */

@RestController
@RequestMapping(value = "/yuri")
public class HomeController {

    @RequestMapping(value = "/home")
    public static String home(){
        return "test";
    }
}
