/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import root.*;

/**
 *
 * @author Sebastian
 */
public class Feld extends JButton
{
    private Brett brett;
    private Stein stein = null;
    private boolean istSchwarz;
    private int zeile, spalte;
    
    public Feld ( Brett brett, boolean schwarz, int zeile, int spalte )
    {
        this.brett = brett;
        istSchwarz = schwarz;
        this.zeile = zeile;
        this.spalte = spalte;
    }
    
    
    //Getter
    public Brett getBrett()
    {
        return brett;
    }
    
    public Stein getStein()
    {
        return stein;
    }

    public int getZeile() {
        return zeile;
    }

    public int getSpalte() {
        return spalte;
    }

    
    //Setter
    public void setStein(Stein stein)
    {
        setStein(stein, false);
    }
    
    public void setStein(Stein stein, boolean init)
    {
        if ( !init && stein.getClass().getCanonicalName().equals( "root.Einfach" ) && (zeile == 0 || zeile == 9 ) )
        {
            stein = new Dame ( this, stein.getSchwarz());
            //System.out.println(stein.getHatGeschlagen());
        }
        this.stein = stein;
        stein.setFeld( this );
        setForeground(stein.getSchwarz() ? Color.black : Color.white );
        this.setFont (new Font ( "Dialog ", 1, 36));
        setText(stein.getSymbol());
    }
    
    public void wegStein()
    {
        stein = null;
        brett.merkeBeginn();
        setText ("");
    }
    
    
}
