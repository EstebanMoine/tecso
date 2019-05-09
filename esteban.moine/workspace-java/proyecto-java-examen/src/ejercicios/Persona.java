package ejercicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona {
	
	enum TipoDocumento{
		dni,
		libretacivica
	}
	
	String tipoDoc;
	
	int NroDocumento;
	
	String Nombre,Apellido;
	
	Date FechaNacimiento;

    public Persona(String tipoDoc,int NroDocumento,String Nombre,String Apellido,String FechaNacimiento) {
        this.tipoDoc=tipoDoc;
    	this.NroDocumento=NroDocumento;
        this.Nombre = Nombre;
        this.Apellido=Apellido;
        this.FechaNacimiento=StringToDate(FechaNacimiento);
    }

	public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNroDocumento() {
        return NroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        NroDocumento = nroDocumento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }
    
    public static Date StringToDate(String fecha){
		
	    Date result = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
			result  = (Date) dateFormat.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        	
	    return result ;
	}
    
    void imprimirValores() {
		// TODO Auto-generated method stub
		System.out.println("Tipo doc: "+this.tipoDoc);
		System.out.println("Nro doc: "+this.NroDocumento);
		System.out.println("Nombre: "+this.Nombre);
		System.out.println("Apellido: "+this.Apellido);
		System.out.println("Fecha nacimiento: "+this.FechaNacimiento);
	}
	
}
