package com.example.day17sol.Controller;

import com.example.day17sol.Model.User;
import com.example.day17sol.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<User> userList=userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String msg=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        userService.addAllUsers(user);
        return ResponseEntity.status(200).body("Added user");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, Errors errors,@PathVariable Integer id){
        if(errors.hasErrors()){
            String msg=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isUpdated= userService.updateUser(user,id);

        if(isUpdated){
            return ResponseEntity.status(200).body("User updated");
        }
        return ResponseEntity.status(400).body("not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        boolean isDeleted= userService.deleteUser(id);

        if(isDeleted){
            return ResponseEntity.status(200).body("User deleted");
        }
        return ResponseEntity.status(400).body("not found");
    }

}
