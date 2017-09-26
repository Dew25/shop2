/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Customer;
import entity.History;
import entity.Product;
import exceptions.NotMoneyException;
import exceptions.NotQuantityException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jvm
 */
public class Repository {
    EntityManager em;
    
    public Repository() {
    }

    public Repository(EntityManager em) {
        this.em = em;
    }
//    Customer
    public Customer findCustomerById(int id) {
        try {
            return (Customer) em.createQuery("SELECT c FROM Customer c WHERE c.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        } catch (Exception e) {
            System.out.println("Не найден пользователь с id = "+id + " "+e.getMessage());
            return null;
        }
       
    }
    
 //   Product
    public Product findProductById(int i) {
        try {
            return (Product) em.createQuery("SELECT p FROM Product p WHERE p.id = :id")
                .setParameter("id", i)
                .getSingleResult();
        } catch (Exception e) {
            System.out.println("Не найден продукт с id=" + i + " " + e.getMessage());
            return null;
        }
    }
    
    
 //  Puchases
   
    public void purcaseCustomerProduct(Customer c, Product p, int i) throws NotQuantityException, NotMoneyException{
        Calendar date = new GregorianCalendar();
        History h = new History(date.getTime(),c,p,i);
      
            
            if(p.getQuantity()-i < 0){
                throw new NotQuantityException("Товар с id = "+p.getId()+" "+p.getName()+": не хватило для пользователя c id = "+c.getId());
            }
            p.setQuantity(p.getQuantity()-i);
            
            if(c.getMoney()-p.getPrice()*i < 0){
                throw new NotMoneyException("Покупатель с id = "+c.getId()+": нет денег для покупки товара "+p.getName() + " в количестве (шт) "+i);
            }
            c.setMoney(c.getMoney()-p.getPrice()*i);
           em.getTransaction().begin();
            em.merge(p);
            em.merge(c);
            em.persist(h);
           em.getTransaction().commit();
           System.out.println("Покупатель "+c.getName()+" "+c.getSurname()+" купил товар: \""+p.getName()+"\" - "+i+" шт.");
        
    }
    
    public HashSet<Customer> findCustomersBuyProductFromCity(Product p, String city) {
        try {
            List<History> histories = em.createQuery("SELECT h FROM History h WHERE h.customer.city=:city AND h.product=:product")
                .setParameter("city", city)
                .setParameter("product", p)
                .getResultList();
            HashSet<Customer> customers = new HashSet<>();
            for (int i = 0; i < histories.size(); i++) {
                History history = histories.get(i);
                customers.add(history.getCustomer());
            }
            return customers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
