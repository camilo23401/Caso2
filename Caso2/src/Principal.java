import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collector;

public class Principal {

	private static int numMarcos;
	private static int numPaginas;
	private static int nivLocalidad;
	
	private static int[] ramFake = {4,17,1,15,7,8,6,17};
	private static int[] edad = {0,0,0,0,0,0,0,0};	//mismo tamaño que la ram
	private static int[] tablaPags;
	private static int[] secuencia;
	
	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader("/Users/nicolasortega/Desktop/Caso2/Caso2/data/referencias4.txt");
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
			for (i=0; i<8; i++){
				tablaPags[i] = i;
			}
			for (i=8; i<numPaginas; i++){
				tablaPags[i] = -1;
			}
			
			
			/* numMarcos = 8;
			numPaginas = 16; */
			
			ArrayList<Integer> buffer = new ArrayList<Integer>();
			
			PrimerThread primero = new PrimerThread(secuencia, ramFake, tablaPags, edad, buffer);
			SegundoThread segundo = new SegundoThread(ramFake, edad, buffer);
			
			primero.start();
			segundo.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
