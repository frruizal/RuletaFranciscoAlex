import java.io.DataInputStream;
import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args) {
		Socket s = null;
		DataInputStream dis = null;
		PrintWriter pw = null;
		try {
			s = new Socket("localhost", 7777);
			dis = new DataInputStream(s.getInputStream());
			String linea = dis.readLine();
			pw = new PrintWriter(s.getOutputStream());

			DataInputStream entrada = new DataInputStream(System.in);

			while (linea != null) {
				if (linea.contains("Introduce una letra")) {
					System.out.println(linea);
					pw.println(entrada.readLine());
				} else {
					System.out.println(linea);
				}
				linea = dis.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
