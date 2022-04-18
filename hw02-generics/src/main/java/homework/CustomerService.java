package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private TreeMap<Customer, String> custumerShelter = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> smallest = custumerShelter.firstEntry();
        return smallest == null ? null : Map.entry(smallest.getKey().clone(), smallest.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> next = custumerShelter.higherEntry(customer);
        return next == null ? null : Map.entry(next.getKey().clone(), next.getValue());
    }

    public void add(Customer customer, String data) {
        custumerShelter.put(customer, data);
    }
}
