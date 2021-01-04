import java.io.DataInputStream;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		Socket s = null;
		DataInputStream dis = null;
		PrintWriter pw = null;
		try {
			s = new Socket("localhost", 8000);
			dis = new DataInputStream(s.getInputStream());
			Scanner entrada= new Scanner(System.in);
			boolean b=false;
			String linea=null;
			pw = new PrintWriter(s.getOutputStream(),true);
			
			//while ((linea=dis.readLine())!=null) {
			while (linea!="fin") {
				///if (linea.contains("Introduce una letra")) {
				System.out.println("Esperando al turno");
				linea=dis.readLine();	
				System.out.println(linea);
				if(linea=="fin") {
					System.out.println("------Fin de la partida---------");
				}
				if(linea.startsWith("Es tu turno")) {
					System.out.println("Tu turno: Introduzca una letra"); 
					String letra =entrada.nextLine(); 
					pw.println(letra);
				}
						
						
						
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

