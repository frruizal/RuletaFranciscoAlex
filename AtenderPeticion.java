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
			DataInputStream dis1=new DataInputStream(cliente1.getInputStream());
			DataInputStream dis2=new DataInputStream(cliente2.getInputStream());
			DataInputStream dis3=new DataInputStream(cliente3.getInputStream());
			
			PrintWriter pw1=new PrintWriter(cliente1.getOutputStream(),true);
			PrintWriter pw2=new PrintWriter(cliente2.getOutputStream(),true);
			PrintWriter pw3=new PrintWriter(cliente3.getOutputStream(),true);
			
			Random randon =new Random();
			String palabra="futbol";
			char[] palabraGuiones=new char[palabra.length()];
			String palabraResolver="";
			for (int i=0; i<palabraGuiones.length;i++) {
				palabraGuiones[i]='_' + ' ';
				palabraResolver+= "_ ";
			}
			
			pw1.println("La palabra: " + palabraResolver);
			pw2.println("La palabra: " + palabraResolver);
			pw3.println("La palabra: " + palabraResolver);
			boolean estaLetra=false;
			boolean b=false;
			int turno=1;
			
			//String linea2=dis2.readLine();
			while(b==false) {
				//String linea=null;
				String linea=dis1.readLine();
				
				/*if (turno==1) {
					linea=dis1.readLine();
					pw1.println("Es tu turno");
					pw2.println("NO es tu turno");
					pw3.println("NO es tu turno");
				}
				if (turno==2) {
					linea=dis2.readLine();
					pw2.println("Es tu turno");
					pw1.println("NO es tu turno");
					pw3.println("NO es tu turno");
				}
				if (turno==3) {
					linea=dis3.readLine();
					pw3.println("Es tu turno");
					pw1.println("NO es tu turno");
					pw2.println("NO es tu turno");
				}*/
				
				/*if(linea==palabra) {
					System.out.println("hodlsfkajlfkdjdlkd");
					b=true;
				}
				
				else {*/
				for (int i=0; i<palabra.length();i++) {
					if (palabra.charAt(i) == linea.charAt(0)) { //que este la palabra introducida
						//palabraGuiones +=palabra.charAt(i);
						//palabraGuiones=palabraGuiones.replace(palabraGuiones.charAt(i), linea.charAt(0));
						palabraGuiones[i]=linea.charAt(0);
						estaLetra=true;
					}					
					/*
					 * if(palabraGuiones.equals(palabra)) {
						pw.println("fin");
					}
						
					if (palabra.charAt(i) == linea2.charAt(0)) { //que este la palabra introducida
						//palabraGuiones +=palabra.charAt(i);
						//palabraGuiones=palabraGuiones.replace(palabraGuiones.charAt(i), linea.charAt(0));
						palabraGuiones[i]=linea2.charAt(0);
						j++;
					}	*/
				}
					
				if (!estaLetra) {
					System.out.println("La letra introducida no esta:");
					
					if(turno==1) {
						turno=2;
						pw1.println("La letra no esta: Has perdido el turno");
						linea=dis2.readLine();
					}
					if(turno==2) {
						turno=3;
						pw2.println("La letra no esta: Has perdido el turno");
						linea=dis3.readLine();
					}
					if(turno==3) {
						turno=1;
						pw3.println("La letra no esta: Has perdido el turno");
						linea=dis1.readLine();
					}					
				}
				//}
				pw1.println(palabraGuiones);
				pw2.println(palabraGuiones);
				pw3.println(palabraGuiones);
				System.out.println(palabraGuiones);
			}
			//pw.println("fin");
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
