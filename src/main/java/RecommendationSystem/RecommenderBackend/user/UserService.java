package RecommendationSystem.RecommenderBackend.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }



    public void addNewUser(User user) {
        Optional<User> userOptional=userRepository
                .findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Email is already taken");
        }
        userRepository.save(user);
    }

    public String deleteUser(Long userId) {
        String message;
        boolean exists=userRepository.existsById(userId);
        if(!exists){
            message="user with id "+userId+" does not exist";
            throw new IllegalStateException("user with id "+userId+" does not exist");


        }
        else {
            message="user with id "+userId+" was deleted successfully ";
        }
        userRepository.deleteById(userId);
        return message;
    }



//    @Transactional //gia na min kanw query sto repository
//    public User updateUser(Long userId, String firstname,String lastname, String email,String password) {
//        User user=userRepository.findById(userId)
//                .orElseThrow(()->new IllegalStateException(
//                        "user with id "+userId+" does not exist"
//                ));
//        if (firstname!=null && firstname.length()>0 &&!Objects.equals(user.getFirstname(),firstname)){
//            user.setFirstname(firstname);
//        }
//        if (lastname!=null && lastname.length()>0 &&!Objects.equals(user.getLastname(),lastname)){
//            user.setLastname(lastname);
//        }
//        if (password!=null && password.length()>0 &&!Objects.equals(user.getPassword(),password)){
//            user.setPassword(password);
//        }
//
//        if (email!=null && email.length()>0 &&!Objects.equals(user.getEmail(),email)){
//            Optional<User> userOptional=userRepository
//                    .findUserByEmail(email);
//            if(userOptional.isPresent()){
//                throw new IllegalStateException("Email is already taken");
//            }
//            user.setEmail(email);
//        }
//        return userRepository.save(user);
//
//    }
    public User updateUser(User user){
        Long id= user.getId();
        User usr=userRepository.findById(id).get();
        usr.setEmail(user.getEmail());
        usr.setFirstname(user.getFirstname());
        usr.setLastname(user.getLastname());
        usr.setPassword(user.getPassword());
        return userRepository.save(usr);

    }




}
