import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
			pw.println("Hola vengo del server y soy el cliente1");
			String linea=dis.readLine();
			
			//while((linea=dis.readLine())!=null) {
				System.out.println(linea);

			//}
			
			System.out.println("Adios");
			
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
