import java.io.DataInputStream;
import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args) {
		Socket s = null;
		DataInputStream dis = null;
		PrintWriter pw = null;
		try {
			s = new Socket("localhost", 8000);
			dis = new DataInputStream(s.getInputStream());
			DataInputStream entrada= new DataInputStream(System.in);
			boolean b=false;
			String linea;
			pw = new PrintWriter(s.getOutputStream(),true);
			linea=dis.readLine();
			//while ((linea=dis.readLine())!=null) {
			while (linea!="fin") {
				///if (linea.contains("Introduce una letra")) {
					System.out.println(linea);
					
					
						System.out.println("Introduzca una letra"); 
						String letra =entrada.readLine(); 
						pw.println(letra);
						linea=dis.readLine();
					}
					
				
				
			//}*/
			//String hola=entrada.readLine();
			//System.out.println(hola);
			
			System.out.println("Adios");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(s!=null) {
					s.close();
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}}}

