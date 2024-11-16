package iuh.backend.repositories;

import iuh.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.mobile = :loginParam OR u.email = :loginParam")
    User findByMobileOrEmail(String loginParam);
}