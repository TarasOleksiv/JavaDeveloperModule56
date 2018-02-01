package ua.goit.java8.javadeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.goit.java8.javadeveloper.service.UserService;

/**
 * Created by t.oleksiv on 01/02/2018.
 */

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/showUsers", method = {RequestMethod.GET,RequestMethod.POST})
    public String showUsers(ModelMap model){
        model.put("list",userService.getAll());
        return "usersList";
    }
}
