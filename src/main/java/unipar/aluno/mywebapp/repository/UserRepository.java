package unipar.aluno.mywebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unipar.aluno.mywebapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Long countById (Long id);



}
