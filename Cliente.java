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
			String linea="";
			pw = new PrintWriter(s.getOutputStream(),true);
			
			while (linea!=null) {
				
				linea=dis.readLine();	
				System.out.println(linea);
				
				if(linea.startsWith("La casilla en la que has caido es")) {
					System.out.println("Introduzca una letra"); 
					String letra =entrada.nextLine(); 
					pw.println(letra);
				}
				else {
				//	System.out.println("Esperando al turno");
				}
									
			}
			
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

