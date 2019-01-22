package servidor;

/**
 * Clase Constelaci�n donde se definen los atributos y m�todos de esta clase. Tiene 2 atributos nombre y observaciones.
 *  Los m�todos ser�n los getters y sobreescribimos el m�todo toString para adpatarlo a la salida
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