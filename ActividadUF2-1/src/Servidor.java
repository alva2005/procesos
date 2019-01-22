
import java.io.IOException; 
import java.net.InetSocketAddress; 
import java.net.ServerSocket; 
import java.net.Socket;
import java.util.TreeMap; 
/*Implementación de la clase Servidor*/

 public class Servidor { 
	
	 public static void main(String[] args) {  
		 String ip ="192.168.1.35";
	  //int puerto=2000;
		 int puerto=2017;
	  /* TreeMap de Productos con una clave tipo cadena, que utilizaremos para buscar el
	   * producto que nos pida el cliente*/
	  TreeMap <String, Producto>  productos = new TreeMap <String, Producto> () ;
		 productos.put("PL",new Producto("Peras limoneras", 14, 5f));
		 productos.put("PC",new Producto("Peras conferencia", 12, 7f));
		 productos.put("PN",new Producto("Plátano canario", 5, 2.5f));
		 productos.put("BN",new Producto("Bananas", 7, 1.3f));
		 productos.put("TP",new Producto("Tomates tipo pera", 8, 1.7f));
		 productos.put("TR",new Producto("Tomates Raf", 7, 5.3f));
		 productos.put("UN",new Producto("Uvas negras", 8, 3.2f));
		 productos.put("UB",new Producto("Uvas blancas", 5, 2.7f));
		 productos.put("PT",new Producto("Picotas", 8, 4.3f));
		 productos.put("CR",new Producto("Ciruelas rojas", 10, 2.8f));
		 productos.put("MR",new Producto("Melocotones rojos", 3, 2.5f));
		 productos.put("MA",new Producto("Melocotones amarillos", 4, 3.2f));
	  
	  
	  System.out.println("SERVIDOR DE CONSULTA DE ARTÍCULOS");   
	  System.out.println("----------------------------------");   
	 
	  try {  
		    /*Creamos el socket del servidor y le asignamos la ip, en este caso localhost,
		     *  y el puerto por el que va a comunicarse con los clientes*/
		    ServerSocket servidor = new ServerSocket();    
		    InetSocketAddress direccion = new InetSocketAddress(ip,puerto);    
		    servidor.bind(direccion); 
	    
		    System.out.println("Servidor listo para aceptar artículos");    
            System.out.println("Dirección IP: " + direccion.getAddress());  
            
            /*El servidor se queda escuchando hasta que llega la petición de un cliente,
             * entonces crea un hilo para conectarse con el cliente y le pasa como parámetro
             * el TreeMap que vamos a usar como base de datos para guardar los productos*/
            while (true) {     
    	               Socket enchufeAlCliente = servidor.accept();     
    	               System.out.println("Comunicación entrante");     
    	               new HiloEscuchador(enchufeAlCliente,productos);    
    	    }      
        } catch (IOException e) { 
    	
        			System.out.println(e.getMessage());  
          } 
    } 
 } 
    
