package org.example.Repositories;

import org.example.Model.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerEntityRepository extends CrudRepository<Customers, Long> {

    List<Customers> findByNickname(String nickname);

  //  List<Customers> updateByMoney(int money);
}
