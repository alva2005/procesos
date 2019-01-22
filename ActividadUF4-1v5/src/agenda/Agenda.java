package agenda;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * La clase Agenda implementa la interfaz Serializable para poder escribir los objetos de este tipo en un archivo.
 * Está formada por un ArrayList de objetos de la clase Contacto y por la clave, en un array de bytes,
 * que se usa para cifrar la agenda, necesaria para desencriptarla cuando se lee la misma desde un archivo.
 */

public class Agenda implements Serializable {
	private static final long serialVersionUID = 1324774912528254307L;
	private ArrayList<Contacto> contactos = new ArrayList<Contacto>();
	private byte[] clave;
	
   //Constructor
	public Agenda() {
		this.contactos = new ArrayList<Contacto>();
		clave=null;
	}

   //Métodos getters y setters
	public byte[] getClave() {
		return clave;
	}


	public void setClave(byte[] clave) {
		this.clave = clave;
	}


	public ArrayList<Contacto> getContactos() {
		return contactos;
	}
	
}
