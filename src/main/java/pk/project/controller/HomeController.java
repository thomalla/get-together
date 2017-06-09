package pk.project.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pk.project.model.CurrentUser;
import pk.project.repository.UserRepository;
import pk.project.util.Path.Template;
import pk.project.util.Path.Web;

import java.util.List;


@Controller
public class HomeController
{
    @Autowired
    UserRepository userRepository;

    @RequestMapping(Web.INDEX)
    public String serveIndexPage(Model model, Authentication authentication)
    {

        User user=(authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
        if(user!=null)
        {
           List<pk.project.model.User> list=userRepository.findAll();
            model.addAttribute("list", list);
        }
        return Template.INDEX;
    }

    @RequestMapping(value = Web.FIND, method = RequestMethod.POST)
    public String serveIndexSurnamesPage(@RequestParam String surname, Model model, Authentication authentication)
    {
        User user=(authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
        if(user!=null)
        {
            List<pk.project.model.User> list = userRepository.findBySurname(surname);
            model.addAttribute("list", list);
        }
            return Template.INDEX;

    }


}

