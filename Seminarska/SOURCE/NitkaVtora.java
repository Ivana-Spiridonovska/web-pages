
public class NitkaVtora implements Runnable {
    
    private int frekvencija = 1000;
    boolean animacija = false;
    ApletNitka a; 
    private String poraka = "Втора нитка се извршува\n"; 
   
    NitkaVtora(ApletNitka a) {
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
            a.setThreadText(poraka);
        }
    }
    
    
    public synchronized void  setLooping(boolean b) {
        animacija = b;
            if (animacija)
            notify();
    } 
} 	