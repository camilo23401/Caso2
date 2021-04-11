import java.util.ArrayList;

public class SegundoThread extends Thread {
    private int[] ramFake;
    private int[] edad;
    private ArrayList<Integer> buffer;
    private PrimerThread primero;

    public SegundoThread (int[] ramFake, int[] edad, ArrayList<Integer> buffer, PrimerThread primero){
        this.edad = edad;
        this.ramFake = ramFake;
        this.buffer = buffer;
        this.primero = primero;
    }

    public void run(){
        while (true){
            synchronized (ramFake) {
                for (int i=0; i<ramFake.length; i++){
                    edad[i] = edad[i] >> 1;
                    
                    int pag =  ramFake[i];
                    if (buffer.contains(pag)){
                        edad[i] += 536870912; 
                    }
                }
                buffer.clear();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!primero.isAlive()) break;
        }
    }
}
