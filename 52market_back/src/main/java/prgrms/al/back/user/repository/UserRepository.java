package prgrms.al.back.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prgrms.al.back.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByNickname(String nickname);

    boolean existsByName(String name);

    boolean existsByNickname(String nickname);
}
