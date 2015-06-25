/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirimdoku.bo.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author stevano
 */
@Controller
public class DefaultController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(ModelMap map){
        System.out.print("Masuk");
        map.addAttribute("hello", "Hello Spring from DefaultController");
        return "index";
    }
}
