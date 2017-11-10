/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import gui.*;

/**
 *
 * @author Sebastian
 */
public abstract class Stein 
{
    private Feld feld;
    private boolean istSchwarz;
    private boolean hatGeschlagen;
    
    public Stein( Feld feld, boolean schwarz )
    {
        this.feld = feld;
        istSchwarz = schwarz;
    }
    
    public boolean getSchwarz()
    {
        return istSchwarz;
    }
    
    public Feld getFeld()
    {
        return feld;
    }
    
    public void setFeld (Feld feld)
    {
        this.feld = feld;
        feld.getBrett().merkeEnde( this );
    }
    
    public void setHatgeschlagen(boolean hatGeschlagen)
    {
        this.hatGeschlagen = hatGeschlagen;
    }
    
    public boolean getHatGeschlagen()
    {
        return hatGeschlagen;
    }
      
     abstract public boolean istOK (Feld feld);
     
     abstract public String getSymbol();
    
}
