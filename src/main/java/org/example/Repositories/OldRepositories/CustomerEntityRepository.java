package org.example.Repositories.OldRepositories;

import org.example.Model.OldModels.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerEntityRepository extends CrudRepository<Customers, Long> {

    List<Customers> findByNickname(String nickname);

  //  List<Customers> updateByMoney(int money);
}
