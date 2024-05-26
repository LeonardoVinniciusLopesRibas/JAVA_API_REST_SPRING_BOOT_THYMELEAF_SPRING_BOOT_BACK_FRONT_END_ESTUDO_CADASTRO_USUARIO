package unipar.aluno.mywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unipar.aluno.mywebapp.exception.UserNotFoundException;
import unipar.aluno.mywebapp.model.User;
import unipar.aluno.mywebapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void save(User user) {

        userRepository.save(user);

    }

    public User get(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Usuário não encontrado com ID "+id);

    }

    public void delete(Long id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Usuário não encontrado com ID "+id);
        }
        userRepository.deleteById(id);
    }
}
