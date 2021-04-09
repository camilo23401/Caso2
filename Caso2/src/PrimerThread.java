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
				//Revisar si la secuencia está en "ram" con la tabla de páginas
				//Si si está, solo se hace el corrimiento 
				//Si no está, añade la página al buffer para que el algoritmo de envejecimiento la tenga en cuenta
				this.sleep(5);
			}
			catch(Exception e)
			{
				e.getMessage();
			}
		}
		
	}

}
