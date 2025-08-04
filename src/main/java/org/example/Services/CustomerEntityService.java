package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.Model.Customers;
import org.example.Model.TransactionAudit;
import org.example.Repositories.CustomerEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerEntityService {

    CustomerEntityRepository customerEntityRepository;

    public CustomerEntityService(CustomerEntityRepository customerEntityRepository){
    this.customerEntityRepository = customerEntityRepository;
    }

    public List<Customers> allCustomers(){
        return (List<Customers>) customerEntityRepository.findAll();
    }

    public Optional<Customers> auditById(long id) {
        return customerEntityRepository.findById(id);
    }

    public List<Customers> byNickname(String nickname){
    //TODO: to find ID somehow by nickname
        return customerEntityRepository.findByNickname(nickname);
    }

    public Customers getCustomersbyid(long id){
        return customerEntityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Row " + id + " not found"));
    }

    public void changeAmount(long id, int money){
    Customers customer = customerEntityRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        int amount = customer.getMoney();
        money = amount + money;
        if (money >= amount) {
            customer.setMoney(money);
        }
    }

}
