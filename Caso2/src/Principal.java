import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Principal {

	private static int numMarcos;
	private static int numPaginas;
	private static double ptgeLocalidad;

	private static int[] ramFake;
	private static int[] edad;
	private static int[] tablaPags;
	private static ArrayList<Integer> secuencia;

	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader("./data/referencias1.txt");
			BufferedReader br =  new BufferedReader(reader);

			numMarcos = Integer.parseInt(br.readLine());
			numPaginas = Integer.parseInt(br.readLine());

			ramFake = new int[numMarcos];
			edad = new int[numMarcos];

			for(int i=0;i<numMarcos;i++){
				ramFake[i] = -1;
			}
			
			

			ArrayList<Integer> buffer = new ArrayList<Integer>();

			ptgeLocalidad = Double.parseDouble(br.readLine());
			
			System.out.println("Información de la referencia");
			System.out.println("   # de marcos: " + numMarcos);
			System.out.println("   # de paginas: " + numPaginas);
			System.out.println("   % de localidad: " + ptgeLocalidad);

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
			SegundoThread segundo = new SegundoThread(ramFake, edad, buffer, primero);

			primero.start();
			segundo.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
