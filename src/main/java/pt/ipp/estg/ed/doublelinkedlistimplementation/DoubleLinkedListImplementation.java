
package pt.ipp.estg.ed.doublelinkedlistimplementation;

import Exceptions.EmptyListException;
import Exceptions.NoSuchElementException;

/**
 *
 * @author sssan
 */
public class DoubleLinkedListImplementation {

    public static void main(String[] args) throws EmptyListException, NoSuchElementException {
         Person p1 = new Person("Ana" , 20);
         Person p2 = new Person("Andre" , 20);
         Person p3 = new Person("Carlos" , 20);
         Person p4 = new Person("Carolina" , 20);
         Person p5 = new Person("Diana" , 20);
         Person p6 = new Person("Diogo" , 20);
         Person p7 = new Person("Joao" , 20);
         Person p8 = new Person("Jose" , 20);
         Person p9 = new Person("Maria" , 20);
         Person p10 = new Person("Mario" , 20);
         Person p11 = new Person("Nuno" , 20);
         Person p12 = new Person("Nadia" , 20);
         Person p13 = new Person("Orlando" , 20);
         Person p14 = new Person("Olga" , 20);
         Person p15 = new Person("Sandro" , 20);
         Person p16 = new Person("Fernanda" , 20);
         Person p17 = new Person("Sara" , 20);
         Person p18 = new Person("Susana" , 20);
         Person p19 = new Person("Vasco" , 20);
         Person p20 = new Person("Valeria" , 20);
         
         UnorderedDoubleLinkedList<Person> list = new UnorderedDoubleLinkedList<Person>();
         
         list.addToFront(p20);
         list.addToFront(p19);
         list.addToFront(p15);
         list.addToRear(p1);
         list.addAfter(p2, p20);
         list.addAfter(p4, p15);
         
         list.removeFirst();
         list.removeLast();
         list.remove(p20);
         System.out.println(list.toString());
    }
}
