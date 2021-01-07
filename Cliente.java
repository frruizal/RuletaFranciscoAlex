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
			
			String linea;
			pw = new PrintWriter(s.getOutputStream(),true);
			
			while ((linea=dis.readLine())!=null) {
				Ruleta r=new Ruleta(linea);
				r.tiradaRuleta(pw, linea);					
			}
			

			System.out.println("FIN DEL CONCURSO: Cliente desconectado");
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

