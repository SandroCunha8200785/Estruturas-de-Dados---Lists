
package Exceptions;

/**
 *Tratamento de excepções quando a lista não tem mais elementos a processar
 * @author Sandro Cunha 8200785
 */
public class NoMoreElementsException extends Exception{
    
    public NoMoreElementsException() {
        
        super("A lista não tem mais elementos a processar.");
    
    }
    
}
