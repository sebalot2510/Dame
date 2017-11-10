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
public class Einfach extends Stein
{
    public Einfach ( Feld feld, boolean schwarz)
    {
        super( feld, schwarz );
    }
    
    @Override
    public boolean istOK (Feld feld)
    {
        int dX = feld.getSpalte() - this.getFeld().getSpalte();
        int dY = feld.getZeile() - this.getFeld().getZeile();
        
        if ( Math.abs(dX) != Math.abs(dY))
        {
            return false;
        }
        else if (Math.abs( dX ) > 2 || Math.abs(dX) == 0)
        {
            return false;
        }
        else if ( ( getSchwarz() && dY > 0 ) ||
                  (!getSchwarz() && dY < 0 ) )
        {
            return false;
        }
        return true;
    }
    
     public String getSymbol()
    {
        return "\u25ce";
    }
}
