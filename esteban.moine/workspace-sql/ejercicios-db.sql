----EJERCICIOS
-- SOLO ES REQUERIDO REALIZAR LOS EJERCICIOS DE COMPLEJIDAD BAJA, realizar los demas ejercicios serán puntos extras para la evaluación del examen.


--- EJERCICIO 1 - COMPLEJIDAD BAJA: 
--Realizar una consulta para consultar todos los alumnos existentes, mostrar: TipoDoc, Documento, Nombre, Apellido, Legajo.

	CONSULTA = SELECT persona.tipodoc, persona.documento, persona.nombre, persona.apellido, alumno.legajo
				FROM persona
				INNER JOIN alumno
				ON persona.identificador = alumno.idpersona;


--- EJERCICIO 2 - COMPLEJIDAD BAJA: 
--Realizar una consulta para consultar todas las carreras a la que un alumno esta inscripto, mostrar: Legajo, nombre, apellido, nombre carrera, fechainscripcioncarrera
--ordenado por legajo descendiente
	
	CONSULTA = SELECT alumno.legajo, persona.nombre, persona.apellido, carrera.nombre,inscripciones_carrera.fechainscripcion
				FROM alumno
				INNER JOIN persona
				ON alumno.idpersona = persona.identificador
				INNER JOIN inscripciones_carrera
				ON inscripciones_carrera.idalumno = alumno.identificador
				INNER JOIN carrera
				ON carrera.identificador = inscripciones_carrera.idcarrera ORDER BY alumno.legajo ASC ;

--- EJERCICIO 3 - COMPLEJIDAD MEDIA: 
--Realizar una consulta para consultar la cantidad de inscriptos por curso, mostrando: nombre carrera, nombre curso, cantidad inscriptos, cupo máximo por curso

	CONSULTA = SELECT curso.nombre, curso.cupomaximo, COUNT(inscripciones_curso.idcurso) AS 'cantidad inscriptos' FROM inscripciones_curso INNER JOIN curso ON inscripciones_curso.idcurso=curso.identificador GROUP BY inscripciones_curso.idcurso

--- EJERCICIO 4 - COMPLEJIDAD ALTA: 
--Sobre la consulta anterior (copiar y pegarla y modificarla) modificar la sql para que la consulta retorne solo los cursos cuya cantidad de inscripciones 
--supera su cupo maximo



--- EJERCICIO 5 -  COMPLEJIDAD BAJA: 
-- actualizar todos las cursos que posean anio 2018 y cuyo cupo sea menor a 5, y actualizar el cupo de todos ellos a 10.

	CONSULTA =  UPDATE curso
				  SET cupomaximo=10
				  WHERE anio=2018 AND cupomaximo<5

--- EJERCICIO 6 -  COMPLEJIDAD ALTA: 
-- actualizar todas las fechas de inscripcion a cursados que posean el siguiente error: la fecha de inscripcion al cursado de un 
-- alumno es menor a la fecha de inscripcion a la carrera. La nueva fecha que debe tener es la fecha del dia. Se puede hacer en dos pasos, primero
-- realizando la consulta y luego realizando el update manual


--- EJERCICIO 7 - COMPLEJIDAD BAJA:  
--INSERTAR un nuevo registro de alumno con tus datos personales, (hacer todos inserts que considera necesario)

	INSERT INTO persona (tipodoc,documento,nombre,apellido,fechanac) VALUES ('DNI', 38979109, 'Esteban', 'Moine', '1995-09-30')
	INSERT INTO alumno (identificador,idpersona,legajo) VALUES (10,6,12345)

--- EJERCICIO 8 -  COMPLEJIDAD BAJA: 
-- si se desea comenzar a persistir de ahora en mas el dato de direccion de un alumno y considerando que este es un único cambio string de 200 caracteres.
-- Determine sobre que tabla seria mas conveniente persistir esta información y realizar la modificación de estructuras correspondientes.

	LO MEJOR SERIA AGREGAR EL CAMPO DIRECCION A LA TABLA PERSONA
	CONSULTA = alter table persona
			   add direccion varchar(200) not null;