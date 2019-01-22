package encriptador;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*
 * La Clase Cifrador se encarga de implementar los métodos para cifrar y descifrar la agenda
 * con el algoritmo DES
 * */
  public class Cifrador{
	
	
	private KeyGenerator generador;
	private Key clave;
	private Cipher descifrador;
	static final String ALGORITMO="DES";
	
	/*Se sobrecarga el constructor y se crea uno sin parámetros para generar una clave cuando se crea 
	 * una nueva agenda con el algoritmo DES
	 */
	
	public Cifrador() {
		
		try { //generamos la clave y obtenemos la instancia para el cifrador/descifrador
		
			generador=KeyGenerator.getInstance(ALGORITMO);
			clave = generador.generateKey();
			 descifrador = Cipher.getInstance(ALGORITMO);
			
		} catch (GeneralSecurityException e) {
			System.out.println("Error al cifrar o descifrar el mensaje");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
		}
		
	}
	
	/*
	 * El constructor con parámetros se usa cuando se lee la agenda desde un archivo. Se lee la clave desde
	 * la agenda como array de bytes y se convierte en clave para poderla usar para desencriptar la misma.
	 */
	public Cifrador(byte[] claveAgenda) {
		
		
		try {//obtenemos la clave y obtenemos la instancia para el cifrador/descifrador
			clave = new SecretKeySpec(claveAgenda, ALGORITMO);
			descifrador = Cipher.getInstance(ALGORITMO);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.out.println("Error al cifrar o descifrar el mensaje");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
		}
		
	}
	
	//Metodo get
    public byte[] getClave() {
		return clave.getEncoded();
    	
    }
   	
    /*
     * Función que recibe una cadena con un texto y devuelve el texto encriptado
     * Utilizamos el descifrador en modo encriptar y pasamos el mensaje a un array de bytes para poder encriptarlo.
     * Una vez realizado el proceso, se codifica en Base64 para evitar carácteres que puedan producir errores y
     *  vuelve a convertirse en una cadena, pero esta vez ya encriptada.
     */
	public String encriptar(String mensajeOriginal) {
		String mensajeCifrado = null;
		
		try {			
			
			descifrador.init(Cipher.ENCRYPT_MODE, clave);
			byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
			byte[] bytesMensajeCifrado = descifrador.doFinal(bytesMensajeOriginal);
			mensajeCifrado = new String(Base64.getEncoder().encode(bytesMensajeCifrado));
			 
		} catch (GeneralSecurityException e) {
			System.out.println("Error al cifrar el mensaje");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
				
			}
		return mensajeCifrado;
		
	}
	
	/*
     * Función que recibe una cadena con un texto y devuelve el texto desencriptado
     * Utilizamos en descifrador en modo desencriptar y pasamos el mensaje a un array de bytes para poder desencriptarlo.
     * Primero se decodifica en Base64 y luego se desencripta. Por último, vuelve a convertirse en una cadena, 
     * pero esta vez desencriptada. 
     * 
     */
	
	public String desencriptar(String mensajeCifrado) {
		
		byte[] bytesMensajeDescifrado = null;
		try {
			
			descifrador.init(Cipher.DECRYPT_MODE, clave);
			bytesMensajeDescifrado = descifrador.doFinal(Base64.getDecoder().decode(mensajeCifrado.getBytes()));
				
			
		} catch (GeneralSecurityException e) {
				System.out.println("Error al descifrar el mensaje");
				System.out.println("Excepción de tipo: " + e.getClass().getName());
				System.out.println(e.getMessage());
			}
		
		return new String(bytesMensajeDescifrado);
		 
			
		
	}


	
	
}