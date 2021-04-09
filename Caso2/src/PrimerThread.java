public class PrimerThread extends Thread{
	
	private int[] ramFake;
	private int[] tablaPags;
	private int[] secuencia;
	private int fallosPag;
	
	public PrimerThread(int[] pSecuencia, int[] pRamFake, int[] pTablaPags)
	{
		ramFake = pRamFake;
		tablaPags = pTablaPags;
		secuencia = pSecuencia;
		fallosPag = 0;
	}
	
	public void run()
	{
		for(int i=0;i<secuencia.length;i++)
		{
			try
			{
				int actual = secuencia[i];
				//Revisar si la secuencia est� en "ram" con la tabla de p�ginas
				//Si si est�, solo se hace el corrimiento 
				//Si no est�, a�ade la p�gina al buffer para que el algoritmo de envejecimiento la tenga en cuenta
				this.sleep(5);
			}
			catch(Exception e)
			{
				e.getMessage();
			}
		}
		
	}

}
