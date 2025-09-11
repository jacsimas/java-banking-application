package org.example.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.Model.Customers;
import org.example.Repositories.CustomerEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerEntityService {

    CustomerEntityRepository customerEntityRepository;
    TransferCalculator transferCalculator;
    final Logger log = LoggerFactory.getLogger(CustomerEntityService.class);

    public CustomerEntityService(CustomerEntityRepository customerEntityRepository, TransferCalculator transferCalculator){
    this.customerEntityRepository = customerEntityRepository;
    this.transferCalculator = transferCalculator;
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

    public void updateSenderReceiver(long sender, long receiver, int money){
        Customers senderCustomer = customerEntityRepository.findById(sender)
                .orElseThrow(EntityNotFoundException::new);
        Customers receiverCustomer = customerEntityRepository.findById(receiver)
                .orElseThrow(EntityNotFoundException::new);
        int senderAmount = senderCustomer.getMoney();
        int receiverAmount = receiverCustomer.getMoney();
        if (senderAmount > money){
            int senderUpdatedAmount = transferCalculator.sendMoneyTransaction(senderAmount, money);
            int receiverUpdatedAmount = transferCalculator.receiveMoneyTransaction(receiverAmount, money);
            senderCustomer.setMoney(senderUpdatedAmount);
            receiverCustomer.setMoney(receiverUpdatedAmount);
        }
        else {
            log.error("Transfer wasn't successful, not enough money in sender's account: {}, amount to send: {}", senderAmount, money);
        }
    }

    public String saveNewCustomer(Customers customer){
        customerEntityRepository.save(customer);
        return "The customer had been saved!";

    }
}
