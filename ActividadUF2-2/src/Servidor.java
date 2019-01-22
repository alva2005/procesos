/*Implementación de la clase Servidor*/
import java.io.IOException;
import java.net.InetSocketAddress; 
import java.net.ServerSocket; 
import java.net.Socket;
 

 public class Servidor { 
	 
public static void main(String[] args) {  
	
	  
	 String ip = "localhost";
	 
	
	  //Si se quiere probar en una misma máquina sustituir la ip por localhost	 
	  int puerto=2017;
	
	  System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");   
	  System.out.println("----------------------------------");   
	  try {    
		  
		  /*Creamos el socket del servidor y le asignamos la ip,
		     *  y el puerto por el que va a comunicarse con los clientes*/
		  
		    ServerSocket servidor = new ServerSocket();    
		    InetSocketAddress direccion = new InetSocketAddress(ip,puerto);    
		    servidor.bind(direccion); 
	    
		    System.out.println("Servidor listo para aceptar solicitudes");    
            System.out.println("Dirección IP: " + direccion.getAddress());        
    
            /*El servidor se queda escuchando hasta que llega la petición de un cliente,
             * entonces crea un hilo para comunicarse con el cliente*/
            while (true) {     
    	               Socket enchufeAlCliente = servidor.accept();    
    	               System.out.println("Comunicación entrante");     
    	               new HiloEscuchador(enchufeAlCliente);    
    	    }      
        } catch (IOException e) { 
    	
        			System.out.println(e.getMessage());  
          } 
    } 
 } 
    
