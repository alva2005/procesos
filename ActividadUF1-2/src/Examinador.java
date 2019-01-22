/*
 * Clase que implementa la interfaz Runnable. Crea hilos con el nombre del alumno
 * y los lanza.Al lanzar cada uno, se obtiene un código de examen de la cola y se crean 10 preguntas.
 * Las respuestas se obtienen como un número aleatorio entre 1 y 5, que pasamos a letra
 * con un switch.
 * Por pantalla mostramos las 10 preguntas cada una con su número,el código de examen, 
 * el nombre del alumno ( que en realidad es el nombre del hilo ) y una de las opciones de respuesta
 * 
 */
public class Examinador implements Runnable {
              private Thread hilo;
              BufferExamenes buffer;
 
              public Thread getHilo() {
                            return hilo;
              }
              public Examinador(String alumno, BufferExamenes generador) {
                            // Construye el hilo. El nombre será el nombre del alumno.
                           // Establece el valor de la propiedad buffer
            	           hilo = new Thread(this,alumno);
            	           buffer=generador;
                           // Inicia el hilo.
            	           hilo.start();
              }
              @Override
              public synchronized void run() {
                            String codigoExamen = this.buffer.consumirExamen();
                            char letra;
                            if (codigoExamen != null) {
                                  // Simula aquí un examen de 10 preguntas
                                  // cuyas respuestas se seleccionarán al azar
                                 // entre A, B, C, D o – (sin contestar).
                            	for(int i=1;i<=10; i++) {
                            	 int respuesta = (int)(Math.random()*5 +1);
                            	 switch(respuesta) {
                            	 
                            	 case 1:letra='A'; break;
                            	 case 2:letra='B'; break;
                            	 case 3:letra='C'; break;
                            	 case 4:letra='D'; break;
                            	 default :letra='-'; break;
                            	 
                            	 
                            	 }
                            	
                            		System.out.println(codigoExamen+"; "+hilo.getName()+"; Pregunta "+ i + "; " + letra);
                            	}
                            	
                            }
                            else {
                                       System.out.println("Agotado tiempo de espera y " +
                                                        "no hay más exámenes");
                            }
              }
}
