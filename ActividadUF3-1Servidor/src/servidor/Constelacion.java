package servidor;

/**
 * Clase Constelación donde se definen los atributos y métodos de esta clase. Tiene 2 atributos nombre y observaciones.
 *  Los métodos serán los getters y sobreescribimos el método toString para adpatarlo a la salida
 */

public class Constelacion {
	
	private String nombre;
	private String observaciones;
	
	
	public Constelacion(String nombre, String observaciones) {
		this.nombre = nombre;
		this.observaciones = observaciones;
	}
	
	/**
	 * Getter del atributo nombre	 
	 * @return nombre de la constelacion
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Getter del atributo observaciones	 
	 * @return observaciones de la constelacion
	 */
	public String getObservaciones() {
		return observaciones;
	}
	

	@Override
	public String toString() {
		return nombre + ": " + observaciones;
	}
}