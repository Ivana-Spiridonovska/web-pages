import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ApletNitka extends JApplet implements ActionListener {
    private JButton kopce1, kopce2;
    private JTextArea tekst;
    private ApletNitka a=this;
    private String deaktiviraj1 ="Деактивирај прва нитка ";
    private String deaktiviraj2 ="Деактивирај втора нитка ";    
    private String aktiviraj1 ="Активирај прва нитка ";
    private String aktiviraj2 ="Активирај втора нитка";
    private NitkaPrva nitka1;
    private NitkaVtora nitka2;
    private Thread nitka;
    
    public void init () {
        
        Container c=getContentPane();
        JPanel panela1=new JPanel();
        kopce1 = new JButton(aktiviraj1);
        panela1.add(kopce1);
        c.add(panela1,BorderLayout.NORTH);
       
        JPanel panela2=new JPanel();
        kopce2 = new JButton (aktiviraj2);
        panela2.add(kopce2);
        c.add(panela2,BorderLayout.SOUTH);
        kopce1.addActionListener(this);
        kopce2.addActionListener(this);
       
        tekst=new JTextArea();
        JScrollPane s = new JScrollPane(tekst);
        s.setHorizontalScrollBarPolicy
        (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
        tekst.setCaretPosition(tekst.getText().length()); 
        c.add(s,BorderLayout.CENTER);
        
       
        nitka1 = new NitkaPrva(this);
        nitka1.start();
        nitka2 = new NitkaVtora(this);
        nitka = new Thread(nitka2);
        nitka.start();
    }
        
   
    public void actionPerformed(ActionEvent a) {
        if(a.getSource()==kopce1) {
            if(kopce1.getText().equals(aktiviraj1)) {
                kopce1.setText(deaktiviraj1);
                nitka1.setLooping(true);
                
            }
            else if(kopce1.getText().equals(deaktiviraj1)) {
                kopce1.setText(aktiviraj1);
                nitka1.setLooping(false);
            }
        } 
        else if(a.getSource()==kopce2) {
            if(kopce2.getText().equals(aktiviraj2)) {
                kopce2.setText(deaktiviraj2);
                nitka2.setLooping(true);
            }
            else if(kopce2.getText().equals(deaktiviraj2)) {
                kopce2.setText(aktiviraj2);
                nitka2.setLooping(false);
            }
            
        } 
    } 
    
  
    public synchronized void setThreadText(String s) { 
        tekst.append(s);
    } 
}	
            
    
      