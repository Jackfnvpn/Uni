package com.example.send_go.Dao.Operatore;
import com.example.send_go.Entity.Customer;

public interface SendGoDaoOperator {
    public Customer checkCustomer(String email, String password);
    public void closeConnection();
    public int insertCustomer(Customer customer);
}
