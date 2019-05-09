/**
 * 
 */
package ejercicios;

/**
 * A. Crear una clase Alumnno con los siguientes campos
 * (con sus respectivos getters, setters y constructor)
 * 
 * Persona
 * Legajo - Integer
 * 
 *  
 * @author examen
 *
 */
public class Ejercicio3 {

	/**
	 * 
	 */
	public Ejercicio3() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Alumno alumno=new Alumno(
				Persona.TipoDocumento.dni.toString(),
	         	38979109,
	         	"Esteban",
	         	"Moine",
				"30-09-1995",
				1);
		
		alumno.imprimirValores();
		System.out.println("Legajo: "+alumno.getLegajo());
	}

}
