//git Test
//gitTest vom Surface
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import root.*;

/**
 *
 * @author Sebastian
 */
public class Brett extends javax.swing.JFrame 
{
    private boolean istZugbeginn = true;
    
    /* weiß: 1    00000001
     *schwarz: 2  00000010    
     *"beide": 3  00000011
     */
    
    private final int amZugWEISS = 1;
    private final int amZugSCHWARZ = 2;
    private int amZug = amZugWEISS;

    /**
     * Creates new form Brett
     */
    public Brett() 
    {
        initComponents();
        
        getContentPane().removeAll(); /* löschen der Inhalte aufgrund der 
        automatisiert erstellen Inhalte durch den Grafikeditor*/
        
        boolean schwarz = true;
        for (int z = 0; z < feld.length; z++)
        {
            for (int sp = 0; sp < feld[z].length; sp++)
            {
                Feld f = new Feld ( this, schwarz, z, sp );
                feld[z][sp] = f;
                f.addActionListener(fl);
                                
                f.setBackground(schwarz ? Color.darkGray : Color.lightGray);
                /* ist das gleiche wie:                
                if ( schwarz ){
                    f.setBackground(Color.darkGray);
                }                    
                else {
                    f.setBackground(Color.lightGray);
                }
                */                
                if ( schwarz )
                {
                    if ( z <=3)
                    {
                        f.setStein (new Einfach ( f, false ), true );
                        //f.setText("O");
                        //f.setForeground(Color.white);
                    }
                    else if (z >= 6)
                    {
                        f.setStein (new Einfach ( f, true ), true );
                        //f.setText("O");
                        //f.setForeground(Color.black);
                    }
                }
                
                jPanel1.add(f);
                schwarz = !schwarz;
          
            }
            schwarz = !schwarz;
        }
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        pack();
    }
    
    public boolean getZugbeginn()
    {
        return istZugbeginn;
    }
    
    public void merkeBeginn()
    {
        istZugbeginn = false;
    }
    
    public void merkeEnde(Stein stein)
    {
        if (stein.getClass().getCanonicalName().equals("root.Einfach"))
        {
            amZug = stein.getSchwarz() ? amZugWEISS : amZugSCHWARZ;
            /* bedeutet:
            if (stein.getSchwarz())
                amZug = amZugWEISS;
            else
                amZug = amZugSCHWARZ;
            */
        }
        
        if (stein.getClass().getCanonicalName().equals("root.Dame") && (!stein.getHatGeschlagen()))
        {
            amZug = stein.getSchwarz() ? amZugWEISS : amZugSCHWARZ;
        }
        istZugbeginn = true;
    }
    
    public boolean istOk ( Stein stein, Feld ziel) 
    {
        //Zielfeld besetzt
        if (ziel.getStein() != null)
        {
            return false;
        }
         
        
        int x1 = stein.getFeld().getSpalte(); //Startposition des Steins Spalte
        int y1 = stein.getFeld().getZeile();  //Startposition des Steins Zeile
        int x2 = ziel.getSpalte();
        int y2 = ziel.getZeile();
         
        int dX = x2-x1;
        int dY = y2-y1;
         
        if ( Math.abs(dX) > 1 ) //Zug länger als 1 Feld:
        {
            int dX1 = dX > 0 ? 1 : -1; //Richtung des Zuges bestimmen
            /*if (dX > 0)
                dX1 = 1;
            else
                dX1 = -1;*/
            int dY1 = dY > 0 ? 1 : -1;
                           
            Feld fletzt = feld[ y2 - dY1 ] [x2 - dX1]; //Letztes Feld = Feld vor Zielfeld
            Stein stletzt = fletzt.getStein();
            
            if (stletzt != null)
                if ( stletzt.getSchwarz() == stein.getSchwarz()) // Stein eigener Farbe
                {
                    return false;
                }
                   
            if (stein.getClass().getCanonicalName().equals("root.Einfach") &&
                    stletzt == null)    // für einfachen Stein: letztes Feld leer
            {
                return false;
            }
            if ( Math.abs(dX) > 2 ) //Zug länger als 2 Felder:
            {
                int z = y1 + dY1; //erstes Feld (Zeile)
                int sp = x1 + dX1; //erstes Feld (Spalte)
                
                //Testen ob von ersten bis vorletztem Feld besetzt
                for (int i = 0; i < Math.abs(dX) - 2; i++) // Erstes bis VORletztes Feld
                {
                    if ( feld[z][sp].getStein() != null )
                    {
                        return false;
                    }
                    
                    z += dY1;
                    sp += dX1;                                     
                }
            }
            fletzt.wegStein();
            if ( stein.getClass().getCanonicalName().equals( "root.Dame" ) )
            {
                stein.setHatgeschlagen(true);
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));

        jPanel1.setLayout(new java.awt.GridLayout(10, 10));
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Brett.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Brett().setVisible(true);
            }
        });
    }
    
    private Feld[][] feld =  new Feld[10][10];

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private class FeldListener implements ActionListener
    {
        
        Stein st = null;
        
        public void actionPerformed ( ActionEvent evt )
        {
            Feld f = null;
            for (int z = 0; z < feld.length; z++)
            {
                for (int sp = 0; sp < feld[z].length; sp++)
                {
                    if (evt.getSource() == feld[z][sp])
                    {
                        System.out.println( "Feld (" + z + ", " + sp + ") geklickt!" );
                        f = feld[z][sp];
                        break;
                    }
                }
            }
            int amZugMem = amZug;
            if ( getZugbeginn() )
            {
                if ( ( st = f.getStein() ) != null )
                    /* zwei in einem:
                    st = f.getStein();
                    if (st != null)....
                    */
                {
                   if (st.getSchwarz() && ( (amZug & amZugSCHWARZ) != 0 ) ||
                        !st.getSchwarz() && ( (amZug & amZugWEISS) != 0) )
                       /* amZug & amZugSCHWARZ --> bitweise "vergleich"*/
                    {
                        f.wegStein();                    
                    } 
                }
                
                
            }
            else if (st.istOK (f) && istOk ( st, f) )
            {
                f.setStein(st);
                if (st.getClass().getCanonicalName().equals("root.Dame"))
                {
                    st.setHatgeschlagen(false);
                }
            }
            else
            {
                st.getFeld().setStein(st);
                amZug = amZugMem;
                if (st.getClass().getCanonicalName().equals("root.Dame"))
                {
                    st.setHatgeschlagen(false);
                }
            }
        }
    }
    FeldListener fl = new FeldListener();
}
