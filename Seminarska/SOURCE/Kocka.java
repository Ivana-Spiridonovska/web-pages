import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;


class Tocka3D {
   public int x, y, z;
   public Tocka3D( int X, int Y, int Z ) {
      x = X;  y = Y;  z = Z;
   }
}



class Rab {
   public int a, b;
   public Rab( int A, int B ) {
      a = A;  b = B;
   }
}

public class Kocka extends Applet
   implements MouseListener, MouseMotionListener {

   int sirina, visina;
   int koordinataX, koordinataY;  // the most recently recorded mouse coordinates

   Image slika;
   Graphics grafika;

   int a = 35, izvishuvanje = 30;

   Tocka3D[] stranaKocka;
   Rab[] rabovi;

   public void init() {
      sirina = 200;//getSize().width;
      visina = 200;//getSize().height;

      stranaKocka = new Tocka3D[ 8 ];
      stranaKocka[0] = new Tocka3D( -1, -1, -1 );
      stranaKocka[1] = new Tocka3D( -1, -1,  1 );
      stranaKocka[2] = new Tocka3D( -1,  1, -1 );
      stranaKocka[3] = new Tocka3D( -1,  1,  1 );
      stranaKocka[4] = new Tocka3D(  1, -1, -1 );
      stranaKocka[5] = new Tocka3D(  1, -1,  1 );
      stranaKocka[6] = new Tocka3D(  1,  1, -1 );
      stranaKocka[7] = new Tocka3D(  1,  1,  1 );

      rabovi = new Rab[ 12 ];
      rabovi[ 0] = new Rab( 0, 1 );
      rabovi[ 1] = new Rab( 0, 2 );
      rabovi[ 2] = new Rab( 0, 4 );
      rabovi[ 3] = new Rab( 1, 3 );
      rabovi[ 4] = new Rab( 1, 5 );
      rabovi[ 5] = new Rab( 2, 3 );
      rabovi[ 6] = new Rab( 2, 6 );
      rabovi[ 7] = new Rab( 3, 7 );
      rabovi[ 8] = new Rab( 4, 5 );
      rabovi[ 9] = new Rab( 4, 6 );
      rabovi[10] = new Rab( 5, 7 );
      rabovi[11] = new Rab( 6, 7 );

      slika = createImage( sirina, visina );
      grafika = slika.getGraphics();
      crtajKocka( grafika );

      addMouseListener( this );
      addMouseMotionListener( this );
   }

   void crtajKocka( Graphics g ) {

      // compute coefficients for the projection
      double teta = Math.PI * a / 180.0;
      double fi = Math.PI * izvishuvanje / 180.0;
      float kosinusT = (float)Math.cos( teta ), sinusT = (float)Math.sin( teta );
      float kosinusP = (float)Math.cos( fi ), sinusP = (float)Math.sin( fi );
      float kosinusTkosinusP = kosinusT*kosinusP, kosinusTsinusP = kosinusT*sinusP,
             sinusTkosinusP = sinusT*kosinusP, sinusTsinusP = sinusT*sinusP;

      // project vertices onto the 2D viewport
      Point[] tocki;
      tocki = new Point[ stranaKocka.length ];
      int j;
      int merka = sirina/4;
      float blisku = 3;  // distance from eye to near plane
      float bliskuDoObjekt = 1.5f;  // distance from near plane to center of object
      for ( j = 0; j < stranaKocka.length; ++j ) {
         int x0 = stranaKocka[j].x;
         int y0 = stranaKocka[j].y;
         int z0 = stranaKocka[j].z;

         // compute an orthographic projection
         float x1 = kosinusT*x0 + sinusT*z0;
         float y1 = -sinusTsinusP*x0 + kosinusP*y0 + kosinusTsinusP*z0;

         // now adjust things to get a perspective projection
         float z1 = kosinusTkosinusP*z0 - sinusTkosinusP*x0 - sinusP*y0;
         x1 = x1*blisku/(z1+blisku+bliskuDoObjekt);
         y1 = y1*blisku/(z1+blisku+bliskuDoObjekt);

         // the 0.5 is to round off when converting to int
         tocki[j] = new Point(
            (int)(sirina/2 + merka*x1 + 0.5),
            (int)(visina/2 - merka*y1 + 0.5)
         );
      }

      // draw the wireframe
      g.setColor( Color.LIGHT_GRAY);
      g.fillRect( 0, 0, sirina, visina );
      g.setColor( Color.white );
      for ( j = 0; j < rabovi.length; ++j ) {
         g.drawLine(
            tocki[ rabovi[j].a ].x, tocki[ rabovi[j].a ].y,
            tocki[ rabovi[j].b ].x, tocki[ rabovi[j].b ].y
         );
      }
   }

   public void mouseEntered( MouseEvent e ) { }
   public void mouseExited( MouseEvent e ) { }
   public void mouseClicked( MouseEvent e ) { }
   public void mousePressed( MouseEvent e ) {
      koordinataX = e.getX();
      koordinataY = e.getY();
      e.consume();
   }
   public void mouseReleased( MouseEvent e ) { }
   public void mouseMoved( MouseEvent e ) { }
   public void mouseDragged( MouseEvent e ) {
      // get the latest mouse position
      int novaKoordinataX = e.getX();
      int novaKoordinataY = e.getY();

      // adjust angles according to the distance travelled by the mouse
      // since the last event
      a -= novaKoordinataX - koordinataX;
      izvishuvanje += novaKoordinataY - koordinataY;

      // update the backbuffer
      crtajKocka( grafika );

      // update our data
      koordinataX = novaKoordinataX;
      koordinataY = novaKoordinataY;

      repaint();
      e.consume();
   }

   public void update( Graphics g ) {
      g.drawImage( slika, 0, 0, this );
      showStatus("Izvishuvanje "+izvishuvanje+" A: "+a+" ");
   }

   public void paint( Graphics g ) {
      update( g );
   }
}