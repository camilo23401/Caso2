import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collector;

public class Principal {

	private static int numMarcos;
	private static int numPaginas;
	private static int nivLocalidad;
	
	private static int[] ramFake = {0,0,0,0,0,0,0,0};
	private static int[] edad = {0,0,0,0,0,0,0,0};	//mismo tamaño que la ram
	private static int[] tablaPags;
	private static int[] secuencia;
	
	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader("data/referencias3.txt");
			BufferedReader br =  new BufferedReader(reader); 

			numMarcos = Integer.parseInt(br.readLine());
			numPaginas = Integer.parseInt(br.readLine());
			br.readLine();

			String linea = "";
			secuencia =  new int[1000];
			int i = 0;
			while ((linea = br.readLine()) != null){
				secuencia[i++] = Integer.parseInt(linea);
			}
			br.close();

			tablaPags = new int[numPaginas];
			for (i=0; i<numPaginas; i++){
				tablaPags[i] = -1;
			}
			
			
			/* numMarcos = 8;
			numPaginas = 16; */
			
			ArrayList<Integer> buffer = new ArrayList<Integer>();
			
			PrimerThread primero = new PrimerThread(secuencia, ramFake, tablaPags, edad, buffer, numMarcos);
			SegundoThread segundo = new SegundoThread(ramFake, edad, buffer);
			
			primero.start();
			segundo.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
