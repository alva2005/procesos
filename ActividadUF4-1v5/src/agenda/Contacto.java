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
	
  
	//Constructor con par�metros 
	public Contacto(String nombre, String telefono) {
	
		this.nombre=nombre;
		this.telefono=telefono;
	}

	//M�todos geters
	public String getNombre() {
		
		return nombre;
	}

	public String getTelefono() {
		
		return telefono;
	}

	//Met�do toString
	@Override
	public String toString() {
		
		return "Contacto [Nombre: " + nombre + ", Tel�fono: " + telefono + "]";
	}
}
