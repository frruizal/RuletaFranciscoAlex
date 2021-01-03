import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class AtenderPeticion implements Runnable {
	private Socket cliente1=null;
	private Socket cliente2=null;
	private Socket cliente3=null;
	
	
	//Servidor multihilo para atender a los 3 jugadores
	//Creo un hilo con tres clientes
	public AtenderPeticion(Socket s1,Socket s2, Socket s3) {
		this.cliente1 = s1;
		this.cliente2 = s2;
		this.cliente3 = s3;
		
	}
	
	public void run() {
      // Concurso c =new Concurso(cliente1,cliente2,cliente3);
		System.out.println("Hola bienvenido a la ruleta de la suerte");
		try {
			DataInputStream dis=new DataInputStream(cliente1.getInputStream());
			PrintWriter pw=new PrintWriter(cliente1.getOutputStream(),true);
			
			Random randon =new Random();
			String palabra="futbol";
			char[] palabraGuiones=new char[palabra.length()];
			String palabraResolver="";
			for (int i=0; i<palabraGuiones.length;i++) {
				palabraGuiones[i]='_'+' ';
				palabraResolver+= "_ ";
			}
			pw.println("La palabra: " + palabraResolver);
			
			boolean b=false;
			while(b==false) {
				String linea=dis.readLine();
				
				for (int i=0; i<palabra.length();i++) {
					if (palabra.charAt(i) == linea.charAt(0)) { //que este la palabra introducida
						//palabraGuiones +=palabra.charAt(i);
						//palabraGuiones=palabraGuiones.replace(palabraGuiones.charAt(i), linea.charAt(0));
						palabraGuiones[i]=linea.charAt(0);
					}
					
					/*
					 * if(palabraGuiones.equals(palabra)) {
						pw.println("fin");
					}
					*/
				}
				
				pw.println(palabraGuiones);
				System.out.println(palabraGuiones);
			}
			pw.println("fin");
			System.out.println("sefini");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(cliente1!=null){
					cliente1.close();
				}
				if(cliente2!=null){
					cliente2.close();
				}
				if(cliente3!=null){
					cliente3.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
    }
}
