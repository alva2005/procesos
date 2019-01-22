package agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import encriptador.Cifrador;

/*
 * Clase que gestiona todo el procedimientos de la agenda. Adem�s,tiene los m�todos para crear y leer el fichero de
 * la agenda y los de encriptar y desencriptarla.
 * 
 */
public class Principal {
	static Scanner lector;
	static Cifrador cifrador; //Clase cifrador 
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		lector = new Scanner(System.in);
		Agenda agenda = null;		
		int opc = 0;
		File fichero = new File("agenda.dat");
		
		//Si el fichero agenda.dat existe se llama la funci�n leerFichero
		if (fichero.exists()) {
			
			agenda=leerFichero(agenda);
			
		}
		else {
			//Si el fichero no existe se crea un objeto agenda y un objeto cifrador
			agenda = new Agenda();
			cifrador = new Cifrador();
		    
		}
		
		//Menu de gesti�n de la agenda. Mientras la variable opc sea distinta de 5 se muestra el men�	 
		  
		while (opc != 5) {
			mostrarMenu(); //Mostrar men� de gesti�n de la agenda
			
			/*
			 * MODIFICADO de la clase original, para evitar errores si se introduce un caracter en vez de 
			 * un n�mero. 
			 * Se lee la entrada por teclado, se convierte a entero y si no hay problemas se asigna a la variable opci�n. 
			 * Si hay problemas salta una excepci�n, indicando el mensaje de que tiene que ser un n�mero y
			 * la variable opc se pone a cero.
			 * */
			
			String leer = lector.nextLine();
	        try{
			  	opc =Integer.parseInt(leer);
	        }
	        catch (Exception e)
	        {
	            System.out.println("Has entrado una letra. Introduce un n�mero entre 1 y 5");
	            System.out.println();
	            opc=0;
	            
	        }
			
	   //Opciones del men� de la agenda
		switch (opc) {
			case 1:
				nuevoContacto(agenda);
				break;
			case 2:
				borrarContacto(agenda);
				break;
			case 3:
				consultarContacto(agenda);
				break;
			case 4:
				listadoContactos(agenda);
				break;
			}
		System.out.println();
		}
		//Si opc=5 se termina el programa y se crea el fichero agenda.dat, con el contenido de la agenda encriptado
		crearFichero(agenda);
		System.out.println("Fichero creado");
		lector.close();
	}

	/*
	 * M�todo que muestra el men� de gesti�n de la agenda
	 * */
	public static void mostrarMenu() {
		System.out.println(" AGENDA TELEF�NICA");
		System.out.println("---------------------------------------");
		System.out.println("1. A�adir nuevo contacto");
		System.out.println("2. Borrar contacto");
		System.out.println("3. Consultar contacto");
		System.out.println("4. Listado de contactos");
		System.out.println("5. Terminar programa");
		System.out.println("---------------------------------------");
		System.out.println("�Qu� opci�n eliges?");
	}

	/*
	 * M�todo para a�adir nuevo contacto. Se introduce por teclado el nombre y el
	 * telefono. Se crea un contacto y se a�ade a la agenda. 
	 * */
	public static void nuevoContacto(Agenda agenda) {
		System.out.println("Nombre: ");
		
		String nombre = lector.nextLine();
		
		System.out.println("Tel�fono: ");
		String tlf = lector.nextLine();
		Contacto c = new Contacto(nombre.trim(),tlf.trim());
		agenda.getContactos().add(c);
			
		System.out.println("El contacto ha sido a�adido con �xito");
	}
	
	/*
	 * M�todo MODIFICADO de la clase Principal para borrar un contacto. Se introduce por teclado el nombre, se compara
	 * con los todos los nombres que existen en la agenda. Si encuentra una coincidencia pregunta si quiere que el contacto sea
	 * eliminado escribiendo S o N, puede haber m�s de una coincidencia. Seg�n la opci�n muestra un mensaje de que el contacto 
	 * se borrar� o no.
	 * Si no existe el nombre, se muestra un mensaje de contacto no encontrado
	 * */

	public static void borrarContacto(Agenda agenda) { 
		int i = 0;
		boolean encontrado=false;
		System.out.println("Nombre buscado: "); 
		String nombre = lector.nextLine(); //introduce el nombre del contacto a borrar por teclado
		String opcion="N";
		ArrayList<Contacto> contactos = agenda.getContactos();
		while (i < contactos.size()) { //recorremos la agenda
			
		   if(contactos.get(i).getNombre().equals(nombre.trim())) { //si hay coincidencias
			   //Se muestra un mensaje para confirmar que se quiere borrar el contacto
			   encontrado=true;
			  System.out.println( " �Quiere que el contacto "+ contactos.get(i).getNombre() + " - " +
					 contactos.get(i).getTelefono() + " SEA ELIMINADO?(S/N)");
			  opcion = lector.nextLine(); 
			  if(opcion.toUpperCase().equals("S")) { //si la opcion es S se borra y se muestra un mensaje indic�ndolo
				  System.out.println( " El contacto "+ agenda.getContactos().get(i).getNombre() + " - " +
							 contactos.get(i).getTelefono() + " SER� ELIMINADO");
				  contactos.remove(i);
				  i--;
				  
			  }
			  //si la opcion no es S se muestra un mensaje indicando que no se borrar�
			  else  System.out.println( " El contacto "+ agenda.getContactos().get(i).getNombre() + " - " +
					contactos.get(i).getTelefono() + " NO SER� ELIMINADO");
		   }
			i++;
		}
		if (!encontrado)  //si el contacto no se encuentra se muestra un mensaje indic�ndolo
			System.out.println("El contacto no ha sido encontrado");
	
		
	}
	
	/*
	 * M�todo MODIFICADO de la clase original para consultar un contacto. Se introduce por teclado 
	 * el nombre, se compara con los nombres que existen en la agenda y se muestra los contactos que coincidan.
	 * Si no existe el nombre, se muestra un mensaje de contacto no encontrado
	 * */


	public static void consultarContacto(Agenda agenda) {
		int i = 0;
		boolean encontrado=false; //variable para indicar si el contacto se ha encontrado o no
		System.out.println("Nombre buscado: ");
		String nombre = lector.nextLine();
		ArrayList<Contacto> contactos = agenda.getContactos();
		while (i <contactos.size() ) { //recorremos toda la agenda

			 if(contactos.get(i).getNombre().equals(nombre.trim())){ // si hay coincidencias se muestran
				System.out.println("Tel�fono de " + nombre + ": " + agenda.getContactos().get(i).getTelefono());
				encontrado=true; // se cambia la variable a true
			}
			i++;
		}
		
		if (!encontrado)  // si se termina y no hay coincidencias
			System.out.println("El contacto no ha sido encontrado");
	}

	/*
	 * M�todo MODIFICADO de la clase Principal que muestra todos los contactos de la agenda y da un mensaje si
	 * la lista est� vacia
	 */
	public static void listadoContactos(Agenda agenda) {
		if(agenda.getContactos().size()!=0)
		for (Contacto c : agenda.getContactos()) {
				System.out.println(c);
		}
		else System.out.println("No existe ning�n contacto");
		
	
	}
    /*
     * M�todo NUEVO para encriptar el objeto Agenda que se introduce como par�metro de entrada. Se crea un nuevo objeto agenda
     * que contendr� la agenda encriptada a la que se le pasar� la clave.
     * Recorremos toda la agenda a�adiendo a la agenda encriptada los contactos con el nombre y el tel�fono encriptados.
     * Para ello llamamos al m�todo de la clase cifrador encriptar y una vez terminada se devuelve la agenda encriptada 
     * como par�metro de salida.
     * 
     */
	public static Agenda encriptarAgenda(Agenda agenda) {
		Agenda agendaEncriptada = new Agenda();
		agendaEncriptada.setClave(cifrador.getClave());
				for (Contacto c : agenda.getContactos()) {
					agendaEncriptada.getContactos().add(new Contacto(cifrador.encriptar(c.getNombre()),cifrador.encriptar(c.getTelefono())));
			}
		
		return agendaEncriptada;
		
	}
	
	/*
     * M�todo NUEVO para desencriptar el objeto Agenda que se introduce como par�metro de entrada. Se crea un nuevo objeto agenda
     * que contendr� la agenda desencriptada. Le pasamos al objeto de la clase cifrador la clave de la agenda para poder
     * desencriptarla.
     * Recorremos toda la agenda a�adiendo a la agenda desencriptada los contactos con el nombre y el tel�fono desencriptados.
     * Para ello llamamos al m�todo de la clase Cifrador, desencriptar y una vez terminada se devuelve la agenda desencriptada 
     * como par�metro de salida.
     * 
     */
	public static Agenda desencriptarAgenda(Agenda agenda) { 
		Agenda agendaDesencriptada = new Agenda();
		cifrador = new Cifrador(agenda.getClave());
		String nombre="";
		String telefono="";
		ArrayList<Contacto> contactos = agenda.getContactos();
		for(int i=0;i< contactos.size();i++) {
		       nombre=cifrador.desencriptar(contactos.get(i).getNombre());
			   telefono =cifrador.desencriptar(contactos.get(i).getTelefono());
				agendaDesencriptada.getContactos().add(new Contacto(nombre,telefono));
			}
		agendaDesencriptada.setClave(cifrador.getClave());
		return agendaDesencriptada;
	}
	
	/*
	 * M�todo MODIFICADO para crear el fichero con la agenda. Antes de escribir el objeto en el archivo
	 * se encripta
	 */
	public static void crearFichero(Agenda agenda) throws IOException {

		FileOutputStream file = new FileOutputStream("agenda.dat");
		ObjectOutputStream buffer = new ObjectOutputStream(file);
		buffer.writeObject(encriptarAgenda(agenda));
		buffer.close();
		file.close();
	}

	/*
	 * Funci�n MODIFCADA para leer el fichero de la agenda. Despu�s de leer el objeto se desencripta y se 
	 * devuelve como par�metro de salida
	 */
	public static Agenda leerFichero(Agenda agenda) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("agenda.dat");
		ObjectInputStream buffer = new ObjectInputStream(file);

		agenda = desencriptarAgenda((Agenda) buffer.readObject());

		buffer.close();
		file.close();
		return agenda;
	}
}
