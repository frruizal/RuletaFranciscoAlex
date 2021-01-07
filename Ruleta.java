import java.io.PrintWriter;
import java.util.Scanner;

public class Ruleta {
	String puntuacion;
	
	public Ruleta(String puntuacion) {
		this.puntuacion=puntuacion;
	}
	
	public void tiradaRuleta(PrintWriter pw, String linea) {
		Scanner entrada= new Scanner(System.in);
		if(linea.startsWith("25")||linea.startsWith("50")||linea.startsWith("75")||linea.startsWith("100")||linea.startsWith("150")||linea.startsWith("200")||linea.startsWith("Q")||linea.startsWith("PT")) {
			System.out.println("El resultado de la tirada a la ruleta es: ");
			System.out.println("         _____ ");
			if(linea.length()==1) {
				System.out.println("       /\\  "+linea+" /\\");
			}
			if(linea.length()==2) {
				System.out.println("       /\\ "+linea+" /\\");
			}
			if(linea.length()==3) {
				System.out.println("       /\\ "+linea+"/\\");
			}
			
			System.out.println("      /  \\  /  \\");
			System.out.println("     /____\\/____\\");
			System.out.println("     \\    /\\    /");
			System.out.println("      \\  /  \\  /");
			System.out.println("       \\/____\\/");
			if(linea.startsWith("25")||linea.startsWith("50")||linea.startsWith("75")||linea.startsWith("100")||linea.startsWith("150")||linea.startsWith("200")){
					System.out.println("Introduzca una LETRA o la palabra en caso de que quiera RESOLVER"); 
					System.out.println("Para comprar una VOCAL solo la tienes que introducir: Perderas 50 euros"); 
					String letra =entrada.nextLine(); 
					pw.println(letra);
			}
		}
		else {
			System.out.println(linea);
		}
	}
}
