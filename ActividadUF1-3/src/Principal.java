/*
 * Clase Principal que llamamos para ejecutar el primer apartado de la actividad 3. En esta clase
 * creamos un objeto BufferExamenes para controlar la cola.También creamos tantos objetos 
 * productorExamenes y Examinador para generar y consumir exámenes como parametros introduzcamos por la 
 * línea de comandos.
 * 
 *Si ejecutamos el programa en Eclipse hay que añadirle los parámetros desde Run As-> Run Configuration->
 *Arguments o ejecutarlo desde la consola en el directorio bin por ejemplo:
 * java Principal "Miguel" "Carmen" "Luis" Rosa"
 */
public class Principal {
   public static void main(String[] args) throws InterruptedException {
	            BufferExamenes generador = new BufferExamenes();
	            for( int i=0;i<args.length;i++) {
	            	new ProductorExamenes(generador);
	            	new Examinador(args[i], generador);
	            	
	            }
   }
}