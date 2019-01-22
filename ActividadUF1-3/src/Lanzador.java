/**
 * Segunda parte del ej 3. 
 * Este programa lanza 2 procesos. Se ejecuta la clase Principal con 3 usuarios cada uno.
 * Se redirige la salida de cada uno de los procesos a examen1.txt y a examen2.txt. 
 * También se redirigen los errores a 2 archivos.
 * 
 * Para ejecutar programa, nos vamos al directorio bin del proyecto y con la consola
 * ejecutamos java Lanzador.
 * Podemos ver que en el mismo directorio se crean los archivos y si se abren obtenemos
 * que en cada uno se han producido 3 exámenes y sus respuestas.
 * 
 * 
 */
import java.io.File;
import java.io.IOException;


public class Lanzador {

	public static void main(String[] args) {	
		 
      	    ProcessBuilder generadorExamen1,generadorExamen2;
			    
			    
	            
				generadorExamen1 = new ProcessBuilder("java","Principal","Pepe","Juan","Luis");
				generadorExamen2 = new ProcessBuilder("java","Principal","Rosa","Miguel","Pedro");
			    		
			  	generadorExamen1.redirectOutput(new File("examen1.txt"));
				generadorExamen2.redirectOutput(new File("examen2.txt"));
				
				generadorExamen1.redirectError(new File("examen1Error.txt"));
				generadorExamen2.redirectError(new File("examen2Error.txt"));	
				
				
		        try {
		                	
		        	generadorExamen1.start();
		        	generadorExamen2.start();
		        	
					  System.out.println("Los procesos han sido lanzados con éxito");
					  System.out.println("Examina los archivos de salida");
				} catch (IOException  e) {
					e.printStackTrace();
				}
		 	
		}
}
 