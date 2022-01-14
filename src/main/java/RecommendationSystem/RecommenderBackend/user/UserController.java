package RecommendationSystem.RecommenderBackend.user;

import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*",allowedHeaders={"*"})
@RestController
@RequestMapping(path="api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserRepository userRepository;



    public UserController(UserService userService) {
        this.userService = userService;


    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

    }

//    @PutMapping(path = "{userId}")
//    public void updateUser(
//            @PathVariable("userId") Long userId,
//            @RequestParam(required = false) String firstname,
//            @RequestParam(required = false) String lastname,
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) String password)
//    {
//            userService.updateUser(userId, firstname,lastname,email,password);
//
//    }

    //get User By Id
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {

        return userRepository.findById(id).get();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return  userService.updateUser(user);
    }

    @JsonIgnore
    @GetMapping({ "/User/{email}/{password}/{role}" })
    public List<User> login(@PathVariable String email,@PathVariable String password,@PathVariable String role) {
        return userRepository.findByEmailAndPasswordAndRole(email, password,role);
    }






}

