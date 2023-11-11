
package pt.ipp.estg.ed.doublelinkedlistimplementation;

import Exceptions.EmptyListException;
import Exceptions.NoMoreElementsException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 *Classe que implementa uma lista generica com recurso a um array
 * @author Sandro Cunha 8200785
 */
public abstract class GenericArrayList<T extends Comparable<T>> implements ListADT<T> {
        
    /*------------CLASSE PRIVADA DO ITERADOR-----------------------------------
    --------------------------------------------------------------------------*/
    
        /**
         * Classe privada do iterador da lista
         */
        private class ArrayListIterator implements Iterator<T> {
            
            /**
             * Index atual da lista
             */
            private int atualIndex = 0;
            
            /**
             * Numero esperado de mudanças na lista
             */
            private int expectedModCount = modCount;
            
            /**
             * MEtodo que testa se o iterador pode remover um elemento
             */
            private boolean okToRemove = false;
            
            /**
             * MEtodo que verifica se existe proximo elemento na lista
             * @return true se existe um proximo elemento
             */
            @Override
            public boolean hasNext() {
                this.checkForConcurrentModification();
                return atualIndex < nElem;
            }
            
            /**
             * Metodo que devolve o proximo elemento na lista
             * @return proximo elemento na lista
             */
            @Override
            public T next(){
                
                this.checkForConcurrentModification();
                
                if (!this.hasNext()) {
                    try {
                        throw new NoMoreElementsException();
                    } catch (NoMoreElementsException ex) {
                    }
                }
                
                this.okToRemove = true;
                return (T) array[atualIndex++];
            }
            
            /**
             * MEtodo que permite ao iterador remover um elemento da lista
             */
            @Override
            public void remove() {
                
                this.checkForConcurrentModification();

                if (!this.okToRemove) {
                    throw new IllegalStateException("Iterator.remove() só pode ser chamado após next()");
                }
                
                try {
                    GenericArrayList.this.remove(array[atualIndex - 1]);
                } catch (EmptyListException ex) {
                    System.out.println(ex);
                }
            
                this.okToRemove = false;
            }
            
            /**
             * Metodo que testa se foram realizadas mudanças concorrentes na lista
             */
            private void checkForConcurrentModification() {
                
                if ( modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException("Modificações concorrentes detetadas.");
                }
            }
        }
    
    
    //-----------ATRIBUTOS DE CLASSE-------------------------------------------
    //-------------------------------------------------------------------------
    
    /**
     * Capacidade por defeito do array;
     */
    private static final int DEFAULT_CAPACITY = 10;
    
    
    
    /*-------------------------ATRIBUTSO DE INSTANCIA---------------------------
    ------------------------------------------------------------------------*/
    
    
    /**
     * Array da lista
     */
    protected T[] array;
    
    /**
     * Numero de elementos efetivos na lista
     */
    private int nElem;
    
    /**
     * Rastreador de modificações na lista
     */
    private int modCount;
    
    
    
    /*-----------------------------CONSTRUTORES------------------------------------
    ----------------------------------------------------------------------------*/
    
    
    /**
     * Construtor vazio que inicializa a lista com um tamanho por defeito
     */
    public GenericArrayList() {
        this.array = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.nElem = 0;
        this.modCount = 0;
    }
    
    
    /**
     * Construtor que inicializa a lista com um tamanho passado por parametro
     * @param capacity capacidade inicial da lista
     */
    public GenericArrayList(int capacity) {
        this.array = (T[]) new Comparable[capacity];
        this.nElem = 0;
        this.modCount = 0;
    }
    
    
    /*----------------METODOS DE INSTANCIA-------------------------------------
    --------------------------------------------------------------------------*/
    
    
    /**
     * Devolve o numero de modificações ocorridas na lista
     * @return modCount
     */
    public int getModCount() {
        return modCount;
    }
    
    /**
     * MEtodo que permite incrementar o numero de elementos da lista
     */
    public void incNElem() {
        this.nElem++;
    }
    
    /**
     * Metodo que permite incrementar o contador de modificações na lista
     */
    public void incModCount() {
        this.modCount++;
    }

    
    //----------------METODOS DA INTERFACE LISTADT------------------------------
    //--------------------------------------------------------------------------

    
    
    /**
     * MEtodo que remove o primeiro elemento da lista
     * @return elemento removido
     * @throws EmptyListException a lista encontra-se vazia 
     */
    @Override
    public T removeFirst() throws EmptyListException {
        
        if (isEmpty()) throw new EmptyListException();

        T removedElement = (T) array[0];
        
        for(int i = 0; i < this.nElem - 1; i++){
            this.array[i] = this.array[i + 1];
        }
        
        this.array[this.nElem - 1] = null;
        this.nElem--;
        this.modCount++;
        
        return removedElement;
    }

    
    /**
     * Metodo que permite eliminar o ultimo elemento da lista
     * @return elemento eliminado
     * @throws EmptyListException erro de lista vazia 
     */
    @Override
    public T removeLast() throws EmptyListException {
        
        if (isEmpty()) throw new EmptyListException();

        T removedElement = (T) this.array[this.nElem - 1];
        array[this.nElem - 1] = null;
        
        this.nElem--;
        this.modCount++;

        return removedElement;
    }

    
    /**
     * Metodo que remove o elemento passado por parametro da lista
     * @param element elemento a remover
     * @return elemento removido
     * @throws EmptyListException erro de lista vazia 
     */
    @Override
    public T remove(T element) throws EmptyListException {
        
        if (isEmpty()) throw new EmptyListException();

        for (int i = 0; i < this.nElem; i++) {
            
            if (this.array[i].equals(element)){
                
                T removedElement = (T) array[i];
                
                for(int j = i; j < this.nElem - 1; j++){
                    this.array[j] = this.array[j + 1];
                }
                
                array[this.nElem - 1] = null;
                this.nElem--;
                this.modCount++;
                
                return removedElement;
            }
        }
        
        //Elemento nao estava na lista
        return null;
    }
    
    
    /**
     * Metodo que devolve o primeiro elemento da lista
     * @return primeiro elemento da lista
     * @throws EmptyListException lista vazia
     */
    @Override
    public T first() throws EmptyListException {
        
        if (isEmpty()) throw new EmptyListException();
        return (T) this.array[0];
    
    }
    
    
    /**
     * Metodo que devolve o ultimo elemento da lista
     * @return ultimo elemento da lista
     * @throws EmptyListException lista vazia
     */
    @Override
    public T last() throws EmptyListException {
        
        if (isEmpty()) throw new EmptyListException();
        return (T) this.array[this.nElem - 1];
    
    }

    
    /**
     * Metodo que verifica a existencia de um elemento target na lista 
     * @param target elemento a procurar
     * @return true se target esta na lista
     */
    @Override
    public boolean contains(T target) {
        
        for (int i = 0; i < this.nElem; i++){
            
            if (array[i].equals(target)){
                return true;
            }
            
        }
        
        return false;
    }
    
    
    /**
     * Metodo que verifica se a lista esta vazia
     * @return true se a lista esta vazia
     */
    @Override
    public boolean isEmpty(){
        return this.nElem == 0;
    }
    
    /**
     * Metodo que devolve o numero efetivo de elementos na lista
     * @return numero efetivo de elementos na lista
     */
    @Override
    public int size() {
        return this.nElem;
    }
    
    /**
     * Metodo que invoca um novo interador da lista
     * @return 
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    
    /**
     * MEtodo que representa textualmente os dados da lista
     * @return String com os dados na lista
     */
    @Override
    public String toString() {
        
        if (isEmpty()) {
            return "\nLista Vazia\n";
        }

        StringBuilder s = new StringBuilder("\n");
        
        s.append("Nr de elementos: ").append(this.nElem).append("\n");
        s.append("Primeiro: ").append(array[0].toString()).append("\n");
        s.append("Último: ").append(array[this.nElem - 1].toString()).append("\n");

        for (int i = 0; i < this.nElem; i++) {
            s.append("Elemento: ").append(array[i].toString()).append("\n");
        }

        return s.toString();
    }

    
    
    
    
    
    //----------------------METODOS AUXILIARES----------------------------------
    //_--------------------------------------------------------------------------
    
    /**
     * Metodo aulixiar que extende a capacidade do array
     */
   public T[] expandCapacity() {
        int newCapacity = array.length * 2;
        return Arrays.copyOf(array, newCapacity);
    }

}

