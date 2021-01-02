import java.io.*;
import java.net.*;
import java.sql.Date;
import java.util.concurrent.*;

public class Servidor{
	//Asignar un nuevo Thread a cada nueva conexión
	//Lo hacemos con el pool de hilos
	public static void main(String [] args) {
		ServerSocket ss = null;
		ExecutorService pool = Executors.newCachedThreadPool();
		try {
			ss = new ServerSocket(7777);
			while(true) {
				try {
					final Socket cliente1 = ss.accept();
					final Socket cliente2 = ss.accept();
					final Socket cliente3 = ss.accept();
					pool.execute(new AtenderPeticion(cliente1,cliente2, cliente3));
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			pool.shutdown();
		}
	}

}
