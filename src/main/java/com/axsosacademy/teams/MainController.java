package com.axsosacademy.teams;

import com.axsosacademy.teams.models.LoginUser;
import com.axsosacademy.teams.models.Team;
import com.axsosacademy.teams.models.User;
import com.axsosacademy.teams.services.TeamService;
import com.axsosacademy.teams.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private TeamService teamService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	if (session.getAttribute("loggedUser") != null) {
            return "redirect:/home";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") != null) {
        	model.addAttribute("teams", teamService.findAll());
            return "home.jsp";
        }
        else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/teams/new")
    public String newTeam(Model model, HttpSession session) {
    	if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        model.addAttribute("team", new Team());
        return "new_team.jsp";
    }
    
    @PostMapping("/teams/new")
    public String createTeam(@Valid @ModelAttribute Team team, BindingResult bindingResult, HttpSession session) {
    	team.setCreator((User)session.getAttribute("loggedUser"));
    	teamService.createTeam(team,bindingResult);
        if (bindingResult.hasErrors()) {
            return "new_team.jsp";
        }
        return "redirect:/home";
    }
    
    @GetMapping("/teams/{id}")
    public String viewTeam(@PathVariable Long id, Model model, HttpSession session) {
    	if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        return "view_team.jsp";
    }
    
    @GetMapping("/teams/{id}/edit")
    public String viewEditBurger(@PathVariable Long id, Model model, HttpSession session) {
    	if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        Team team = teamService.findById(id);
        model.addAttribute("team", team);
        return "edit_team.jsp";
    }
    
    @PostMapping("/teams/{id}/addPlayer")
    public String addPlayerToTeam(@PathVariable Long id, @RequestParam(value="name") String name) {
    	teamService.addPlayer(id, name);
    	return "redirect:/teams/" + id;
    }
    
    @PostMapping("/teams/edit/{id}")
    public String updateBurger(@Valid @ModelAttribute("team") Team team, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "edit_team.jsp";
        } else {
            teamService.updateTeam(team);
            return "redirect:/home";
        }
    }
    
    @PostMapping("/teams/{id}/delete")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return "redirect:/home";
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