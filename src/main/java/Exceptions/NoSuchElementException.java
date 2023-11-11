
package Exceptions;

/**
 *Tratamento de exceçoes para elementos nao existentes na lista
 * @author SandroCunha8200785
 */
public class NoSuchElementException extends Exception{
    
    public NoSuchElementException() {
        
        super("O elemento não existe na lista.");
    
    }
    
}
