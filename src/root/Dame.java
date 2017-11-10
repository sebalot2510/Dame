package root;

import gui.*;

/**
 *
 * @author Sebastian
 */
public class Dame extends Stein 
{
    private boolean hatGeschlagen = false;
    
    public Dame ( Feld feld, boolean schwarz)
    {
        super( feld, schwarz );
    }
    
    public boolean istOK (Feld feld)
    {
        int dX = feld.getSpalte() - this.getFeld().getSpalte();
        int dY = feld.getZeile() - this.getFeld().getZeile();
        
        if ( Math.abs(dX) != Math.abs(dY))
        {
            return false;
        }
        else if (Math.abs(dX) == 0)
        {
            return false;
        }
        
        return true;
    }
    
    public String getSymbol()
    {
        return "\u25c9";
    }
}
