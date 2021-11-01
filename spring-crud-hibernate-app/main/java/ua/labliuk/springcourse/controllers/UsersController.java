package ua.labliuk.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.labliuk.springcourse.dao.UserDAO;
import ua.labliuk.springcourse.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class UsersController {

    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userDAO.index());
        return "people/index";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", userDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User user){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/new";
        userDAO.save(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", userDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id, Model model){
        if(bindingResult.hasErrors())
            return "people/edit";
        user.setAccount_id(id);
        userDAO.update(user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userDAO.delete(id);
        return "redirect:/people";
    }
    @GetMapping("/greetingpage")
    public String enter(){
        return "/hellopage";
    }

    @GetMapping("/firstpage")
    public String firstPage(){
        return "/firstpage";
    }
}
