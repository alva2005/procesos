/*
 * Clase que controla la cola que va a ser utilizada para gestionar los códigos de examen.
 * 
 */
import java.util.LinkedList;
import java.util.Queue;
public class BufferExamenes {
             private Queue<String> colaExamenes;
             public BufferExamenes() {
                           colaExamenes = new LinkedList<String>();
             }
 /*
  * El método facbricarNuevoExamen añade el código del examen a la cola y notifica 
  * a los hilos que estén esperando que puede actuar.
  * @param: String codigo del examen "E"+numeroExamen+"-"+ año
  */
             public synchronized void fabricarNuevoExamen(String codigo) {
                           // Aquí se fabrica un nuevo examen.
            	 
                           // Un hilo de la clase ProductorExamenes
                           // se encargará de fabricarlo
                           // y pasarlo como argumento a este método.
            	           // Añade el código pasado como argumento a la cola            	          
                           // y libera el hilo que está intentando consumir
                           // un nuevo examen.
            	 		   
            	 		   colaExamenes.add(codigo);
            	           notify();
            	                      }
   /*
    * El método consumirExamen hace que los hilos esperen si la cola está vacia, hasta que reciban
    * notify indicando que la cola está disponible y que pueden trabajar. Le hemos añadido un tiempo 
    * de salida al wait, por si no recibe la señal de notify con lo que volvería a comprobar si la cola 
    * está vacía.
    * Cuando la cola no está vacía, se obtiene un código de examen de la cola que pasa como
    * salida de la función 
    * @return String codigo de examen de la cola
    */
            public synchronized String consumirExamen() {
                          // Este método se encargará de suministrar un examen
                          // a cada hilo de tipo Examinador que lo solicite.
 
                         // Para suministrar el examen habrá antes que esperar
                         // hasta que haya algún examen para consumir en la cola.
            	         while(colaExamenes.isEmpty()) {
            	        	 try {
            	        		 wait(10);
            	        	 } catch(InterruptedException e) {
            	        		 System.out.println(e.getMessage());
            	        	 }
            	        	 
            	         }
 
                        // Haz aquí una pausa hasta que se haya fabricado algún examen.
 
                         // Si la cola sigue sin estar vacía, saca un examen y
                        // entrégalo como retorno de esta función.
            	         return (String) colaExamenes.remove();
              }
}
