/*Implementaci�n de la clase SocketCliente*/
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.net.InetSocketAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 

 public class SocketCliente { 
  public static void main(String[] args) {  
	  String ip ="79.153.233.111";
	  //String ip ="localhost";
	  //int puerto=2000;
	  int puerto=2017;
	  System.out.println("        APLICACI�N CLIENTE");  
	  System.out.println("-----------------------------------");      
	  Scanner lector = new Scanner(System.in);   
	  try { 
		  /*Creamos el socket del cliente y le asignamos la ip, en este caso localhost,
		     *  y el puerto por el que va a comunicarse con el servidor*/
		    Socket cliente = new Socket();    
		    InetSocketAddress direccionServidor = new InetSocketAddress(ip,puerto);   
		    System.out.println("Esperando a que el servidor acepte la conexi�n"); 
		    cliente.connect(direccionServidor);    
		    
		    System.out.println("Comunicaci�n establecida para consulta de almac�n");        
		    
		    InputStream entrada = cliente.getInputStream();    
		    OutputStream salida = cliente.getOutputStream();      
		    /*Mientras el cliente no escriban fin ( en may�sculas o min�sculas) el cliente va 
		     * pidiendo por teclado los c�digos de los productos*/
		    String texto = "";   
		    while (!texto.toUpperCase().equals("FIN")) {     
		    	System.out.println("Escribe c�digo del art�culo (FIN para terminar): ");     
		    	texto = lector.nextLine();     
		    	/*Enviamos el c�digo del producto como bytes*/
		    	salida.write(texto.getBytes());     
		    	byte[] mensaje = new byte[100];     
		    	
		    	System.out.println("Esperando respuesta ...... "); 
		    	/*Esperamos la respuesta del servidor en bytes tambi�n que mostramos por pantalla*/
		    	entrada.read(mensaje);     
		    	System.out.println("Servidor responde: " + new String(mensaje));   
		    	} 
		    /*Si el cliente escribe fin, se cierra los stream de entrada de salida, el
		     * socket cliente y se cierrra la comunicaci�n*/
		    entrada.close();    
		    salida.close();    
		    cliente.close();        
		    System.out.println("Comunicaci�n cerrada");       
		    } catch (UnknownHostException e) {   
		    	System.out.println("No se puede establecer comunicaci�n con el servidor");    
		    	System.out.println(e.getMessage());   
		    	} catch (IOException e) {   
		    		System.out.println("Error de E/S");    
		    		System.out.println(e.getMessage());  
		    		}   
	  } 
		 }  