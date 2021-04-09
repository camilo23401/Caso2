import java.util.ArrayList;

public class PrimerThread extends Thread{
	
	private int[] ramFake;
	private int[] tablaPags;
	private int[] secuencia;
	private int[] edad;
	private ArrayList<Integer> buffer;
	private int numMarcos;

	private int fallosPag;
	
	public PrimerThread(int[] pSecuencia, int[] pRamFake, int[] pTablaPags,  int[] edad, ArrayList<Integer> buffer, int numMarcos)
	{
		ramFake = pRamFake;
		tablaPags = pTablaPags;
		secuencia = pSecuencia;
		fallosPag = 0;
		this.edad = edad;
		this.buffer = buffer;
		this.numMarcos = numMarcos;
	}
	
	public void run()
	{
		int cargadoARam = 0;
		for(int i=0;i<secuencia.length;i++)
		{
			try
			{
				int actual = secuencia[i];
				synchronized (ramFake){
					buffer.add(actual);
					if(cargadoARam<numMarcos)
					{
						fallosPag++;
						ramFake[cargadoARam] = actual;
						tablaPags[actual] = cargadoARam;
						cargadoARam++;
					}
					//Revisar si la pagina est� en "ram" con la tabla de p�ginas
					else if (tablaPags[actual] == -1){
						//Si no est�, fallo de pag
						fallosPag++;
						//Reemplazar por la pag de la ram menos llamada
						int index = indicePagMenosUsada();
						tablaPags[ramFake[index]] = -1;
						ramFake[index] = actual;
						tablaPags[actual] = index;
					}
				}

				Thread.sleep(5);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println(fallosPag);
	}

	private int indicePagMenosUsada(){
		synchronized (edad){
			int index = 0;
			for (int i=1; i<edad.length; i++){
				if (edad[i]<edad[index])
				index = i;
			}
			return index;
		}
	}

}
