import java.util.ArrayList;

public class PrimerThread extends Thread {

	private int[] ramFake;
	private int[] tablaPags;
	private ArrayList<Integer> secuencia;
	private int[] edad;
	private ArrayList<Integer> buffer;
	private int numMarcos;
	private int fallosPag;

	public PrimerThread(ArrayList<Integer> pSecuencia, int[] pRamFake, int[] pTablaPags, int[] edad,
			ArrayList<Integer> buffer, int numMarcos) {
		ramFake = pRamFake;
		tablaPags = pTablaPags;
		secuencia = pSecuencia;
		fallosPag = 0;
		this.edad = edad;
		this.buffer = buffer;
		this.numMarcos = numMarcos;
	}

	public void run() {
		int cargadoARam = 0;
		for (int i = 0; i < secuencia.size(); i++) {
			try {
				int actual = secuencia.get(i);
				synchronized (ramFake) {
					buffer.add(actual);
					if(tablaPags[actual] == -1)
					{
						fallosPag++;
						if (cargadoARam < numMarcos) {
							ramFake[cargadoARam] = actual;
							edad[cargadoARam] = 0;
							tablaPags[actual] = cargadoARam;
							cargadoARam++;
						}
						else 
						{ 
							int index = indicePagMenosUsada();
							tablaPags[ramFake[index]] = -1;
							ramFake[index] = actual;
							edad[index] = 0;
							tablaPags[actual] = index;
						}
					}
				}

				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Numero total de fallos de pagina: " + fallosPag);
	}

	public int indicePagMenosUsada() {
		int index = 0;
		for (int i = 1; i < edad.length; i++) {
			if (edad[i] < edad[index])
				index = i;
		}
		return index;
	}

}
