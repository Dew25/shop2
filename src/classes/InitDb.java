/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Customer;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jvm
 */
public class InitDb {
    
    private EntityManager em;
    
    public InitDb() {
    }

    public InitDb(EntityManager em) {
        this.em = em;
    }
    public void init(){
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Ivan", "Ivanov", 10000L, "45454545", "Kohtla-Jarve"));
        customers.add(new Customer("Peter", "Petrov", 10000L, "45454445", "Johvi"));
        customers.add(new Customer("Andrey", "Andreyev", 10000L, "45565445", "Johvi"));
        customers.add(new Customer("Priit", "Tomme", 10000L, "45546786", "Puru"));
        customers.add(new Customer("Nadezda", "Nikolayeva", 10000L, "45754345", "Narva"));
        
        List<Product> products = new ArrayList<>();
        products.add(new Product("Хлеб", 200L, 10));
        products.add(new Product("Кефир", 300L, 10));
        products.add(new Product("Колбаса", 400L, 10));
        products.add(new Product("Яйца 10 шт", 150L, 10));
        products.add(new Product("Молоко", 130L, 10));
         
        em.getTransaction().begin();
            for (int i = 0; i < customers.size(); i++) {
                Customer customer = customers.get(i);
                em.persist(customer);
            }
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                em.persist(product);
            }
        em.getTransaction().commit();
    }
    
}
