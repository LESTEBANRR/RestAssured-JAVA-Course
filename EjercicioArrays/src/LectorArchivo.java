import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivo {

	public static BufferedReader readFile(String archivoUrl) throws FileNotFoundException {
		File archivo = new File(archivoUrl);
		FileReader fr = new FileReader(archivo);
		BufferedReader br= new BufferedReader(fr);
		return br;
	}
	
	public static void DisplayList(List<String> fileArray) {
		for(int i=0;i<fileArray.size();i++) {
			System.out.println(fileArray.get(i));
		}
	}
	
	public static void ReverseList(List<String> fileArray) {
		int listSize=fileArray.size()-1;		
		
		String temp;
		for(int i=0;i <=(((listSize+1)/2)-1);i++) {
			temp=fileArray.get(i);
			//fileArray.remove(i);
			fileArray.set(i, fileArray.get(listSize-i));
			//fileArray.remove(listSize-i);
			fileArray.set(listSize-i,temp);
		}
		DisplayList(fileArray);
	}
	
	public static void BubbleOrder(List<String> fileArray) {
		int listSize=fileArray.size()-1;
		String temp;
		for (int i = 1; i <= (fileArray.size()-1); i++) {
			for (int j = 0; j < (fileArray.size()-i); j++) {
				if(fileArray.get(j).compareToIgnoreCase(fileArray.get(j+1))>0) {
					temp=fileArray.get(j);
					//fileArray.remove(j);
					fileArray.set(j, fileArray.get(j+1));
					//fileArray.remove(j+1);
					fileArray.set((j+1), temp);
				}
			}
		}
		LectorArchivo.DisplayList(fileArray);
	}
	
	public static List<String> getItems(BufferedReader br) throws IOException {
		String linea=null;
		List<String> fileArray=(List<String>) new ArrayList<String>();
		do {
			linea= br.readLine();	
			String cadena="";
			
			if(linea!=null) {
			int length=linea.length();			
			for (int i = 0; i < length; i++) {
				
				char ch=linea.charAt(i);
				
				if(ch==',' || ch == ' ') {
					if(cadena.length()>0) {
						fileArray.add(cadena);
					}					
					cadena="";
				}else {
					cadena+=linea.charAt(i);
					if(i>=(length-1) && cadena.length()>0) {
						fileArray.add(cadena);
					}					
				}
				
			  }
			
			}
			
		}while(linea!=null);
		return fileArray;
	}
	
}
