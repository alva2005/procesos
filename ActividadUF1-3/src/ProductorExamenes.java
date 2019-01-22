/*
 * Clase que implementa la interfaz Runnable. Crea hilos que se nombran con la letra E y 
 * el n�mero de examen y los lanza.Al lanzar cada uno, se obtiene el nombre del hilo al que se 
 * le a�ade el a�o y ese ser� el c�digo de examen. Se a�ade el c�digo a la cola, 
 * llamando al m�todo fabricarNuevoExamen de buffer y se muestra por pantalla el c�digo de examen
 * 
 */

import java.time.LocalDateTime;
public class ProductorExamenes implements Runnable {
              private BufferExamenes buffer;
              private static int numeroExamen = 0;
              private Thread hilo;
              public ProductorExamenes(BufferExamenes buffer) {
                           // Incrementa el contador de ex�menes (variable numeroExamen).
            	             numeroExamen++;
                           // Construye el hilo. El nombre ser� la letra E seguida
            	           // del valor de la variable numeroExamen.            	             
            	             hilo = new Thread(this,"E"+numeroExamen);
                          // Establece el valor de la propiedad buffer
            	             this.buffer=buffer;
                         // Inicia el hilo.
            	             hilo.start();
              }
              @Override
              public void run() {
                            int aa = LocalDateTime.now().getYear();
                            // Generaci�n del c�digo de examen.
                            String codigo = hilo.getName() + "-" +aa;
 
                           // A�ade el nuevo c�digo al buffer de ex�menes.
                              buffer.fabricarNuevoExamen(codigo);
                           // Muestra un mensaje en consola informando sobre el
                           // c�digo del examen que se acaba de producir.
                              System.out.println("Se ha producido el examen de c�digo:" + codigo);
                }
}

