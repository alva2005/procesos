/*Implementación de la clase SocketCliente*/
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
		//Si se quiere probar en una misma máquina sustituir la ip por localhost	
		int puertoServidor = 2017;
		// nos conectamos al servidor a traves de esta dirección IP
		System.out.println("        APLICACIÓN CLIENTE");  
		  System.out.println("-----------------------------------");   
		Scanner lector = new Scanner(System.in);   
		try {
			 /*Creamos el socket del cliente y le asignamos la ip, 
		     *  y el puerto por el que va a comunicarse con el servidor*/
			
			 System.out.println("Esperando a que el servidor acepte la conexión"); 
			socketCliente = new Socket(ipServidor, puertoServidor);
			/*Esto es lo que se envia y se recibe del servidor*/
			entrada = new InputStreamReader(socketCliente.getInputStream());
			salida = new PrintStream(socketCliente.getOutputStream());
			
			/*En este caso para evitar tener que poner un límite a los mensajes vamos a usar 
			 * la clase BufferedReader para crear un buffer y tener una línea completa y 
			 * en vez de enviarlos como array de bytes */
			
			BufferedReader bf = new BufferedReader(entrada);
			System.out.println("Comunicación establecida"); 
			
			/* Por pantalla se pide el nombre del cliente que se envia al servidor, más concretamente
			 * al hilo creado por el servidor para la comunicación con el cliente*/
		   System.out.println("Escribe tu nombre: ");  
			String nombre = lector.nextLine();
			salida.println(nombre);
			
			/*Mientras el cliente no escriba fin (en mayúsculas o minúsculas) el cliente va 
		     * mandando mensajes que ha introducido por teclado que se envían*/
			/*También se lee lo que envía el servidor que se muestra por pantalla*/
			
			String texto="";
			 while (!texto.toUpperCase().equals("FIN")) {     
			    	System.out.println("Escribe mensaje (FIN para terminar): ");     
			    	texto = lector.nextLine();
			    	salida.println(texto);		    	
					String mensaje = bf.readLine();
			    	System.out.println("Servidor responde:" +new String(mensaje));
			    	
					
			    	} 
			
			
		} catch (UnknownHostException excepcion) {
			System.err.println("No encuentro el servidor en la dirección" + ipServidor);
		} catch (IOException excepcion) {
			System.err.println("Error de entrada/salida");
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}finally {	
			/* Al escribir fin se cierra la comunicación*/
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
