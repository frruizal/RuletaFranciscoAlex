import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
		System.out.println("----------RULETA DE LA SUERTE----------");
		try {
			DataInputStream dis1=new DataInputStream(cliente1.getInputStream());
			DataInputStream dis2=new DataInputStream(cliente2.getInputStream());
			DataInputStream dis3=new DataInputStream(cliente3.getInputStream());
			
			PrintStream pw1=new PrintStream(cliente1.getOutputStream(),true);
			PrintStream pw2=new PrintStream(cliente2.getOutputStream(),true);
			PrintStream pw3=new PrintStream(cliente3.getOutputStream(),true);
			
			Random randon =new Random(); //Para que los paneles sean randon
			String paneles[] =new String[5];
			paneles[0]="sistemas distribuidos es muy divertido";
			paneles[1]="esta ruleta va a ser un diez";
			paneles[2]="a por el bote oe";
			paneles[3]="la ruleta de la suerte";
			paneles[4]="nos gusta mucho programar hilos";
			int aleatorio=randon.nextInt(5);
			String palabra=paneles[aleatorio];
				 
			int [] premios = {25,50,75,100,150,200,0,-1}; //Para que los premios sean random
			int contador1=0; //marcador del primer jugador
			int contador2=0; //marcador del segundo jugador
			int contador3=0; //marcador del tercer jugador
			
			char[] palabraGuiones=new char[palabra.length()];
			
			for (int i=0; i<palabra.length();i++) { //Para poner las palabras en caracteres ----- ...
				if(palabra.charAt(i)==' ') {
					palabraGuiones[i]=' ';
					
				}else {
					palabraGuiones[i]='-';
					
				}
			}
			pw1.println("Bienvenido a la ruleta de la suerte");
			pw2.println("Bienvenido a la ruleta de la suerte");
			pw3.println("Bienvenido a la ruleta de la suerte");
			pw1.print("El panel a resolver es: " );
			pw2.print("El panel a resolver es " );
			pw3.print("El panel a resolver es " );
			pw1.println(palabraGuiones);
			pw2.println( palabraGuiones);
			pw3.println( palabraGuiones);
			boolean estaLetra=false; //Para ver si la letra dicha por el jugador esta o no
			boolean resolver=false; //Para ver si se ha resuelto bien el panel o no
			boolean b=false;
			boolean esvocal=false; //Para ver si es vocal
			int turno=1; //Para ver que jugador tiene el turno
			String linea = null;
			
			//String linea2=dis2.readLine();
			while(b==false) {
				estaLetra=false;
				resolver=false;
				int valor = premios[(int) Math.floor(Math.random() * premios.length)];
				if(turno==1) {
					pw1.println("Tu turno jugador 1");
					if(valor!=-1 && valor!=0) {
						pw1.println("La casilla en la que has caido es: " + valor);
						linea=dis1.readLine();
					}else {
						if(valor==0) {
							pw1.println("Has caido en el pierde turno");
							pw1.println("Has perdido el turno");
							turno = 2;
						}else {
							pw1.println("Has caido en la quiebra");
							pw1.println("Has perdido el turno y todo tu dinero");
							turno = 2;
							contador1=0;
						}
					}
					
					
				}
				else {
					if(turno==2) {
						pw2.println("Tu turno jugador 2");
						if(valor!=-1 && valor!=0) {
							pw2.println("La casilla en la que has caido es: " + valor);
							linea=dis2.readLine();
						}else {
							if(valor==0) {
								pw2.println("Has caido en el pierde turno");
								pw2.println("Has perdido el turno");
								turno = 3;
							}else {
								pw2.println("Has caido en la quiebra");
								pw2.println("Has perdido el turno y todo tu dinero");
								turno = 3;
								contador2=0;
							}
						}
						
					}
					else {
						if(turno==3) {
							pw3.println("Tu turno jugador 3");
							if(valor!=-1 && valor!=0) {
								pw3.println("La casilla en la que has caido es: " + valor);
								linea=dis3.readLine();
							}else {
								if(valor==0) {
									pw3.println("Has caido en el pierde turno");
									pw3.println("Has perdido el turno");
									turno = 1;
								}else {
									pw3.println("Has caido en la quiebra");
									pw3.println("Has perdido el turno y todo tu dinero");
									turno = 1;
									contador3=0;
								}
							}
							
						}
					}
				}
				
				
				if(linea.length()==1 && valor!=-1 && valor!=0) { //Que se haya introducido una letra
					
					for (int i=0; i<palabra.length();i++) {
						if (palabra.charAt(i) == linea.charAt(0)) { //que este la letra introducida
							
							palabraGuiones[i]=linea.charAt(0);
							estaLetra=true;
							if((linea.charAt(0)=='a'||linea.charAt(0)=='e'||linea.charAt(0)=='i'||linea.charAt(0)=='o'||linea.charAt(0)=='u')&& esvocal==true) {
								valor=0;
							}
							if((linea.charAt(0)=='a'||linea.charAt(0)=='e'||linea.charAt(0)=='i'||linea.charAt(0)=='o'||linea.charAt(0)=='u')&& esvocal==false) {
								valor=-50;
								esvocal=true;
							}
							if(turno==1) {
								
								contador1 = contador1 + valor;
								
							}
							else {
								if(turno==2) {
									contador2 = contador2 + valor;
									
								}
								else {
									if(turno==3) {
										contador3 = contador3 + valor;
										
									}
								}
							}
							
						}					
					}
					
				}else {
					if(palabra.equals(linea)) {
						b=true;
						resolver=true;
						System.out.println("Has acertado el panel.");
					}
				}
					
				if (estaLetra==false && linea.length()==1 && valor>0) {
					System.out.println("La letra "+linea.charAt(0)+" no esta");
					
					if(turno==1) {
						turno=2;
						pw1.println("La letra no esta: Has perdido el turno");
					}
					else {
						if(turno==2) {
							turno=3;
							pw2.println("La letra no esta: Has perdido el turno");
						}
						else {
							if(turno==3) {
								turno=1;
								pw3.println("La letra no esta: Has perdido el turno");
							}
						}
						
					}
										
				}
				
				if (resolver==false && linea.length()>1) {
					System.out.println("La resolución no es correcta");
					
					if(turno==1) {
						turno=2;
						pw1.println("Has fallado el panel: Has perdido el turno");
						
					}
					else {
						if(turno==2) {
							turno=3;
							pw2.println("Has fallado el panel: Has perdido el turno");
							
						}
						else {
							if(turno==3) {
								turno=1;
								pw3.println("Has fallado el panel: Has perdido el turno");
							}
						}
						
					}
										
				}
				//}
				pw1.println(palabraGuiones);
				pw2.println(palabraGuiones);
				pw3.println(palabraGuiones);
				
				pw1.println("Llevas acumulado "+ contador1 +" euros");
				pw2.println("Llevas acumulado "+ contador2 +" euros");
				pw3.println("Llevas acumulado "+ contador3 +" euros");
				
				System.out.print("El panel a resolver es:  ");
				System.out.println(palabraGuiones);
				System.out.println("El jugador 1 lleva " + contador1 + "€");
				System.out.println("El jugador 2 lleva " + contador2 + "€");
				System.out.println("El jugador 3 lleva " + contador3 + "€");
				
				if(String.valueOf(palabraGuiones).equals(palabra)) { //Si se ha resuelto el panel
					b=true;
					
				}
			}
			if(turno==1) {
				pw1.println("¡¡¡¡ENHORABUENA HAS RESUELTO EL PANEL!!!!"); 
				pw1.println("¡¡¡¡HAS GANADO "+ contador1 +" EUROS!!!!"); 
				pw2.println("----------FIN DEL PANEL: Has ganado 0 euros----------"); 
				pw3.println("----------FIN DEL PANEL: Has ganado 0 euros----------");
				pw3.println("El panel correcto era: "+palabra); 
				pw2.println("El panel correcto era: "+palabra);
			}
			else {
				if(turno==2) {
					pw2.println("¡¡¡¡ENHORABUENA HAS RESUELTO EL PANEL!!!!"); 
					pw2.println("¡¡¡¡HAS GANADO "+ contador2 +" EUROS!!!!"); 
					pw1.println("----------FIN DEL PANEL: Has ganado 0 euros----------"); 
					pw3.println("----------FIN DEL PANEL: Has ganado 0 euros----------");
					pw1.println("El panel correcto era: "+palabra); 
					pw3.println("El panel correcto era: "+palabra);
				}
				else {
					if(turno==3) {
						pw3.println("¡¡¡¡ENHORABUENA HAS RESUELTO EL PANEL!!!!"); 
						pw3.println("¡¡¡¡HAS GANADO "+ contador3 +" EUROS!!!!"); 
						pw2.println("----------FIN DEL PANEL: Has ganado 0 euros----------"); 
						pw1.println("----------FIN DEL PANEL: Has ganado 0 euros----------");
						pw1.println("El panel correcto era: "+palabra); 
						pw2.println("El panel correcto era: "+palabra);
					}
				}
			}
			
			System.out.println("----------FIN DEL PANEL----------");
			System.out.println("----------LO HA RESUELTO EL JUGADOR "+ turno+"----------");
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



