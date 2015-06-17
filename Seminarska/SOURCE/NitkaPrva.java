
            
    
                 

public class NitkaPrva extends Thread {
    private boolean animacija=false;
    private ApletNitka a; 
    private int frekvencija = 1000; 
    private String poraka = "Прва нитка се извршува\n"; 
    
  
    NitkaPrva(ApletNitka a) {
        this.a=a;
    } 
    
  
    public void run() {
        while(true) {
            try {
                Thread.sleep(frekvencija);
                synchronized(this) {
                while (!animacija)
                    wait();
                } 
            } 
            catch (InterruptedException e) {
                         
            } 
            a.setThreadText(poraka);//callback to make the text visible in the GUI	
        } 		
    }
    
   
    public synchronized void  setLooping(boolean b) {
        animacija = b;
        if (animacija)
            notify();
    } 
}