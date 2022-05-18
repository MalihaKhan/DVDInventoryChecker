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

import java.util.*;
public class BinTree <Payload extends Comparable <Payload>> {
    
    private Node<DVD> root;
    
    public BinSearchTree()
    {
        
    }
    public void setRoot(Node<DVD> root)
    {
        this.root = root;
    }
    public Node<DVD> getRoot()
    {
        return root;
    }
    public Node<DVD> insert(Node<DVD> current, Node<DVD> newNode)
    {    
        
        //Node<DVD> current = null;

        if(root == null)
        {
            root = current;
            
           
            return root;
        }
        else
        {
            
            if(current == null)
            {
       
                return newNode;
            }
            if( current.getLeft() == null && current.getPayload().getTitle().compareTo(newNode.getPayload().getTitle()) < 0)
            {
                 current.setLeft(insert(current.getLeft(), newNode));
            }
            else if(current.getRight() == null && current.getPayload().getTitle().compareTo(newNode.getPayload().getTitle()) > 0)
            {

                current.setRight(insert(current.getRight(), newNode));

            }
            else
            {
                return current;
            }
   
           
        }
        
        return current;
    }
    public boolean search(String data, Node<DVD> find)
    {
       
        if(find == null || data.compareTo(find.getPayload().getTitle()) == 0)
        {
           return true;
        }
        if(data.compareTo(find.getPayload().getTitle()) < 0)
        {
            return search(data, find.getLeft());
        }
        else if(data.compareTo(find.getPayload().getTitle()) > 0)
        {
            return search(data, find.getRight());
        }
      
        return false;
       
               
    }
    
    public Node<DVD> searchReturn(String data, Node<DVD> find)
    {
       
        if(find == null || data.compareTo(find.getPayload().getTitle()) == 0)
        {
            return find;
        }
        if(find.getPayload().getTitle().compareTo(data) > 0)
        {
            return searchReturn(data, find.getLeft());
        }
        
        return searchReturn(data, find.getRight());
   
    }
        
    void breadthFirst(Node<DVD> node)
    {
        Queue<Node> queue = new LinkedList<>();
        
        if(root == null)
        {
            root = node;
        
            
        }
        else
        { 
            queue.clear();
            queue.add(node);
            while(!queue.isEmpty())
            {
                Node<DVD> newNode = queue.remove();
                System.out.print(newNode.getPayload().getTitle() + " ");
                if (newNode.getLeft() != null)
                {
                    queue.add(newNode.getLeft());
                }
                if (newNode.getRight() != null)
                {
                    queue.add(newNode.getRight());
                }
            }
        } 
    }
    public boolean remove(Node<DVD> remNode)
    {
        Node<DVD> parent = null;
        Node<DVD> current = root;
        while(current != null)
        {
            if(remNode.getPayload().getTitle().compareTo(current.getPayload().getTitle()) < 0)
            {
                parent = current;
                current = current.getLeft();
            }
            else if(remNode.getPayload().getTitle().compareTo(current.getPayload().getTitle()) > 0)
            {
                parent = current;
                current = current.getRight();
            }
            else
            {
                break;
            }
            
        }
        if(current == null)
        {
            return false;
        }
        if(current.getLeft() == null)
        {
            if(parent == null)
            {
                root = current.getRight();
            }
            else
            {
                if(remNode.getPayload().getTitle().compareTo(parent.getPayload().getTitle()) < 0)
                {
                   parent.setLeft(current.getRight());
                }
                else
                {
                    parent.setRight(current.getRight());
                }
            }
        
        }
        else
        {
            Node<DVD> parOfRM = current;
            Node<DVD> rightMost = current.getLeft();
           
            while(rightMost.getRight() != null)
            {
                parOfRM = rightMost;
                rightMost = rightMost.getRight();
            }
            
            current.getPayload().setTitle(rightMost.getPayload().getTitle());
            
            if(parOfRM.getRight() == rightMost)
            {
                parOfRM.setRight(rightMost.getLeft());
            }
            else
            {
                parOfRM.setLeft(rightMost.getLeft());
            }
          
        }

 
        return true;
    }
    public void inorder(Node<DVD> node)
    {
        if(node == null)
        {
            return;
        }
        inorder(node.getLeft());
        System.out.print(node.getPayload().getTitle() + " ");
        inorder(node.getRight());
    }
    
}
