package emma.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emma.store.entity.UserPayment;


@Repository
public interface UserPaymentDao extends JpaRepository<UserPayment, Long>{

}
