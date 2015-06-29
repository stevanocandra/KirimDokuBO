/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kirimdoku.bo.controllers;

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
//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public String index(ModelMap map){
//        System.out.print("Masuk");
//        map.addAttribute("hello", "Hello Spring from DefaultController");
//        return "index";
//    }
//    
//    @RequestMapping(value="/person", method = RequestMethod.GET)
//    public String person(ModelMap map){
//        System.out.print("Masuk Person");
//        map.addAttribute("person", "This is Persons");
//        return "person";
//    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String free(ModelMap map){
        System.out.print("Masuk Free");
        map.addAttribute("free", "Hello from Freemarker");
        return "index";
    }
}
