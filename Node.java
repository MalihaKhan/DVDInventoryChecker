/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hi.dvdinventory;

/**
 *
 * @author malih
 */

import java.lang.*;
public class Node<T extends java.lang.Comparable <T>> {
    private T data;
    private Node<T> left;
    private Node<T> right;
 
    
    public class Payload<T extends java.lang.Comparable <T>>
    {
        private String title;
        private int avail;
        private int rented;
        
        public Payload()
        {
            
        }
        public void setTitle(String title)
        {
            this.title = title;
            
        }
        public String getTitle()
        {
            return title;
        }
        public void setAvail(int avail)
        {
            this.avail = avail;
        }
        public int getAvail()
        {
            return avail;
        }
        public void setRented(int rented)
        {
            this.rented = rented;
        }
        public int getRented()
        {
            return rented;
        }

        
    }
    
    private Payload<T> payload = new Payload<>();
    
    public Node()
    {
        
    }
    
    public Node(Payload<T> payload)
    {
        this.payload = payload;
        
    } 
    public Node(T data)
    {
        this.data = data;
    }
   
    public void setLeft(Node<T> left)
    {
        this.left = left;
    }
    public void setRight(Node<T> right)
    {
        this.right = right;
    }

    public Node<T> getLeft()
    {
        return left;
    }
    public Node<T> getRight()
    {
        return right;
    }
    public void setData(T data)
    {
        this.data = data;
    }
    public T getData()
    {
        return data;
    }
    public void setPayload(Payload<T> payload)
    {
        this.payload = payload;
    }
    public Payload<T> getPayload()
    {
        return payload;
    }
   
    
}
