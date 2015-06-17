/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mojaplet;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Ivana
 */
public class MojAplet extends JApplet {
  private JTextArea poraki;
  private CheckboxGroup c;

    /**
     * @param args the command line arguments
     */
 public void init() {
  setLayout(null);
  setSize(450,500);
  poraki=new JTextArea();
   c = new CheckboxGroup();
  Checkbox c1=new Checkbox("Црвена", c, false);
  Checkbox c2=new Checkbox("Сина", c, false);
  Checkbox c3=new Checkbox("Жолта", c, false);
  Checkbox c4=new Checkbox("Зелена", c, false);
  Checkbox c5=new Checkbox("Портокалова", c, false);
  Checkbox c6=new Checkbox("Сива", c, false);
 add(c1);
 add(c2);
 add(c3);
 add(c4);
 add(c5);
 add(c6);
 add(poraki);
 c1.setBounds(0,0,70,50);
 c2.setBounds(70,0,70,50);
 c3.setBounds(140,0,70,50);
 c4.setBounds(210,0,70,50);
 c5.setBounds(280,0,100,50);
 c6.setBounds(380,0,70,50);
 
 c1.addItemListener(new BojaKlasa(Color.RED));
 c2.addItemListener(new BojaKlasa(Color.BLUE));
 c3.addItemListener(new BojaKlasa(Color.YELLOW));
 c4.addItemListener(new BojaKlasa(Color.GREEN));
 c5.addItemListener(new BojaKlasa(Color.ORANGE));
 c6.addItemListener(new BojaKlasa(Color.GRAY));
 poraki.setBounds(50,80,100,250);
 poraki.append("Иницијализација\n");
 }
 class BojaKlasa implements ItemListener{
     Color pozadinaBoja;

        public BojaKlasa(Color boja) {
            pozadinaBoja=boja;
        }
     
  public void itemStateChanged(ItemEvent e){
      getContentPane().setBackground(pozadinaBoja);
      repaint();
      }
 }
 
    @Override
    public void paint(Graphics g) {
      poraki.append("Цртање\n");  
      g.setColor(Color.DARK_GRAY);
    }

    @Override
    public void repaint() {
       poraki.append("Прецртување\n");
    }
   
  @Override
    public void start() {
       poraki.append("Почеток\n");
    }
  
    @Override
    public void stop() {
      poraki.append("Прекин\n");
    }
  
    @Override
    public void destroy() {
        poraki.append("Уништување\n");
    }
}

