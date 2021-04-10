import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Principal {

	private static int numMarcos;
	private static int numPaginas;
	
	private static int[] ramFake;
	private static int[] edad;	//mismo tamaño que la ram
	private static int[] tablaPags;
	private static ArrayList<Integer> secuencia;
	
	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader("Caso2/data/referencias4_8.txt");
			BufferedReader br =  new BufferedReader(reader); 

			numMarcos = Integer.parseInt(br.readLine());
			numPaginas = Integer.parseInt(br.readLine());

			ramFake = new int[numMarcos];
			edad = new int[numMarcos];
			ArrayList<Integer> buffer = new ArrayList<Integer>();

			br.readLine();

			String linea;
			secuencia =  new ArrayList<Integer>();
			int i = 0;
			while ((linea = br.readLine()) != null){
				secuencia.add(Integer.parseInt(linea));
			}
			br.close();

			tablaPags = new int[numPaginas];
			for (i=0; i<numPaginas; i++){
				tablaPags[i] = -1;
			}
			
			PrimerThread primero = new PrimerThread(secuencia, ramFake, tablaPags, edad, buffer, numMarcos);
			SegundoThread segundo = new SegundoThread(ramFake, edad, buffer);
			
			primero.start();
			segundo.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
