
package pt.ipp.estg.ed.doublelinkedlistimplementation;

/**
 * Classe que implementa uma lista em que os nós estão ordenados pelo seu metodo comparavel
 * @author SandroCunha 8200785
 */
public class OrderedDoubleLinkedList<T extends Comparable<T>> 
        extends DoubleLinkedList<T> implements OrderedListADT<T> {

    
    
    //-----------------CONSTRUTOR---------------------------------------------
    //_------------------------------------------------------------------------
    
    /**
     * Construtor vazio da lista
     */
    public OrderedDoubleLinkedList() {
        super();
    }
    
    
    
    //-------------METODOS DA INTERFACE ORDEREDLIST-----------------------------
    //--------------------------------------------------------------------------
    
    
    /**
     * MEtodo que insere um novo elemento na lista, o elemento devera ser inserido
     * numa posição especifica segundo um criterio comparavel de forma a manter a lista ordenada
     * @param element elemento a inserir
     */
    @Override
    public void add(T element) {
        
        BiNode<T> ins = new BiNode<>(element);

        // Inserção na cabeça da lista
        if (this.getHead() == null || this.getHead().getElem().compareTo(element) > 0){
            
            ins.setNext(this.getHead());
            //lista nao se encontrava vazia
            if (ins.getNext() != null) {
                ins.getNext().setPrev(ins);
            } 
            else{
                // Se a lista estava vazia, o novo nó também será a cauda
                this.setTail(ins);
            }
            //CAbeça agora aponta para o nó
            this.setHead(ins);
        } 
        
        else{
            // Inserção depois da cabeça da lista
            BiNode<T> prev = positionInsert(this.getHead(), element);
            
            if (prev != null){
                
                ins.setPrev(prev);
                ins.setNext(prev.getNext());
                prev.setNext(ins);

                if (ins.getNext() != null) {
                    ins.getNext().setPrev(ins);
                } 
                
                else{
                    // Se a inserção foi no final, atualize a cauda
                    this.setTail(ins);
                }
            }
        }
        
        //Incrementar o modCount e o numero de elementos
        this.incModCount();
        this.incNElem();
        
    }
    
    /**
     * Metodo que calcula a posição de inserção de um elemento na lista
     * @param head cabeça da lista
     * @param element elemento a inserir
     * @return No anterior ao novo no a inserir na lista
     */
    private BiNode<T> positionInsert(BiNode<T> head, T element) {
        
        BiNode<T> atual = this.getHead(), prev = this.getHead();
        
        while(atual != null && element.compareTo(atual.getElem()) > 0){
            prev = atual;
            atual = atual.getNext();
        }
        
        return prev;
        
    }
    
    
    
    //----------------------------TOSTRING-------------------------------------
    //-------------------------------------------------------------------------
    
    /**
     * Metodo que imprime a lista ordenada
     * @return representação textual da lista ordenada
     */
    @Override
    public String toString() {
        
        StringBuilder s = new StringBuilder("\n----LISTA ORDENADA----\n");
        
        s.append(super.toString());
        
        return s.toString();
        
    }
    
    
    
}
