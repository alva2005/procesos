/*Implementaci�n del hilo creado por el Servidor*/
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.net.Socket;
import java.util.TreeMap; 

/*Al ser un hilo se implementa la interfaz Runnable*/
public class HiloEscuchador implements Runnable {  
	
	 private Thread hilo;  
	 private static int numCliente = 0;  
	 private Socket enchufeAlCliente;  
	 private TreeMap <String, Producto> productos;
	
	 /*En el constructor se crea el objeto hilo con el nombre del n� del cliente 
	  * y se arranca. Asignamos tambi�n el treemap para poder obtener los productos*/
	 public HiloEscuchador(Socket cliente, TreeMap <String,Producto> productos) {   
		 numCliente++;   
		 hilo = new Thread(this, "Cliente"+numCliente);   
		 this.enchufeAlCliente = cliente;  
		 this.productos=productos;
		 hilo.start();		 
	}   
	 
	/*funci�n que busca el producto dentro del treemap seg�n la clave pasada
	 * como par�metro*/ 
	private Producto buscarProducto(String clave) {
		
		return productos.get(clave);
		
	}
	
	@Override  
	public void run() {   
		System.out.println("Estableciendo comunicaci�n con " + hilo.getName());   
		try {    
			InputStream entrada = enchufeAlCliente.getInputStream();    
			OutputStream salida = enchufeAlCliente.getOutputStream();    
			String texto = "";    
			/*Mientras el cliente no ponga fin a la comunicaci�n, escribiendo fin, 
			 * leemos los bytes que nos llegan del mismo.*/
			while (!texto.trim().toUpperCase().equals("FIN")) {     
				byte[] codigo = new byte[3];     
				entrada.read(codigo);     
				texto = new String(codigo);  
				/* Convertimos esos bytes a cadena y si no es fin, eliminamos los espacions en blanco y
				 * buscamos el producto en el treemap y enviamos al cliente el resultado como bytes.
				 * Si el producto no existe se manda un mensaje indicando que �ste no existe*/
				
	
				if (texto.trim().toUpperCase().equals("FIN")) {      
					salida.write("Hasta pronto, gracias por establecer conexi�n".getBytes());      
					System.out.println(hilo.getName()+" ha cerrado la comunicaci�n");    
				} else {     
					System.out.println(hilo.getName() + " consulta producto: " + texto); 
					Producto productoResultado = buscarProducto(texto.trim().toUpperCase());
					if(productoResultado!=null) 
					      salida.write(productoResultado.toString().getBytes());    
				     else salida.write("No existe ning�n producto con ese c�digo".getBytes());  
				}
			}    
			/*Si lo recibido es fin, se manda al cliente un mensaje de despedida y se cierra la comunicaci�n del
			 * socket y de los stream*/
			entrada.close();    
			salida.close();    
			enchufeAlCliente.close();   
			} catch (IOException e) {    
				System.out.println(e.getMessage());  
		} 
	 }
 }