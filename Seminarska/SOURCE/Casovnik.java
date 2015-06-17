import java.applet.*;
import java.awt.*;
import java.util.*;
import java.text.*;

public class Casovnik extends Applet implements Runnable {

   int sirina, visina;
   Thread t = null;
   boolean animacija;
   int casovi=0, minuti=0, sekundi=0;
   String s = "";
  
 

   public void init() {
      sirina = 150;
      visina =200;
      setBackground( Color.black );
     
   }

   public void start() {
      if ( t == null ) {
         t = new Thread( this );
         t.setPriority( Thread.MIN_PRIORITY );
         animacija = false;
         t.start();
      }
      else {
         if ( animacija ) {
            animacija = false;
            synchronized( this ) {
               notify();
            }
         }
      }
   }

   public void stop() {
      animacija = true;
     
   }

   public void run() {
      try {
         while (true) {

            
            Calendar kalendar = Calendar.getInstance();
            casovi = kalendar.get( Calendar.HOUR_OF_DAY );
            if ( casovi > 12 ) casovi -= 12;
            minuti = kalendar.get( Calendar.MINUTE );
            sekundi = kalendar.get( Calendar.SECOND );

            SimpleDateFormat format
               = new SimpleDateFormat( "hh:mm:ss", Locale.getDefault() );
            Date datum = kalendar.getTime();
            s = format.format( datum );

           
            if ( animacija ) {
               synchronized( this ) {
                  while ( animacija ) {
                     wait();
                  }
               }
            }
            repaint();
            t.sleep( 1000 );
         }
      }
      catch (InterruptedException e) { }
   }

   void drawHand( double agol, int radius, Graphics g ) {
      agol -= 0.5 * Math.PI;
      int x = (int)( radius*Math.cos(agol) );
      int y = (int)( radius*Math.sin(agol) );
      g.drawLine( sirina/2, visina/2, sirina/2 + x, visina/2 + y );
   }

   void drawWedge( double agol, int radius, Graphics g ) {
      agol -= 0.5 * Math.PI;
      int x = (int)( radius*Math.cos(agol) );
      int y = (int)( radius*Math.sin(agol) );
      agol += 2*Math.PI/3;
      int x2 = (int)( 5*Math.cos(agol) );
      int y2 = (int)( 5*Math.sin(agol) );
      agol += 2*Math.PI/3;
      int x3 = (int)( 5*Math.cos(agol) );
      int y3 = (int)( 5*Math.sin(agol) );
      g.drawLine( sirina/2+x2, visina/2+y2, sirina/2 + x, visina/2 + y );
      g.drawLine( sirina/2+x3, visina/2+y3, sirina/2 + x, visina/2 + y );
      g.drawLine( sirina/2+x2, visina/2+y2, sirina/2 + x3, visina/2 + y3 );
   }

   public void paint( Graphics g ) {
      g.setColor( Color.gray );
      drawWedge( 2*Math.PI * casovi / 12, sirina/5, g );
      drawWedge( 2*Math.PI * minuti / 60, sirina/3, g );
      drawHand( 2*Math.PI * sekundi / 60, sirina/2, g );
      g.setColor( Color.white );
      g.drawString( s, 10, visina-10 );
   }
}