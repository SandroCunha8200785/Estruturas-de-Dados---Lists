
package pt.ipp.estg.ed.doublelinkedlistimplementation;

/**
 * Classe que implementa uma lista ordenada
 * @author SandroCunha 8200785
 */

public class OrderedArrayList<T extends Comparable<T>> 
        extends GenericArrayList<T> implements OrderedListADT<T> {

    //-----------------------COSTRUTORES---------------------------------------
    //_------------------------------------------------------------------------
    
    /**
     * Cosntrutor com tamanho por defeito
     */
    public OrderedArrayList(){
        
        super();
        
    }
    
    /**
     * Construtor com tamanho definido por parametro
     * @param capacity 
     */
    public OrderedArrayList(int capacity){
        super(capacity);
    }
    
    
    //-------------METODOS DA INTERFACE-----------------------------------------
    //_------------------------------------------------------------------------
    
    
    /**
     * Adiciona o elemento à lista ordenadamente usando o método compareTo.
     *
     * @param element o elemento a ser adicionado à lista
     */
    @Override
    public void add(T element) {
    
            if (element == null) {
                throw new IllegalArgumentException("Elemento inexistente");
            }
    
            // Expandir o array se necessário
            if (this.size() == this.array.length){
                this.array = this.expandCapacity();
            }
    
            int index = 0;
                while (index < this.size() && element.compareTo(this.array[index]) > 0){
                    index++;
            }

            for (int i = this.size(); i > index; i--) {
                this.array[i] = this.array[i - 1];
            }

            this.array[index] = element;

            this.incNElem();
            this.incModCount();
        }
}

