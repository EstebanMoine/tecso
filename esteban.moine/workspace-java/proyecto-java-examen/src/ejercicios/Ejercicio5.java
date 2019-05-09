package ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Ejercicio5 {

 	static Connection conn;
 	static BufferedReader reader;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		reader = new BufferedReader(new InputStreamReader(System.in));

		ConexionMySQL SQL = new ConexionMySQL();
		
		conn = SQL.conectarMySQL();
		
		crearTablas();
		
		mostrarMenu();
	}
	
	
	private static void mostrarMenu() {

		System.out.println("--- MENU ---");
		System.out.println("Seleccione una accion");
		System.out.println("1 Registrar alumno");
		System.out.println("2 Editar alumno");
		System.out.println("3 Ver estado academico del alumno");
		System.out.println("4 Ver alumnos y docente de un curso");
		System.out.println("5 Inscribir a alumno a una materia");
		System.out.println("Escribe aqui la opcion: ");
		
		try {
			String opcion = reader.readLine();
			
			switch(opcion) {
			  case "1":
			      logicaPersona();
			      break;
			  case "2":
			      editarAlumno();
			      break;
			  case "3":
				  verEstadoAcademicoAlumno();
				  break;
			  case "4":
				  verAlumnosYDocentesCurso();
				  break;
			  case "5":
				  inscribirAlumnoACarrera();
				  break;
			  default:
				  System.out.println("Elije una opcion valida");
				  mostrarMenu();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void verAlumnosYDocentesCurso() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM curso";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
		      
		    ResultSet rs = preparedStatement.executeQuery(query);
		      
		    while (rs.next()){
		    	System.out.println("ID: "+rs.getInt("identificador")+" Nombre: "+rs.getString("nombre"));
		    }
		    preparedStatement.close();
		    
			System.out.println("Ingresa el ID del curso seleccionado");
			try {
				String idcurso=reader.readLine();
				
				String query2 = "SELECT idalumno FROM inscripciones_curso WHERE idcurso="+idcurso;
				
				PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
				      
				ResultSet rs2 = preparedStatement2.executeQuery(query2);

				System.out.println("Alumnos inscrptos");  
				
				while (rs2.next()){
					
					String query3 = "SELECT persona.nombre,persona.apellido FROM alumno INNER JOIN persona ON alumno.idpersona=persona.identificador WHERE alumno.identificador="+rs2.getInt("idalumno");
					
					PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
					      
					ResultSet rs3 = preparedStatement3.executeQuery(query3);
					      
					while (rs3.next()){
					   	System.out.println("Nombre: "+rs3.getString("nombre")+" "+rs3.getString("apellido"));
					}				
					
				}
				preparedStatement2.close();
				  
				
				
				String query4 = "SELECT docente FROM curso WHERE identificador="+idcurso;
				
				PreparedStatement preparedStatement4 = conn.prepareStatement(query4);
				      
				ResultSet rs4 = preparedStatement4.executeQuery(query4);

				System.out.println("Docente del curso");  
				
				while (rs4.next()){
					System.out.println("Nombre: "+rs4.getString("docente"));  		
				}
				preparedStatement2.close();
				
				mostrarMenu();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		} catch (SQLException e) {

            System.out.println(e.getMessage());

        }
	}


	private static void inscribirAlumnoACarrera() {
		// TODO Auto-generated method stub
		System.out.println("Ingrese el Nro de Legajo del alumno");
		
		try {
			String legajo = reader.readLine();
		
			String query = "SELECT * FROM alumno WHERE legajo="+Integer.parseInt(legajo);
		
			PreparedStatement preparedStatement;
		
			preparedStatement = conn.prepareStatement(query);
		
			ResultSet rs = preparedStatement.executeQuery(query);
			  
			if(rs.next()) {
				int idAlumno = rs.getInt("identificador");
				
				System.out.println("Carreras");
				String query2 = "SELECT * FROM carrera";
			
				PreparedStatement preparedStatement2;
			
				preparedStatement2 = conn.prepareStatement(query2);
			
				ResultSet rs2 = preparedStatement2.executeQuery(query2);
				
				while(rs2.next()) {
					System.out.println("ID: "+rs2.getInt("identificador")+" Nombre: "+rs2.getString("nombre"));
			    }
			    preparedStatement2.close();
			    
				System.out.println("Ingresa el ID de la carrera seleccionada");
				
				String idCarrera=reader.readLine();
				
				System.out.println("Cursos de la carrera seleccionada");
				
				String query3 = "SELECT * FROM curso WHERE idcarrera="+idCarrera;
			
				PreparedStatement preparedStatement3;
			
				preparedStatement3 = conn.prepareStatement(query3);
			
				ResultSet rs3 = preparedStatement3.executeQuery(query3);
				
				while(rs3.next()) {
					System.out.println("ID: "+rs3.getInt("identificador")+" Nombre: "+rs3.getString("nombre"));
			    }
			    preparedStatement3.close();
			    
				System.out.println("Ingresa los IDS de los cursos separados por comas");
				
				String idCursos=reader.readLine();
				
				String[] ids = idCursos.split(",");
				int size=idCursos.split(",").length;
				
				java.util.Date date = new java.util.Date();
				String fecha= new SimpleDateFormat("yyyy-MM-dd").format(date);
				
				for(int i=0;i<size;i++) {
					String query4 = "INSERT INTO inscripciones_curso (idalumno,idcurso,fechainscripcion) VALUES ("+idAlumno+","+ids[i]+",'"+fecha+"')";
					System.out.println(query4);
					PreparedStatement preparedStatement4;
					preparedStatement4 = conn.prepareStatement(query4);
					preparedStatement4.executeUpdate(query4);
				}
				
				String query5 = "INSERT INTO inscripciones_carrera (idalumno,idcarrera,fechainscripcion) VALUES ("+idAlumno+","+idCarrera+",'"+fecha+"')";
				PreparedStatement preparedStatement5;
				preparedStatement5 = conn.prepareStatement(query5);
				preparedStatement5.executeUpdate(query5);
				
				System.out.println("Se agrego al alumno a la carrera y los cursos");
				mostrarMenu();
				
			}else {
				System.out.println("El alumno no existe");
				mostrarMenu();
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void verEstadoAcademicoAlumno() {
		// TODO Auto-generated method stub
		System.out.println("Ingrese el Nro de Legajo del alumno");
		
		try {
			String legajo = reader.readLine();
		
			String query = "SELECT * FROM alumno WHERE legajo="+Integer.parseInt(legajo);
		
			PreparedStatement preparedStatement;
		
			preparedStatement = conn.prepareStatement(query);
		
			ResultSet rs = preparedStatement.executeQuery(query);
			  
			while(rs.next()) {
				int idAlumno = rs.getInt("identificador");
		        
				System.out.println("Carreras del almuno");
				String query2 = "SELECT carrera.nombre FROM inscripciones_carrera INNER JOIN carrera ON inscripciones_carrera.idcarrera=carrera.identificador WHERE inscripciones_carrera.idalumno="+idAlumno;
	
				PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
			      
			    ResultSet rs2 = preparedStatement2.executeQuery(query2);
			      
			    while (rs2.next()){
			        System.out.println("Nombre: "+rs2.getString("nombre"));
			    }
			    preparedStatement.close();
			    
			    
			    System.out.println("Cursos del almuno");
				String query3 = "SELECT curso.nombre FROM inscripciones_curso INNER JOIN curso ON inscripciones_curso.idcurso=curso.identificador WHERE idalumno="+idAlumno;
	
				PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
			      
			    ResultSet rs3 = preparedStatement3.executeQuery(query3);
			      
			    while (rs3.next()){
			        System.out.println("Nombre: "+rs3.getString("nombre"));
			    }
			    preparedStatement.close();
			    
			    
			    System.out.println("Promedio del alumno");
				String query4 = "SELECT carrera.identificador,carrera.nombre FROM inscripciones_carrera INNER JOIN carrera ON inscripciones_carrera.idcarrera=carrera.identificador WHERE idalumno="+idAlumno;
	
				PreparedStatement preparedStatement4 = conn.prepareStatement(query4);
			      
			    ResultSet rs4 = preparedStatement4.executeQuery(query4);
			      
			    int notaTotal=0;
			    int cant=0;
			    while (rs4.next()){
					String query5 = "SELECT inscripciones_curso.nota FROM inscripciones_curso INNER JOIN curso ON inscripciones_curso.idcurso=curso.identificador WHERE inscripciones_curso.idalumno="+idAlumno+" AND curso.idcarrera="+rs4.getInt("identificador")+" AND inscripciones_curso.nota>6";
					
					PreparedStatement preparedStatement5 = conn.prepareStatement(query5);
				      
				    ResultSet rs5 = preparedStatement5.executeQuery(query5);

				    while (rs5.next()){
				    	notaTotal=notaTotal+rs5.getInt("nota");
				    	cant++;
				    }
				    if(cant!=0) {
				    	System.out.println("Promedio: "+notaTotal/cant);
				    }
			    }
			    preparedStatement.close();
			    mostrarMenu();
			}
			
			/*if(!rs.next()) {
				// alumno no existe
			    System.out.println("La persona no existe");
			    mostrarMenu();
			}else {
				
		        
				
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void editarAlumno() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Ingrese el nro de documento");
		String documento = reader.readLine();
		String query = "SELECT * FROM persona WHERE documento="+Integer.parseInt(documento);
		
		try {

			PreparedStatement preparedStatement = conn.prepareStatement(query);
		      
		      ResultSet rs = preparedStatement.executeQuery(query);
		      
		      if(!rs.next()) {
		    	  // la persona no existe
		  		System.out.println("La persona no existe");
		  		mostrarMenu();

		      }else {
		    	  
		    		  System.out.println("Ingrese los nuevos datos de la Persona");
		    			
		    		  System.out.println("Nombre:");
	    				String nombre = reader.readLine();
	    				
	    				System.out.println("Apellido:");
	    				String apellido = reader.readLine();
	    				
	    				System.out.println("Fecha de nacimiento: yyyy-MM-dd");
	    				String fechanac = reader.readLine();
	    				
	    				System.out.println("Direccion:");
	    				String direccion = reader.readLine();
	    				
	    				String SQL =  "UPDATE persona SET documento="+documento+",nombre='"+nombre+"',apellido='"+apellido+"',fechanac='"+fechanac+"',direccion='"+direccion+"' WHERE documento="+documento;
	    				
	    		        PreparedStatement preparedStatement2 = conn.prepareStatement(SQL);

	    		        preparedStatement2.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
	    		        
	    		        ResultSet rs2 = preparedStatement2.getGeneratedKeys();
	    		        rs2.next();
	    		        int idPersonaModificada = rs.getInt(1);
	    		        
	    		        System.out.println("Persona modificada");
	    		        
	    		        System.out.println("Ingrese los nuevos datos del Alumno");
		    				
	    		        System.out.println("Legajo:");
	    				String legajo = reader.readLine();
	    				
	    				String SQL2 =  "UPDATE alumno SET legajo="+legajo+" WHERE identificador="+idPersonaModificada;
	    				
	    		        PreparedStatement preparedStatement3 = conn.prepareStatement(SQL2);

	    		        preparedStatement3.executeUpdate(SQL2, Statement.RETURN_GENERATED_KEYS);

	    		        System.out.println("Alumno modificado");
	    				
	    		        mostrarMenu();
		    		            
			      
		      }
		      
		      preparedStatement.close();
		} catch (SQLException e) {

            System.out.println(e.getMessage());
			mostrarMenu();

        }
	}


	/**
	 * @throws IOException
	 */
	private static void logicaPersona() throws IOException {
		System.out.println("Ingrese el nro de documento");
		String documento = reader.readLine();
		String query = "SELECT * FROM persona WHERE documento="+Integer.parseInt(documento);
		
		try {

			PreparedStatement preparedStatement = conn.prepareStatement(query);
		      
		      ResultSet rs = preparedStatement.executeQuery(query);
		      
		      if(!rs.next()) {
		    	  // la persona no existe
		    	  int idPersona = registrarPersona();
					if(idPersona!=0) {
						registrarAlumno(idPersona);
					}
		      }else {
		    	 
			    	  String query2 = "SELECT * FROM alumno WHERE idpersona="+rs.getInt("identificador");
			    	  PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
				      
				      ResultSet rs2 = preparedStatement2.executeQuery(query2);
				      
				      if(!rs2.next()) {
				    	  // el alumno no existe
				    	  registrarAlumno(rs.getInt("identificador")); 
				      }else {
				  		System.out.println("El alumno ya esta creado");
				  		mostrarMenu();
				      }
		    	  
		    	 
		      }
		      
		      preparedStatement.close();
		} catch (SQLException e) {

            System.out.println(e.getMessage());
			mostrarMenu();

        }
		
		
		
	}
	
	private static void listarPersona() {
		String query = "SELECT * FROM persona";
		try {

			PreparedStatement preparedStatement = conn.prepareStatement(query);
		      
		      ResultSet rs = preparedStatement.executeQuery(query);
		      
		      while (rs.next())
		      {
		        System.out.println("ID: "+rs.getInt("identificador")+" Nombre: "+rs.getString("nombre")+" "+rs.getString("apellido"));
		      }
		      preparedStatement.close();
		} catch (SQLException e) {

            System.out.println(e.getMessage());

        }
	}
	
	private static void registrarAlumno(int idPersona) {
		System.out.println("Crear el Alumno");
		
		try {
			//INSERT INTO alumno (identificador,idpersona,legajo) VALUES (10,6,12345)
			System.out.println("Legajo:");
			String legajo = reader.readLine();
			
			String SQL =  "INSERT INTO alumno (idpersona,legajo)"
					+ " VALUES ("+idPersona+","+legajo+")";
			
	        PreparedStatement preparedStatement = conn.prepareStatement(SQL);

	        preparedStatement.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);

	        System.out.println("Alumno creado");
			
	        mostrarMenu();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear la persona");
			mostrarMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear la persona");
			mostrarMenu();
		}
	}
	
	private static int registrarPersona() {
		System.out.println("Crear la Persona");
		
		try {
			//INSERT INTO persona (tipodoc,documento,nombre,apellido,fechanac) VALUES ('DNI', 38979109, 'Esteban', 'Moine', '1995-09-30')
			System.out.println("Documento:");
			String documento = reader.readLine();
			
			System.out.println("Nombre:");
			String nombre = reader.readLine();
			
			System.out.println("Apellido:");
			String apellido = reader.readLine();
			
			System.out.println("Fecha de nacimiento: yyyy-MM-dd");
			String fechanac = reader.readLine();
			
			System.out.println("Direccion:");
			String direccion = reader.readLine();
			
			String SQL =  "INSERT INTO persona (tipodoc,documento,nombre,apellido,fechanac,direccion) "
					+ "VALUES ('DNI', "+documento+", '"+nombre+"', '"+apellido+"', '"+fechanac+"','"+direccion+"')";
			
	        PreparedStatement preparedStatement = conn.prepareStatement(SQL);

	        preparedStatement.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);

	        ResultSet rs = preparedStatement.getGeneratedKeys();
	        rs.next();
	        int idPersonaCreada = rs.getInt(1);
	        
	        System.out.println("Persona creada");
	        
			System.out.println("idPersonaCreada:"+idPersonaCreada);

	        return idPersonaCreada;
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear la persona");
			mostrarMenu();
            return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear la persona");
			mostrarMenu();
            return 0;
		}
	}
	
	private static void listarPersonas() {
		String query = "SELECT * FROM persona";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
		      
		      ResultSet rs = preparedStatement.executeQuery(query);
		      
		      while (rs.next())
		      {
		        int id = rs.getInt("identificador");
		        String nombre = rs.getString("nombre");
		        
		        System.out.format("%s, %s", id, nombre);
		      }
		      preparedStatement.close();
		} catch (SQLException e) {

            System.out.println(e.getMessage());

        }
	    
	}
	
	public static Date StringToDate(String fecha){
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    java.util.Date date = null;
		try {
			date = dateFormat.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        java.sql.Date sql = new java.sql.Date(date.getTime());
        	
	    return sql ;
	}
	
	private static void crearTablas() {
		
		String persona =  "CREATE TABLE IF NOT EXISTS persona (" + 
				"    identificador  integer PRIMARY KEY NOT NULL AUTO_INCREMENT," + 
				"    tipodoc       	char(5) NOT NULL," + 
				"    documento 	    bigint UNIQUE NOT NULL," + 
				"    nombre       	varchar(40) NOT NULL," + 
				"    apellido       varchar(40) NOT NULL," + 
				"    fechanac		date NOT NULL," + 
				"    direccion		varchar(200) NOT NULL" + 
				");";
		
		String alumno = "CREATE TABLE IF NOT EXISTS alumno (" + 
				"    identificador  integer PRIMARY KEY NOT NULL AUTO_INCREMENT," + 
				"    idpersona	    integer UNIQUE REFERENCES persona (identificador) ," + 
				"    legajo 	    integer NOT NULL" + 
				");";
		
		String carrera = "CREATE TABLE IF NOT EXISTS carrera (" + 
				"    identificador  integer PRIMARY KEY NOT NULL AUTO_INCREMENT," + 
				"    nombre       	varchar(40) NOT NULL," + 
				"    descripcion    varchar(250)," + 
				"    fechadesde		date NOT NULL," + 
				"    fechahasta 	date" + 
				");";
		
		String curso = "CREATE TABLE IF NOT EXISTS curso (" + 
				"    identificador  integer PRIMARY KEY NOT NULL AUTO_INCREMENT," + 
				"    idcarrera 		integer REFERENCES carrera (identificador)," + 
				"    nombre         varchar(40) NOT NULL," + 
				"    descripcion    varchar(250)," + 
				"    cupomaximo 	smallint NOT NULL," + 
				"    anio			smallint NOT NULL," + 
				"    docente        varchar(250)" + 
				");";
		
		String inscripciones_carrera = "CREATE TABLE IF NOT EXISTS inscripciones_carrera(" + 
				"    idalumno 		integer NOT NULL REFERENCES alumno (identificador)," + 
				"    idcarrera		integer NOT NULL REFERENCES carrera (identificador)," + 
				"    fechainscripcion	date NOT NULL" + 
				");";
		
		String inscripciones_curso = "CREATE TABLE IF NOT EXISTS inscripciones_curso(" + 
				"    idalumno 		integer NOT NULL REFERENCES alumno (identificador) ," + 
				"    idcurso 		integer NOT NULL REFERENCES curso (identificador) ," + 
				"    nota 		    integer," + 
				"    fechainscripcion	date NOT NULL" + 
				");";						
        
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(persona);
	        preparedStatement.executeUpdate(persona, Statement.RETURN_GENERATED_KEYS);

	        PreparedStatement preparedStatement2 = conn.prepareStatement(alumno);
	        preparedStatement2.executeUpdate(alumno, Statement.RETURN_GENERATED_KEYS);
	        
	        PreparedStatement preparedStatement3 = conn.prepareStatement(carrera);
	        preparedStatement3.executeUpdate(carrera, Statement.RETURN_GENERATED_KEYS);

	        PreparedStatement preparedStatement4 = conn.prepareStatement(curso);
	        preparedStatement4.executeUpdate(curso, Statement.RETURN_GENERATED_KEYS);
	        
	        PreparedStatement preparedStatement5 = conn.prepareStatement(inscripciones_carrera);
	        preparedStatement5.executeUpdate(inscripciones_carrera, Statement.RETURN_GENERATED_KEYS);
	        
	        PreparedStatement preparedStatement6 = conn.prepareStatement(inscripciones_curso);
	        preparedStatement6.executeUpdate(inscripciones_curso, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

}
