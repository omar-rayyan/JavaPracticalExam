package com.axsosacademy.teams;

import com.axsosacademy.teams.models.LoginUser;
import com.axsosacademy.teams.models.User;
import com.axsosacademy.teams.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private final UserService userService;
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("loggedUser") != null) {
            return "home.jsp";
        }
        else {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult,
            Model model, HttpSession session){
        User loggedUser = userService.register(newUser,bindingResult);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        else {
            session.setAttribute("loggedUser", loggedUser);
            return "redirect:/home";
        }
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult bindingResult,
            HttpSession session,Model model) {
        User loggedUser = userService.login(newLogin, bindingResult);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        else {
            session.setAttribute("loggedUser", loggedUser);
            return "redirect:/home";
        }
    }
}