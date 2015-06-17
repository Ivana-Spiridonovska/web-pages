/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apletvtor;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ivana
 */
public class ApletVtor extends JApplet implements ActionListener{

    /**
     * @param args the command line arguments
     */
 
     JButton kopce1;
     JButton kopce2;
     JTextArea tekst;
     JTextField pole;
     CheckboxGroup grupi; 
     JPanel panela;
     Checkbox grupa1;
     Checkbox grupa2;
     Checkbox grupa3;
  //  JLabel labela;
     Checkbox c2;
    
 

    @Override
     public void init()
     {
        panela=new JPanel();
        panela.setLayout(null);
        panela.setSize(500,300);
        
       // setVisible(true);
        panela.setForeground(Color.GRAY);
    //   labela = new JLabel();
        kopce1 = new JButton("Прикажи");
        kopce2 = new JButton("Не тука");
        tekst=new JTextArea("Избери боја па потоа кликни на копчето прикажи!");
        pole = new JTextField("Европско првенство",200);
 
          grupi = new CheckboxGroup();
          grupa1 = new Checkbox("Црвена", grupi,false);
          grupa2 = new Checkbox("Сина", grupi,false);
          grupa3 = new Checkbox("Розова", grupi,false); 
         
          c2 = new Checkbox("Зелена",false);
         

 
          kopce1.setBounds(30,20,100,30);
          kopce2.setBounds(150,20,100,30);
          tekst.setBounds(20,50,320,20);
          pole.setBounds(20,80,200,40);
          grupa1.setBounds(20,120,80,30);
          grupa2.setBounds(100,120,80,30);
          grupa3.setBounds(180,120,80,30);
       
          c2.setBounds(100,150,80,30);
          tekst.setBackground(Color.red);
          pole.setBackground(Color.CYAN);
          

      panela.add(kopce1);
      panela.add(kopce2);
      panela.add(tekst);
      panela.add(pole);
      panela.add(grupa1);
      panela.add(grupa2);
      panela.add(grupa3);

      panela.add(c2);
    /**  
      labela.add(kopce1);
      labela.add(kopce2);
      labela.add(tekst);
      labela.add(pole);
      labela.add(grupa1);
      labela.add(grupa2);
      labela.add(grupa3);

      labela.add(c2);
     */         
      kopce1.addActionListener(this);
      kopce2.addActionListener(this);
     
      c2.addItemListener(new BojaKlasa(Color.GREEN));
    
      Scrollbar sb =new Scrollbar(Scrollbar.VERTICAL,0,20, -100, 105); 
      sb.setBounds(0,0,20,200);
      panela.add(sb);
  // panela.add(labela);
  //  this.add(labela);
    this.add(panela);
  } 
 
public void paint(Graphics g)
         {   
           if (c2.getState()&&(grupa1.getState()||grupa2.getState() || grupa3.getState()))
               JOptionPane.showMessageDialog(null,"Не може да се изберат две или повеќе во исто време");
            if (grupa1.getState()) g.setColor(Color.red);
           else if (grupa2.getState()) g.setColor(Color.blue);
           else if(grupa3.getState()) g.setColor(Color.PINK);
        
           else g.setColor(Color.green);
           g.drawString(pole.getText(),40,90);
      }
        public void actionPerformed(ActionEvent a) 
         {
              if (a.getSource() == kopce1) 
                   repaint();
          else if (a.getSource() == kopce2) 
          { 
               kopce2.setLabel("Не тука!");
               pole.setText("Тоа е погрешно копче!");
               repaint();
          }}
 
    class BojaKlasa implements ItemListener{
     Color pozadinaBoja;

        public BojaKlasa(Color boja) {
            pozadinaBoja=boja;
        }
     
        @Override
  public void itemStateChanged(ItemEvent e){
      getContentPane().setForeground(pozadinaBoja);
      repaint();
      }
      
}
}
    
     

