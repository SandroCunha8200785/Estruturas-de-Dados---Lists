
package pt.ipp.estg.ed.doublelinkedlistimplementation;

import Exceptions.NoSuchElementException;

/**
 * Classe que implementa uma lista de elementos não organizados
 * @author SandroCunha8200785
 * @param <T> Tipo parametrizado
 */
public class UnorderedDoubleLinkedList<T> 
        extends DoubleLinkedList<T> implements UnorderedListADT<T> {
    
    
    
    //--------------------CONSTRUTOR--------------------------------------------
    //-------------------------------------------------------------------------
    /**
     * Construtor da lista
     */
    public UnorderedDoubleLinkedList() {
        super();
    }
    
    
    
    //-----------------METODOS DA INTERFACE UNoRDEREDLIST---------------------
    //--------------------------------------------------------------------------
    
    /**
     * MEtodo que adiciona um elemento na cabeça da lista
     * @param elem elemento a adicionar na lista
     */
    @Override
    public void addToFront(T elem) {
        BiNode<T> node = new BiNode<>(elem);

        if (this.isEmpty()){
            // Se a lista estiver vazia
            this.setHead(node);
            this.setTail(node);
        } 
        
        else{
            node.setNext(this.getHead());
            this.getHead().setPrev(node);
            this.setHead(node);
        }

        // Incrementar o modCount e o número de elementos
        this.incModCount();
        this.incNElem();
    }
    
    
    /**
     * Metodo que insere um elemento no fim da lista
     * @param elem elemento a inserir
     */
    @Override
    public void addToRear(T elem) {
        
        BiNode<T> node = new BiNode<>(elem);

        if (this.isEmpty()){
            // Se a lista estiver vazia
            this.setHead(node);
            this.setTail(node);
        } 
        
        else{
            node.setPrev(this.getTail());
            this.getTail().setNext(node);
            this.setTail(node);
        }

        // Incrementar o modCount e o número de elementos
        this.incModCount();
        this.incNElem();
    }
    
    
    /**
     * Metodo que insere um elemento apos outro especifico elemento ja presente na lista
     * @param insertElem
     * @param afterElement 
     */
    @Override
    public void addAfter(T insertElem, T afterElement) throws NoSuchElementException{
        
        BiNode<T> node = new BiNode<>(insertElem);
        BiNode<T> atual = this.getHead();

        while (atual != null && !atual.getElem().equals(afterElement)) {
            atual = atual.getNext();
        }

        if (atual != null){
            node.setPrev(atual);
            node.setNext(atual.getNext());

            if (atual.getNext() != null){
                atual.getNext().setPrev(node);
            } 
            
            else{
                // Inserir no fim
                this.setTail(node);
            }

            atual.setNext(node);

            // Incrementar o modCount e o número de elementos
            this.incModCount();
            this.incNElem();
        } 
        
        else{
            // Elemento afterElement não encontrado na lista
            throw new NoSuchElementException();
        }
    }
    
    
    
    //----------------------------TOSTRING-------------------------------------
    //-------------------------------------------------------------------------
    
    /**
     * Metodo que imprime a lista nao ordenada
     * @return representação textual da lista nao ordenada
     */
    @Override
    public String toString() {
        
        StringBuilder s = new StringBuilder("\n----LISTA NAO ORDENADA----\n");
        
        s.append(super.toString());
        
        return s.toString();
        
    }
}
