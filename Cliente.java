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
			String linea;
			pw = new PrintWriter(s.getOutputStream(),true);

			//DataInputStream entrada = new DataInputStream(System.in);
			pw.println("Hola soy el cliente");
			while ((linea=dis.readLine())!=null) {
				///if (linea.contains("Introduce una letra")) {
					System.out.println(linea);
	
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

