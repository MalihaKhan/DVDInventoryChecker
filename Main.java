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
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException
    {
        
        File file = new File("inventory.dat");
        Scanner in = new Scanner(file);
        BinSearchTree<String> tree = new BinSearchTree<>();
        String rootContent;
        String content,title, amount, rents, rootTitle, rootAm, rootRents;
        int avail, rented, rootAvail, rootRented;
        String[] rootParts;
        String[] part;
        Node<DVD> rootNode;
        Node<DVD> node = null;
        rootContent = in.nextLine();
        rootParts = rootContent.split(",");
        rootTitle = rootParts[0];
        rootAm = rootParts[1];
        rootRents = rootParts[2];
        rootAvail = Integer.parseInt(rootAm);
        rootRented = Integer.parseInt(rootRents);
        rootNode = new Node<>(new DVD(rootTitle, rootAvail, rootRented));
        rootNode.getPayload().setTitle(rootTitle);
        rootNode.getPayload().setAvail(rootAvail);
        rootNode.getPayload().setRented(rootRented);
        tree.insert(rootNode, node);
        
        BinSearchTree holdtree = new BinSearchTree<>();
        
          
        
        
        while(in.hasNext())
        {
             content = in.nextLine();
             part = content.split(",");
             title = part[0];
             amount = part[1];
             rents = part[2];
             avail = Integer.parseInt(amount);
             rented = Integer.parseInt(rents);
             node = new Node<>(new DVD(title, avail, rented));
             node.getPayload().setTitle(title);
             node.getPayload().setAvail(avail);
             node.getPayload().setRented(rented);
             tree.insert(rootNode, node);

        }
        
        
        
        File trans = new File("transaction.txt");
        Scanner transIn = new Scanner(trans);
        File error = new File("Error.log");
        error.createNewFile();
        String logCont = "";
        FileWriter writer = new FileWriter(error);
        while(transIn.hasNext())
        {
            logCont += transIn.nextLine() + '\n';
        }
        
        String[] split = logCont.split("\n");
        String[] senPa;
        String[] cPa;
        String str;
        String first;
        String second;
        for(int index = 0; index < split.length; index++)
        {
            str = split[index];
            if(valFormat(str) == false)
            {
                writer.write(str);
            }
            else
            {
                    senPa = str.split(" ");
                    first = senPa[0];
                    second = senPa[1];
                    if(validateCommand(first) == false)
                    {
                         writer.write(str);
                    }
                    else if(comma(second) == true)
                    {
                        cPa = second.split(",");
                        String movie, qty;
                        movie = cPa[0];
                        qty = cPa[1];
                        if(first.compareTo("add") != 0 && valMovie(movie, rootNode) == false)
                        {
                            writer.write(str);
                        }  
                        if(valNum(qty) == false)
                        {
                             writer.write(str);
                        }
                        else if(first.compareTo("add") == 0 && valMovie(movie, rootNode) == false) 
                        {
                            int newAvail  = Integer.parseInt(qty);
                            
                            Node<DVD> newNode = new Node<>(new DVD(movie, newAvail, 0));
                            
                            
                            tree.insert(rootNode, newNode);
                           
                        }
                        else if(first.compareTo("remove") == 0 && Integer.parseInt(qty) > 0)
                        {
                            Node<DVD> treeNode = tree.searchReturn(movie, rootNode);
                            treeNode.getPayload().setAvail(treeNode.getPayload().getAvail()- Integer.parseInt(qty));
                        }

                    }
                    else if(comma(second) == false)
                    {
                       
                        String movie = second;
                        if(first.compareTo("rent") == 0)
                        {
                            Node<DVD> find;
                            find = tree.searchReturn(movie, holdtree.getRoot());
                            find.getPayload().setAvail((find.getPayload().getAvail())- 1);
                        }
                        else if(first.compareTo("return") == 0)
                        {
                            Node<DVD> treeNode = tree.searchReturn(movie, rootNode);
                            treeNode.getPayload().setAvail(treeNode.getPayload().getAvail()+ 1);
                        }
                       
                            
                    }
                            

            }
        }
        
        displayReport(rootNode);
     

    }
    public static boolean validateCommand(String first)
    {
        String add = "add";
        String remove = "remove";
        String rent = "rent";
        String ret = "return";
        if(first.compareTo(add) == 0)
        {
            return true;
        }
        else if(first.compareTo(remove) == 0)
        {
            return true;
        }
        else if(first.compareTo(rent) == 0)
        {
            return true;
        }
        else if(first.compareTo(ret) == 0)
        {
            return true;
        }
      
        return false;
    }
    public static boolean comma(String str)
    {
        for(int index = 0; index < str.length(); index++)
        {
            if(str.charAt(index) == ',')
            {
                return true;
            }
        }
        return false;
    }
    public static boolean valMovie(String str, Node<DVD> node)
    {
        BinSearchTree<String> tree = new BinSearchTree<>();
        return !tree.search(str, node);
        
    }
    public static boolean valNum(String str)
    {
        char charStr;
        charStr = str.charAt(0);
        return Character.isDigit(charStr);
    }
    public static boolean valFormat(String str)
    {
        String add = "add \"", remove = "remove \"", ret = "return \"", rent = "rent \"";
        
        if(str.contains(add) || str.contains(remove) || str.contains(ret) || str.contains(rent))
        {
            if(str.contains(add) && comma(str))
            {
                int commPos = str.indexOf(',');
                if(str.charAt(commPos-1) != '"')
                {
                    return false;
                }
            }
            if(str.contains(remove) && comma(str))
            {
                int commPos = str.indexOf(',');
                if(str.charAt(commPos-1) != '"')
                {
                    return false;
                }
            }
            if(str.contains(ret) || str.contains(rent))
            {
                if(str.charAt(str.length()-1) != '"')
                {
                    return false;
                }
                        
            }
                
        }
        
        return true;
    }
    public static void displayReport(Node<DVD> node) throws IOException
    {
        File file = new File("redbox_kiosk.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        
        Scanner in = new Scanner(file);
        BinSearchTree<String> tree = new BinSearchTree<>();
        tree.inorder(node);
       
        
    }
    
}
