/*
 * Clase Principal que llamamos para ejecutar el programa. En esta clase
 * creamos varios objetos productorExamenes, Examinador para generar y consumir exámenes
 * y uno BufferExamenes para controlar la cola
 */
public class Principal {
   public static void main(String[] args) throws InterruptedException {
                 BufferExamenes generador = new BufferExamenes();
                 new ProductorExamenes(generador);
                 new Examinador("Rosa", generador);
                 new ProductorExamenes(generador);
                 new Examinador("Miguel", generador);
                 new ProductorExamenes(generador);
                 new Examinador("Carlos", generador);
                 }
}
