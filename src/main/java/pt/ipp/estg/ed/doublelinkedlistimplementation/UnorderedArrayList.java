
package pt.ipp.estg.ed.doublelinkedlistimplementation;

/**
 * Classe que implementa uma lista não ordenada com recurso a um array
 * @author SandroCunha8200785
 */
import java.util.NoSuchElementException;

public class UnorderedArrayList<T extends Comparable<T>> 
        extends GenericArrayList<T> implements UnorderedListADT<T> {

    
    
    /*--------------------CONSTRUTORES------------------------------------------
    ---------------------------------------------------------------------------*/
    
    
    
    /**
     * Construtor que inicializa uma lista com tamanho por defeito
     */
    public UnorderedArrayList() {
        super();
    }
    
    /**
     * Construtor que inicializa uma lista com o tamanho passado por parametro
     * @param capacity 
     */
    public UnorderedArrayList(int capacity) {
        super(capacity);
    }
    
    
    /*--------------------METODOs DA INTERFACE UNORDEREDLIST-----------------
    --------------------------------------------------------------------------*/
    
    
    /**
     * Adiciona um elemento no inicio da fila
     * @param element elemento a adicionar
     */
    @Override
    public void addToFront(T element) {
        
        if (element == null) {
            throw new IllegalArgumentException("\nElemento inexistente.\n");
        }

        // Expandir o array se necessário
        if (this.size() == this.array.length) {
            this.array = this.expandCapacity();
        }

        
        for (int i = this.size(); i > 0; i--) {
            this.array[i] = this.array[i - 1];
        }

        
        this.array[0] = element;

        this.incNElem();
        this.incModCount();
    }
    
    
    /**
     * MEtoto que insere um elemento no fim da fila
     * @param element elemento a inserir no fim da fila
     */
    @Override
    public void addToRear(T element) {
        
        if (element == null) {
            throw new IllegalArgumentException("\nElemento inexistente.\n");
        }

        // Expandir o array se necessário
        if (this.size() == this.array.length) {
            this.array = this.expandCapacity();
        }

        
        this.array[this.size()] = element;

        this.incNElem();
        this.incModCount();
    }
    
    
    /**
     * Elemento que insere o novo elemento apos um elemento ja existente
     * @param insertElem elemento a inserir
     * @param afterElement elemento apos o qual tera lugar a inserção
     * @throws NoSuchElementException elemento apos o qual deve ser inserido nao se encontra na lista
     */
    @Override
    public void addAfter(T insertElem, T afterElement) throws NoSuchElementException {
        
        if (insertElem == null || afterElement == null) {
            throw new IllegalArgumentException("\nElemento inexistente\n");
        }

        int index = -1;

        
        for (int i = 0; i < this.size(); i++) {
            if (this.array[i].equals(afterElement)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Elemento após o qual inserir não encontrado");
        }

        // Expandir o array se necessário
        if (this.size() == this.array.length) {
            this.array = this.expandCapacity();
        }

        
        for (int i = this.size(); i > index + 1; i--) {
            this.array[i] = this.array[i - 1];
        }

        
        this.array[index + 1] = insertElem;

        this.incNElem();
        this.incModCount();
    }
}

