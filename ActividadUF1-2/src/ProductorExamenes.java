/*
 * Clase que implementa la interfaz Runnable. Crea hilos que se nombran con la letra E y 
 * el número de examen y los lanza.Al lanzar cada uno, se obtiene el nombre del hilo al que se 
 * le añade el año y ese será el código de examen. Se añade el código a la cola, 
 * llamando al método fabricarNuevoExamen de buffer y se muestra por pantalla el código de examen
 * 
 */

import java.time.LocalDateTime;
public class ProductorExamenes implements Runnable {
              private BufferExamenes buffer;
              private static int numeroExamen = 0;
              private Thread hilo;
              public ProductorExamenes(BufferExamenes buffer) {
                           // Incrementa el contador de exámenes (variable numeroExamen).
            	             numeroExamen++;
                           // Construye el hilo. El nombre será la letra E seguida
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
                            // Generación del código de examen.
                            String codigo = hilo.getName() + "-" +aa;
 
                           // Añade el nuevo código al buffer de exámenes.
                              buffer.fabricarNuevoExamen(codigo);
                           // Muestra un mensaje en consola informando sobre el
                           // código del examen que se acaba de producir.
                              System.out.println("Se ha producido el examen de código:" + codigo);
                }
}

