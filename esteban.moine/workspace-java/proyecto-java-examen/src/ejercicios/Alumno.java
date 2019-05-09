package ejercicios;

public class Alumno extends Persona {
	
	
	int Legajo;
	
    public Alumno(String tipoDoc,int NroDocumento,String Nombre,String Apellido,String FechaNacimiento,int Legajo){
        super(tipoDoc,NroDocumento,Nombre,Apellido,FechaNacimiento);
        this.Legajo = Legajo;
    }

	public int getLegajo() {
        return Legajo;
    }

    public void setLegajo(int legajo) {
    	this.Legajo = legajo;
    }
    
}
