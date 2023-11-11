/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pt.ipp.estg.ed.doublelinkedlistimplementation;

import Exceptions.NoSuchElementException;

/**
 *
 * @author sssan
 */
public interface UnorderedListADT<T> extends ListADT<T>  {
    
    /**
     * Adds the specified element to the beginning of the list
     *
     *
     * @param element the element to be added to this list
     */
    public void addToFront(T elem);
    
    
    /**
     * Adds the specified element to the end of this list
     *
     * @param element the element to be added to this list
     */
    public void addToRear(T elem);
 
    
    /**
     * Adds the specified element to this list at the proper location
     *
     * @param insertElement the insertElem to be added to this list
     * @param afterElement the element which the insertion will occur after it
     */
    public void addAfter(T insertElem, T afterElement) throws NoSuchElementException;
    
}
