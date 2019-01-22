/*Implementación del hilo creado por el Servidor*/
import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket; 

/*Al ser un hilo se implementa la interfaz Runnable*/
 public class HiloEscuchador implements Runnable {  
	 private Thread hilo;  
	 private static int numCliente = 0;  
	 private Socket enchufeAlCliente;   
	 
	 /*En el constructor se crea el objeto hilo con el nombre del nº del cliente 
	  * y se arranca.*/

	 public HiloEscuchador(Socket cliente) {   
		 numCliente++;   
		 hilo = new Thread(this, "Cliente"+numCliente);   
		 this.enchufeAlCliente = cliente;  
		
		 hilo.start();		 
	}   
	 
	@Override  
	public void run() {   
		System.out.println("Estableciendo comunicación con " + hilo.getName());   
		try {    
			
			InputStreamReader entrada = new InputStreamReader(enchufeAlCliente.getInputStream());
			PrintStream salida = new PrintStream(enchufeAlCliente.getOutputStream());
		    /*Al igual que en el cliente usamos un buffer para no limitar 
		     * el tamaño de los mensajes*/  
		    BufferedReader bf = new BufferedReader(entrada);
		  
			//Esperamos aqui a que el servidor envie el nombre del cliente
			String nombre = bf.readLine();
			
			//Una vez obtenido el nombre se cambia el nombre al hilo, como indica la práctica
			//aunque también se podía haber usado directamente la variable nombre 
			hilo.setName(nombre);
				
			System.out.println("Nombre: " + hilo.getName());  
			/*Mientras el cliente no ponga fin a la comunicación, escribiendo fin, 
			 * leemos lo que nos llega del mismo y lo mostramos por pantalla con su 
			 * nombre.*/  
			
			/* Cada vez que recibimos un mensaje calculamos su longitud y la enviamos como
			 * mensaje al cliente*/
		    String texto="";
			while (!texto.trim().toUpperCase().equals("FIN")) { 
				texto= bf.readLine();
				
				if (texto.trim().toUpperCase().equals("FIN")) {      
					salida.println("Hasta pronto, gracias por establecer conexión");   
					
					System.out.println(hilo.getName() +" ha cerrado la comunicación");    
				} else {     
					System.out.println(hilo.getName() + " dice: " + texto);  
					
					salida.println("Tu mensaje tiene " + texto.trim().length() + " caracteres");    
				}   
				
			}    
			/*Si el cliente escribe fin, se cierra la comunicación*/
			entrada.close();    
			salida.close();    
			enchufeAlCliente.close();   
			} catch (IOException e) {    
				System.out.println(e.getMessage());  
		} 
	 }
 }