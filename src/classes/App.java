/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Customer;
import entity.History;
import entity.Product;
import java.util.HashSet;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import repository.Repository;

/**
 *
 * @author jvm
 */
public class App {

    public App() {
    }

    public void run() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Shop2PU");
        EntityManager em = emf.createEntityManager();
        try {
            InitDb initDb = new InitDb(em);
            initDb.init();
            
            Repository r = new Repository(em);
            //попробуйте разные id покупателя
            Customer c = r.findCustomerById(4);
            Customer c2 = r.findCustomerById(5);
            // попробуйте разные id продуктов
            Product p = r.findProductById(1);
            Product p2 = r.findProductById(3);
            
            if (c != null && p != null) {
                // протестируйте разныех покупателей с разными продуктами.
                // поэкспирементируйте с количеством покупаемых продутов
                r.purcaseCustomerProduct(c, p, 5);
 
                r.purcaseCustomerProduct(c2, p, 10);
                r.purcaseCustomerProduct(c2, p2, 5);
            }
            String city = "Johvi";
            //найти всех пользователей, которче купили продукт p из city города
            HashSet<Customer> customers = r.findCustomersBuyProductFromCity(p,city);
            Iterator iter = customers.iterator();
            while(iter.hasNext()){
                Customer customer = (Customer) iter.next();
                System.out.println("Товар "+p.getName()+ " из города "+city+ " купил "+customer.getName()+" "+customer.getSurname());
            }
        } catch(Exception e){
            System.out.println("Что то пошло не так! "+e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
