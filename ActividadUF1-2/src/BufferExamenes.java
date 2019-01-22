/*
 * Clase que controla la cola que va a ser utilizada para gestionar los c�digos de examen.
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
  * El m�todo facbricarNuevoExamen a�ade el c�digo del examen a la cola y notifica 
  * a los hilos que est�n esperando que puede actuar.
  * @param: String codigo del examen "E"+numeroExamen+"-"+ a�o
  */
             public synchronized void fabricarNuevoExamen(String codigo) {
                           // Aqu� se fabrica un nuevo examen.
            	 
                           // Un hilo de la clase ProductorExamenes
                           // se encargar� de fabricarlo
                           // y pasarlo como argumento a este m�todo.
            	           // A�ade el c�digo pasado como argumento a la cola            	          
                           // y libera el hilo que est� intentando consumir
                           // un nuevo examen.
            	 		   
            	 		   colaExamenes.add(codigo);
            	           notify();
            	                      }
   /*
    * El m�todo consumirExamen hace que los hilos esperen si la cola est� vacia, hasta que reciban
    * notify indicando que la cola est� disponible y que pueden trabajar. Le hemos a�adido un tiempo 
    * de salida al wait, por si no recibe la se�al de notify con lo que volver�a a comprobar si la cola 
    * est� vac�a.
    * Cuando la cola no est� vac�a, se obtiene un c�digo de examen de la cola que pasa como
    * salida de la funci�n 
    * @return String codigo de examen de la cola
    */
            public synchronized String consumirExamen() {
                          // Este m�todo se encargar� de suministrar un examen
                          // a cada hilo de tipo Examinador que lo solicite.
 
                         // Para suministrar el examen habr� antes que esperar
                         // hasta que haya alg�n examen para consumir en la cola.
            	         while(colaExamenes.isEmpty()) {
            	        	 try {
            	        		 wait(10);
            	        	 } catch(InterruptedException e) {
            	        		 System.out.println(e.getMessage());
            	        	 }
            	        	 
            	         }
 
                        // Haz aqu� una pausa hasta que se haya fabricado alg�n examen.
 
                         // Si la cola sigue sin estar vac�a, saca un examen y
                        // entr�galo como retorno de esta funci�n.
            	         return (String) colaExamenes.remove();
              }
}
