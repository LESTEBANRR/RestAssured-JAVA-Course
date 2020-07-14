import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
public class Main {
	
private static String archivoUrl="C:\\Archivo.txt";

	public static void main(String[] args) throws IOException {
		
		BufferedReader br= LectorArchivo.readFile(archivoUrl);		
		List<String> fileArray=LectorArchivo.getItems(br);	
		System.out.println("Orden Normal");
		LectorArchivo.DisplayList(fileArray);
		System.out.println("\nOrden Invertido");
		LectorArchivo.ReverseList(fileArray);
		System.out.println("\nOrdenamiento Burbuja");
		LectorArchivo.BubbleOrder(fileArray);
	}

}
