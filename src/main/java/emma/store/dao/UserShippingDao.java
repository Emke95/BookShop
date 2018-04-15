package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.UserShipping;

@Repository
public interface UserShippingDao extends JpaRepository<UserShipping, Long> {

}
