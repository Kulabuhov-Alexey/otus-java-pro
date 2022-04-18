package homework;


import java.util.LinkedList;
import java.util.List;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    private LinkedList<Customer> customerList = new LinkedList<>();

    public void add(Customer customer) {
        customerList.addFirst(customer);
    }

    public Customer take() {
        return customerList.pop();
    }
}
