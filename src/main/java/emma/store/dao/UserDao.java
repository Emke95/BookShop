package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);

}


