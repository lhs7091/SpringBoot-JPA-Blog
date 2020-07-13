package com.cos.blog.test;

//mport java.util.function.Supplier;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// controller which return data type
@RestController
public class DummyControllerTest {

    @Autowired //DI
    private UserRepository userRepository;

    // {id} parameter can be received {id}
    // http://localhost:8000/blog/dummy/user/id
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

        // if there is no data in database,
        // then null will return.
        // So before we return, it needed to be checked.
        // User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
        //      @Override
        //      public IllegalArgumentException get(){
        //          return new IllegalArgumentException("No Data in Database - id : "+id);
        //      }
        // });
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("No Data in Database - id : "+id);
        });

        // request : html
        // user object : java object
        // conversion to make understanding -> Json
        // but this Spring boot
        // java object return -> call MessageConverter -> call jackson library
        // -> conversion to json -> html
        return user;
    }
    
    @PostMapping("/dummy/join")
    public String join(User user){ // key = value
        System.out.println("id : "+user.getId());
        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getEmail());
        System.out.println("role : "+user.getRole());
        System.out.println("date : "+user.getCreatedate());
        
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입완료!!";
    }

}