/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author jvm
 */
public class NotMoneyException extends Exception {

    /**
     * Creates a new instance of <code>NotMoneyException</code> without detail
     * message.
     */
    public NotMoneyException() {
       
    }

    /**
     * Constructs an instance of <code>NotMoneyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotMoneyException(String msg) {
        super(msg);
    }
}
