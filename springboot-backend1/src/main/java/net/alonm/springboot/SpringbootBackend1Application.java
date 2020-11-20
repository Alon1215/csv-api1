package net.alonm.springboot;

import net.alonm.springboot.model.User;
import net.alonm.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackend1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackend1Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new User("Alon","Michaeli", "amichaeli@dalet.com"));
        this.userRepository.save(new User("Yael","Jannai", "yjannai@dalet.com"));
        this.userRepository.save(new User("Tom","Levy", "tom@dalet.com"));


    }
}
