/*Implementaci�n de la clase Servidor*/
import java.io.IOException;
import java.net.InetSocketAddress; 
import java.net.ServerSocket; 
import java.net.Socket;
 

 public class Servidor { 
	 
public static void main(String[] args) {  
	
	  
	 String ip = "localhost";
	 
	
	  //Si se quiere probar en una misma m�quina sustituir la ip por localhost	 
	  int puerto=2017;
	
	  System.out.println("APLICACI�N DE SERVIDOR MULTITAREA");   
	  System.out.println("----------------------------------");   
	  try {    
		  
		  /*Creamos el socket del servidor y le asignamos la ip,
		     *  y el puerto por el que va a comunicarse con los clientes*/
		  
		    ServerSocket servidor = new ServerSocket();    
		    InetSocketAddress direccion = new InetSocketAddress(ip,puerto);    
		    servidor.bind(direccion); 
	    
		    System.out.println("Servidor listo para aceptar solicitudes");    
            System.out.println("Direcci�n IP: " + direccion.getAddress());        
    
            /*El servidor se queda escuchando hasta que llega la petici�n de un cliente,
             * entonces crea un hilo para comunicarse con el cliente*/
            while (true) {     
    	               Socket enchufeAlCliente = servidor.accept();    
    	               System.out.println("Comunicaci�n entrante");     
    	               new HiloEscuchador(enchufeAlCliente);    
    	    }      
        } catch (IOException e) { 
    	
        			System.out.println(e.getMessage());  
          } 
    } 
 } 
    
