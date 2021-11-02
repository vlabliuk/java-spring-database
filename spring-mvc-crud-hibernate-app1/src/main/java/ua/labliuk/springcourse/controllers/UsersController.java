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
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    //inject DAO
    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //show all Users
    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userDAO.index());
        return "users/index";
    }

    //show User with {id}
    @RequestMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDAO.show(id));
        return "users/show";
    }

    //pass new User to users/new form
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user){
        return "users/new";
    }

    //get new User from users/new form
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "users/new";
        userDAO.save(user);
        return "redirect:/users";
    }

    //pass user with {id} to users/edit form
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userDAO.show(id));
        return "users/edit";
    }

    //update User with data from users/edit
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id, Model model){
        if(bindingResult.hasErrors())
            return "users/edit";
        user.setAccount_id(id);
        userDAO.update(user);
        return "redirect:/users";
    }

    //delete User with {id}
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userDAO.delete(id);
        return "redirect:/users";
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
