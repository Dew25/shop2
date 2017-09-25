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
public class NotQuantityException extends Exception {

    /**
     * Creates a new instance of <code>NotQuantytyException</code> without
     * detail message.
     */
    public NotQuantityException() {
        
    }

    /**
     * Constructs an instance of <code>NotQuantytyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotQuantityException(String msg) {
        super(msg);
    }
}
