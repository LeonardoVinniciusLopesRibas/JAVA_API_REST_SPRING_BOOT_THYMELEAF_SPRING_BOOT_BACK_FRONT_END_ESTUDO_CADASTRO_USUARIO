package unipar.aluno.mywebapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import unipar.aluno.mywebapp.model.User;
import unipar.aluno.mywebapp.repository.UserRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

   @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("felipe.239689");
        user.setName("Fe");
        user.setPassword("a");

        User userSalvo = userRepository.save(user);
        Assertions.assertThat(userSalvo).isNotNull();
        Assertions.assertThat(userSalvo.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = userRepository. findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
       Long id = 1L;
       Optional<User> optionalUser = userRepository.findById(id);
       User user = optionalUser.get();
       user.setPassword("CP1205RM3M1");
       userRepository.save(user);

       User updaterUser = userRepository.findById(id).get();

       Assertions.assertThat(updaterUser.getPassword()).isEqualTo("CP1205RM3M1");
    }

    @Test
    public void testGetById(){
       Long id = 1L;
       Optional<User> optionalUser = userRepository.findById(id);
       Assertions.assertThat(optionalUser).isPresent();
       System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Long id = 1L;
        userRepository.deleteById(id);
        Optional<User> optionalUser = userRepository.findById(id);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
