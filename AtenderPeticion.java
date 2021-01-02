import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AtenderPeticion implements Runnable {
	private Socket cliente1;
	private Socket cliente2;
	private Socket cliente3;

	//Servidor multihilo para atender a los 3 jugadores
	//Creo un hilo con tres clientes
	public AtenderPeticion(Socket s1, Socket s2, Socket s3) {
		this.cliente1 = s1;
		this.cliente2 = s2;
		this.cliente3 = s3;
	}

	public void run() {
       Concurso c =new Concurso(cliente1,cliente2,cliente3);
    }
}
