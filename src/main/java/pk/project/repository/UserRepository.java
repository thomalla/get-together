package pk.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pk.project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneById(Long id);
    Optional<User> findOneByLogin(String login);
    List<User> findBySurname(String surname);

}
