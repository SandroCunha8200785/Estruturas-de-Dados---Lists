
package Exceptions;

/**
 *Tratamento de excepções quando a lista encontra-se vazia
 * @author Sandro Cunha 8200785
 */
public class EmptyListException extends Exception{
    
    public EmptyListException() {
        
        super("A lista não tem qualquer elemento.\n");
    
    }
    
}
