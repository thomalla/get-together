package pk.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pk.project.form.RegisterForm;
import pk.project.model.User;
import pk.project.service.UserService;
import pk.project.util.Path.Template;
import pk.project.util.Path.Web;
import pk.project.validator.RegisterValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController
{
    private final UserService userService;
    private final RegisterValidator registerValidator;

    @Autowired
    public UserController(UserService userService, RegisterValidator registerValidator)
    {
        this.userService = userService;
        this.registerValidator = registerValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registerValidator);
    }


    @RequestMapping(value = Web.LOGIN, method = RequestMethod.GET)
    public String serveLoginPage(Model model, @RequestParam Optional<String> error)
    {
        model.addAttribute("error",error);
        return Template.LOGIN;
    }

    @RequestMapping(value = Web.REGISTER, method = RequestMethod.GET)
    public String serveRegisterPage(Model model)
    {
        model.addAttribute("errors", new ArrayList<ObjectError>());
        return Template.REGISTER;
    }



    @RequestMapping(value = Web.REGISTER, method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("form")RegisterForm form,BindingResult bindingResult, Model model)
    {
        List<ObjectError> errorList;
        User user;

        if(bindingResult.hasErrors())
        {
            errorList=bindingResult.getAllErrors();
            model.addAttribute("errors",errorList);
            return Template.REGISTER;
        }
        try
        {
            user = userService.register(form);
        }
        catch(DataIntegrityViolationException e)
        {
            errorList=new ArrayList<>();
            errorList.add(new ObjectError("email.exists","Email istnieje już w bazie"));
            model.addAttribute("errors",errorList);
            return Template.REGISTER;
        }
        return Template.REGISTER_SUCCESS;
    }

    
}
