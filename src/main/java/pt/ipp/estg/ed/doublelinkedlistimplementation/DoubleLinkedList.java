
package pt.ipp.estg.ed.doublelinkedlistimplementation;

import Exceptions.EmptyListException;
import Exceptions.NoMoreElementsException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Classe abstrata que implementa uma lista com nós biligados genérica
 * @author SandroCunha8200785
 */
public abstract class DoubleLinkedList<T> implements ListADT<T>{
    
    
    
    
    
    //---------------CLASSE PRIVADA DE NOS DA LISTA----------------------------
    //-------------------------------------------------------------------------
    
    /**
     * Classe dos nós bi-ligados da lista
     * @param <T> Tipo parametrizado
     */
    public static class BiNode<T>{
        
        /**
         * Ligação ao nó anterior
         */
        private BiNode<T> prev;
        /**
         * Ligação ao nó seguinte
         */
        private BiNode<T> next;
        /**
         * Elemento referenciado pelo nó
         */
        private T elem;
        
        /**
         * Construtor do nó, recebe o elemento e coloca as ligações a apontarem para null
         * @param pElem elemento a ser referenciado pelo nó
         */
        public BiNode(T pElem){
            
           this.elem = pElem;
           this.prev = this.next = null;
        
        }
        
        /**
         * Getter do no anterior
         * @return no anterior
         */
        public BiNode<T> getPrev() {
            return prev;
        }
        
        /**
         * Define um novo no anterior
         * @param prev 
         */
        public void setPrev(BiNode<T> prev) {
            this.prev = prev;
        }
        
        /**
         * Devolve o no seguinte
         * @return no seguinte
         */
        public BiNode<T> getNext() {
            return next;
        }
        
        /**
         * Define o proximo no
         * @param next proximo no
         */
        public void setNext(BiNode<T> next) {
            this.next = next;
        }
        
        /**
         * Devolve o elemento guardado no no
         * @return 
         */
        public T getElem() {
            return elem;
        }
        
        /**
         * Coloca um novo elemento no no
         * @param elem 
         */
        public void setElem(T elem) {
            this.elem = elem;
        }
        
        
        
    }
    
    
    
    
    
    
    
    //-------------CLASSE PRIVADA DO ITERADOR DA LISTA--------------------------
    //-------------------------------------------------------------------------
    
    private class DoublyLinkedListIterator implements Iterator<T> {
        
        /**
         * Atributo que inicializa na cabeça da lista
         */
        private BiNode<T> atual = head; 
        
        /**
         *Atributo que guarda o valor esperado de modificações na lista
         */
        private int expectedModCount = modCount;
        
        /**
         * Flag para indicar se é seguro remover um elemento
         */
        private boolean okToRemove = false; 
        
        
        /**
         * Verifica se o proximo elemento a ser processado não é null
         * De relembrar que o proximo elemento a ser processado é aquele para onde 
         * o atributo atual aponta, e não o seu .next. Apenas depois de ser processado
         * é que atual passa para o proximo
         * @return true se o elemento for diferente de null
         */
        @Override
        public boolean hasNext() {
            
            this.checkForConcurrentModification();
            return this.atual != null; 
        }
        
        /**
         * Processa o proximo elemento. O proximo elemento a ser processado é aquele que 
         * se encontra apontado por atual. Apos o seu processamento, atual aponta para o seguinte.
         * @return Elemento a ser processado pelo iterador
         */
        @Override
        public T next() {
            
            this.checkForConcurrentModification();
            
            //Não existe elemento a processar
            if (!this.hasNext()) {
               
                try {
                    //Lança a excepção
                    throw new NoMoreElementsException();
                } catch (NoMoreElementsException ex) {
                }
            }

            T elem = atual.elem; 
            this.atual = this.atual.next; 
            this.okToRemove = true;
            return elem;
        }
        
        /**
         * Metodo para remover um elemento pelo iterador
         */
        @Override
        public void remove(){
            
            this.checkForConcurrentModification();

            if (!this.okToRemove) {
                throw new IllegalStateException("Iterator.remove() só pode ser chamado após next()");
            }

            
            this.okToRemove = false;
        }
        
        /**
         * Metodo que verifica se o numero de modificações atuais na lista é o 
         * esperado pelo iterador
         */
        private void checkForConcurrentModification() {
            
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Modificações concorrentes detetadas.");
            }   
        }
        
    }
    
    
    
    //------------------------LISTA--------------------------------------------
    
    
    //---------------CONSTRUTOR E ATRIBUTOS DA LISTA----------------------------
    //--------------------------------------------------------------------------
    
    /**
     * No que representa a cabeça da lista
     */
    private BiNode<T> head;
    
    /**
     * Nó que representa a cauda da lista
     */
    private BiNode<T> tail;
    
    /**
     * Numero de elementos efetivos na lista
     */
    private int nElem;
    
    /**
     * Atributo que permite rastrear as alterações na estrutura
     */
    private int modCount;
    
    /**
     * Construtor da lista, inicia a cabeça e a cauda a apontar para null
     * e o numero de elementos e o modCount a 0
     */
    public DoubleLinkedList(){
        this.head = this.tail = null;
        this.nElem = this.modCount = 0;
    }
    
    //----------------------GETTERS E SETTERS-----------------------------------
    //-------------------------------------------------------------------------
    
    
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
    
    /**
     * 
     * @return cabeça da lista
     */
    public BiNode<T> getHead() {
        return head;
    }
    
    /**
     * 
     * @param head nova cabeça da lista 
     */
    public void setHead(BiNode<T> head) {
        this.head = head;
    }
    
    /**
     * 
     * @return Cauda da lista 
     */
    public BiNode<T> getTail() {
        return tail;
    }
    
    /**
     * 
     * @param tail Nova cauda da lista
     */
    public void setTail(BiNode<T> tail) {
        this.tail = tail;
    }
    
    
    
    
    //--------------------------METODOS DA INTERFACE LIST-----------------------
    //--------------------------------------------------------------------------
    
    
    /**
     * Metodo que devolve e remove o primeiro elemento da lista
     * @return primeiro elemento da lista enquanto o remove
     */
    @Override
    public T removeFirst() throws EmptyListException{
        
        if (this.isEmpty()) throw new EmptyListException();
            
 
        T delElem = this.head.elem;

        if (this.head.next != null){ //A lista tinha mais de um elemento
            this.head.next.prev = null;
        } 
        else{
            this.tail = null; // A lista tinha apenas um elemento
        }

        this.head = this.head.next;
        this.modCount++;
        this.nElem--;

        return delElem;
    }
    
    
    /**
     * Metodo que devolve e remove o ultinmo elemento da lista
     * @return ultimo elemento da lista
     */
    @Override
    public T removeLast() throws EmptyListException{
        
        if (this.isEmpty()) throw new EmptyListException();
            
        

        T delElem = this.tail.elem;

        if(this.tail.prev != null){
            this.tail.prev.next = null; // A lista tem mais de um elemento
        } 
        else{
            this.head = null; // A lista tinha apenas um elemento
            
        }

        this.tail = this.tail.prev;
        this.modCount++;
        this.nElem--;

        return delElem;
    }
    
    
    /**
     * Remove e devolve o elemento procurado na lista
     * @param element elemento a remover
     * @return elemento removido da lista
     */
    @Override
    public T remove(T element) throws EmptyListException{
        
        if (this.isEmpty()) throw new EmptyListException();

        
        BiNode<T> node = this.head;

        while(node != null) {
            
            if(node.elem.equals(element)){
                
                if (node.prev != null){ //Nao é o primeiro elemento
                    node.prev.next = node.next;
                }
                else{
                    this.head = node.next;// Remoção do primeiro elemento
                }

                if (node.next != null) {
                    node.next.prev = node.prev; // Não é o ultimo elemento
                } else {
                    this.tail = node.prev;// Remoção do último elemento
                }
                
                this.modCount++;
                this.nElem--;
                
                return element; // Elemento removido
            }

            node = node.next;
        }

        return null; // Elemento não encontrado na lista
    
    }
    
    
    /**
     * Devolve o primeiro elemento da lista
     * @return 
     */
    @Override
    public T first() throws EmptyListException{
        
        if(!this.isEmpty()) return this.head.elem;
        else throw new EmptyListException();
    }
    
    
    /**
     * Devolve o ultimo elemento da lista
     * @return ultimo elemento da lista
     */
    @Override
    public T last() throws EmptyListException {
        if(!this.isEmpty()) return this.tail.elem;
        else throw new EmptyListException();
    }
    
    
    /**
     * Testa se o elemento target existe na lista
     * @param target elemento a procurar
     * @return true se existe na lista
     */
    @Override
    public boolean contains(T target) {
        
        for(BiNode<T> node = this.head; node != null; node = node.next){
            if(node.elem.equals(target)) return true;
        }
        
        return false;
    }
    
    
    /**
     * 
     * @return true se a lista está vazia 
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }
    
    
    /**
     * 
     * @return Numero de elementos efetivos na lista 
     */
    @Override
    public int size() {
        return this.nElem;
    }
    
    
    /**
     * 
     * @return Um iterador sobre os elementos da lista
     */
    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }
    
    /**
     * Metodo que apresenta textualmente os dados de uma lista
     * @return String com os dados dos elementos da lista
     */
    @Override
    public String toString() {
       
        if (this.isEmpty()) {
            return "\nLista Vazia\n";
        }

        StringBuilder s = new StringBuilder("\n");
        s.append("Nr de elementos: ").append(this.nElem).append("\n");
        s.append("Head: ").append(this.head.elem.toString()).append("\n");
        s.append("Tail: ").append(this.tail.elem.toString()).append("\n");
        
        //Como vamos processar todos os elementos da lista, optamos pelo iterador
        Iterator<T> iterator = this.iterator();
        
        while (iterator.hasNext()) {
            s.append("Elemento: ").append(iterator.next().toString()).append("\n");
        }

        return s.toString();
    }
    
    
    
}
