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
public class DVD {
    private String title;
    private int avail;
    private int rented;
    
    
    public DVD()
    {
        
    }
    public DVD(String title, int avail, int rented)
    {
        this.title = title;
        this.avail = avail;
        this.rented = rented;
    }
    
    @Override
    public int compareTo(DVD o) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
