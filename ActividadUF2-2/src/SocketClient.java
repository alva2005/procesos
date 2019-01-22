/*Implementaci�n de la clase SocketCliente*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
	public static void main(String[] args) {
		Socket socketCliente = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;
   
	
		String ipServidor = "localhost";
		//Si se quiere probar en una misma m�quina sustituir la ip por localhost	
		int puertoServidor = 2017;
		// nos conectamos al servidor a traves de esta direcci�n IP
		System.out.println("        APLICACI�N CLIENTE");  
		  System.out.println("-----------------------------------");   
		Scanner lector = new Scanner(System.in);   
		try {
			 /*Creamos el socket del cliente y le asignamos la ip, 
		     *  y el puerto por el que va a comunicarse con el servidor*/
			
			 System.out.println("Esperando a que el servidor acepte la conexi�n"); 
			socketCliente = new Socket(ipServidor, puertoServidor);
			/*Esto es lo que se envia y se recibe del servidor*/
			entrada = new InputStreamReader(socketCliente.getInputStream());
			salida = new PrintStream(socketCliente.getOutputStream());
			
			/*En este caso para evitar tener que poner un l�mite a los mensajes vamos a usar 
			 * la clase BufferedReader para crear un buffer y tener una l�nea completa y 
			 * en vez de enviarlos como array de bytes */
			
			BufferedReader bf = new BufferedReader(entrada);
			System.out.println("Comunicaci�n establecida"); 
			
			/* Por pantalla se pide el nombre del cliente que se envia al servidor, m�s concretamente
			 * al hilo creado por el servidor para la comunicaci�n con el cliente*/
		   System.out.println("Escribe tu nombre: ");  
			String nombre = lector.nextLine();
			salida.println(nombre);
			
			/*Mientras el cliente no escriba fin (en may�sculas o min�sculas) el cliente va 
		     * mandando mensajes que ha introducido por teclado que se env�an*/
			/*Tambi�n se lee lo que env�a el servidor que se muestra por pantalla*/
			
			String texto="";
			 while (!texto.toUpperCase().equals("FIN")) {     
			    	System.out.println("Escribe mensaje (FIN para terminar): ");     
			    	texto = lector.nextLine();
			    	salida.println(texto);		    	
					String mensaje = bf.readLine();
			    	System.out.println("Servidor responde:" +new String(mensaje));
			    	
					
			    	} 
			
			
		} catch (UnknownHostException excepcion) {
			System.err.println("No encuentro el servidor en la direcci�n" + ipServidor);
		} catch (IOException excepcion) {
			System.err.println("Error de entrada/salida");
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}finally {	
			/* Al escribir fin se cierra la comunicaci�n*/
			try {
				salida.close();
				entrada.close();
				
				socketCliente.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
