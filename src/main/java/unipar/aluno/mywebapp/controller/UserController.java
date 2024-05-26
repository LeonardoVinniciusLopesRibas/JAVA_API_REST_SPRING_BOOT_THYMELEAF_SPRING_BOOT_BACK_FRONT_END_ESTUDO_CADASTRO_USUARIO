package unipar.aluno.mywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import unipar.aluno.mywebapp.exception.UserNotFoundException;
import unipar.aluno.mywebapp.model.User;
import unipar.aluno.mywebapp.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showingUserList(Model model){
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);

        return "users";

    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Adiciona novo usuário");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        userService.save(user);
        ra.addFlashAttribute("message", "O usuário foi salvo com sucesso!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable ("id") Long id, Model model, RedirectAttributes ra) {
        try{
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edita Usuário: ID "+id);
            return "user_form";
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable ("id") Long id, Model model, RedirectAttributes ra) {
        try{
            userService.delete(id);
            ra.addFlashAttribute("message", "Usuário deletado com sucesso");
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";

    }

}
