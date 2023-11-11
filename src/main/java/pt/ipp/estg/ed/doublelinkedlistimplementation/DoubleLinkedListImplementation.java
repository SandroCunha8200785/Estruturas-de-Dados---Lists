
package pt.ipp.estg.ed.doublelinkedlistimplementation;

import Exceptions.EmptyListException;
import Exceptions.NoSuchElementException;

/**
 *
 * @author SandroCunha8200785
 */
public class DoubleLinkedListImplementation {

    public static void main(String[] args) throws EmptyListException, NoSuchElementException {
         
         UnorderedArrayList<Person> list = new UnorderedArrayList<Person>();
         
         System.out.println(list.toString());
    }
}
