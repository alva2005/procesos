package DES;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cifrador {
	public static void main (String args[]) {
		System.out.println("Probando sistema de encriptación con algoritmo DES");
		try {
			KeyGenerator generador = KeyGenerator.getInstance("DES");
			System.out.println("Paso 1: Se ha obtenido el generador de claves");
			
			SecretKey clave = generador.generateKey();
			System.out.println("Paso 2: Se ha obtenido la clave");

			Cipher descifrador = Cipher.getInstance("DES");
			System.out.println("Paso 3: Hemos obtenido el descifrador");
			
			descifrador.init(Cipher.ENCRYPT_MODE, clave);
			System.out.println("Paso 4: Hemos configurado el descifrador");
			
			descifrador.init(Cipher.ENCRYPT_MODE, clave);
			System.out.println("Paso 4: Hemos configurado el descifrador");
			
			String mensajeOriginal = "La cripta mágica";
			byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
			byte[] bytesMensajeCifrado = descifrador.doFinal(bytesMensajeOriginal);
			String mensajeCifrado = new String(bytesMensajeCifrado);
			System.out.println("Mensaje Original: " + mensajeOriginal);
			System.out.println("Mensaje Cifrado: " + mensajeCifrado);
			
			System.out.println("Desciframos el mensaje cifrado para comprobar que comprueba con el original");
			descifrador.init(Cipher.DECRYPT_MODE, clave);
			byte[] bytesMensajeDescifrado = descifrador.doFinal(bytesMensajeCifrado);
			System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			System.out.println("Error al crear y configurar el descifrador");
			System.out.println(e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error al cifrar el mensaje");
			System.out.println(e.getMessage());
		}
	}
}