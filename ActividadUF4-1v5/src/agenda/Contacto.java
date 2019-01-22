package agenda;



import java.io.Serializable;

/*
 * Clase Contacto que implementa la interfaz serializable para poder insertar los contactos en 
 * un archivo.
 */

public class Contacto implements Serializable{
	
	
	private static final long serialVersionUID = -482120416608362415L;
	private String nombre; //atributo nombre
	private String telefono; //atributo telefono
	
  
	//Constructor con parámetros 
	public Contacto(String nombre, String telefono) {
	
		this.nombre=nombre;
		this.telefono=telefono;
	}

	//Métodos geters
	public String getNombre() {
		
		return nombre;
	}

	public String getTelefono() {
		
		return telefono;
	}

	//Metódo toString
	@Override
	public String toString() {
		
		return "Contacto [Nombre: " + nombre + ", Teléfono: " + telefono + "]";
	}
}
