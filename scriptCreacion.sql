--Crea tabla genero
CREATE TABLE genero (
genero VARCHAR(50) PRIMARY KEY
);

--Crea tabla usuario
CREATE TABLE usuario (
cuil BIGINT PRIMARY KEY,
nombre VARCHAR(50),
apellido VARCHAR(50),
suenoPromedio REAL,
aguaPromedio REAL,
fechaNacimiento DATE,
sexo CHAR(1),
genero VARCHAR(50),
imc NUMERIC,
estadoPeso VARCHAR(30),
horasDeporteSemanales REAL,
FOREIGN KEY (genero) REFERENCES genero(genero)
);

--Crea tabla telefonousuario
CREATE TABLE telefonoUsuario (
telefono BIGINT,
cuil BIGINT,
PRIMARY KEY (cuil, telefono),
FOREIGN KEY (cuil) REFERENCES usuario(cuil)
);

--Crea tabla mailUsuario
CREATE TABLE mailUsuario (
mail VARCHAR(50),
cuil BIGINT,
PRIMARY KEY (cuil, mail),
FOREIGN KEY (cuil) REFERENCES usuario(cuil)
);

--Crea tabla dieta
CREATE TABLE dieta (
id SERIAL PRIMARY KEY,
objetivoDieta VARCHAR(50)
);

--Crea tabla comida
CREATE TABLE comida (
id SERIAL,
comida VARCHAR(50) PRIMARY KEY
);

--Crea tabla dietaTieneComida
CREATE TABLE dietaTieneComida (
comida VARCHAR(50),
nroDieta SERIAL,
FOREIGN KEY (comida) REFERENCES comida(comida),
FOREIGN KEY (nroDieta) REFERENCES dieta(id),
PRIMARY KEY (nroDieta, comida)
);

--Crea tabla deporte
CREATE TABLE deporte (
id SERIAL,
nombre VARCHAR(50) PRIMARY KEY
);

--Crea tabla iniciaDeporte
CREATE TABLE iniciaDeporte (
cuil BIGINT,
fechaInicioDeporte DATE,
nombreDeporte VARCHAR(50),
PRIMARY KEY (cuil, fechaInicioDeporte),
FOREIGN KEY (cuil) REFERENCES usuario(cuil),
FOREIGN KEY (nombreDeporte) REFERENCES deporte(nombre)
);

--Crea tabla realizaDieta
CREATE TABLE realizaDieta (
cuil BIGINT,
fechaInicio DATE,
nroDieta SERIAL,
PRIMARY KEY (cuil, fechaInicio),
FOREIGN KEY (cuil) REFERENCES usuario(cuil),
FOREIGN KEY (nroDieta) REFERENCES dieta(id)
);

--Crea tabla medicion
CREATE TABLE medicion (
cuil BIGINT,
fechaMedicion DATE,
altura REAL,
PRIMARY KEY (cuil, fechaMedicion),
FOREIGN KEY (cuil) REFERENCES usuario(cuil)
);

--Crea tabla pesaje
CREATE TABLE pesaje (
cuil BIGINT,
fechaPesaje DATE,
peso REAL,
PRIMARY KEY (cuil, fechaPesaje),
FOREIGN KEY (cuil) REFERENCES usuario(cuil)
);

--Crea función utilizada en el trigger de inserción a la tabla de medición
CREATE OR REPLACE FUNCTION funcionMedicion()
RETURNS TRIGGER AS $$
DECLARE
p FLOAT;
imcc FLOAT;
a FLOAT;
f DATE;
estadoPesoc VARCHAR(30);
BEGIN
SELECT peso INTO p
FROM pesaje
WHERE cuil = NEW.cuil
ORDER BY fechapesaje DESC
LIMIT 1;

SELECT altura, fechaMedicion 
INTO a, f
FROM medicion
WHERE cuil = NEW.cuil
ORDER BY fechaMedicion DESC
LIMIT 1;

IF f IS NOT NULL THEN
    IF f < NEW.fechamedicion THEN
    a := NEW.altura;
    END IF;
END IF;

IF p IS NOT NULL AND a > 0 THEN
imcc := p / (a * a);
ELSE
imcc := NULL;
END IF;
IF imcc < 18.50 THEN
estadoPesoc := 'Delgadez';
ELSIF imcc > 24.99 THEN
estadoPesoc := 'Sobrepeso';
ELSEIF imcc >= 18.50 AND imcc <= 24.99 THEN
estadoPesoc := 'Peso Normal';
ELSE
estadoPesoc := NULL;
END IF;
UPDATE usuario
SET imc = imcc,
estadopeso = estadoPesoc
WHERE cuil = NEW.cuil;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Crea trigger para la tabla medicion
CREATE TRIGGER calcularimcusuariomedicion
AFTER INSERT OR UPDATE ON medicion
FOR EACH ROW
EXECUTE FUNCTION funcionmedicion();

--Crea función utilizada en el trigger de inserción a la tabla de pesaje
CREATE OR REPLACE FUNCTION funcionpesaje()
RETURNS TRIGGER AS $$
DECLARE
p FLOAT;
imcc FLOAT;
a FLOAT;
f DATE;
estadoPesoc VARCHAR(30);
BEGIN
SELECT altura INTO p
FROM medicion
WHERE cuil = NEW.cuil
ORDER BY fechamedicion DESC
LIMIT 1;

SELECT peso, fechaPesaje 
INTO a, f
FROM pesaje
WHERE cuil = NEW.cuil
ORDER BY fechaPesaje DESC
LIMIT 1;

IF f IS NOT NULL THEN
    IF f < NEW.fechapesaje THEN
    a := NEW.peso;
    END IF;
END IF;


IF p IS NOT NULL AND a > 0 THEN
imcc := a / (p * p);
ELSE
imcc := NULL;
END IF;

IF imcc < 18.50 THEN
estadoPesoc := 'Delgadez';
ELSIF imcc > 24.99 THEN
estadoPesoc := 'Sobrepeso';
ELSEIF imcc >= 18.50 AND imcc <= 24.99 THEN
estadoPesoc := 'Peso Normal';
ELSE
estadoPesoc := NULL;
END IF;
UPDATE usuario
SET imc = imcc,
estadopeso = estadoPesoc
WHERE cuil = NEW.cuil;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Crea trigger para la tabla pesaje
CREATE TRIGGER calcularimcusuariopesaje
AFTER INSERT OR UPDATE ON pesaje
FOR EACH ROW
EXECUTE FUNCTION funcionpesaje();

--Se insertan datos en la tabla deporte
INSERT INTO deporte (nombre) VALUES ('Fútbol');
INSERT INTO deporte (nombre) VALUES ('Baloncesto');
INSERT INTO deporte (nombre) VALUES ('Tenis');
INSERT INTO deporte (nombre) VALUES ('Voleibol');
INSERT INTO deporte (nombre) VALUES ('Atletismo');
INSERT INTO deporte (nombre) VALUES ('Natación');
INSERT INTO deporte (nombre) VALUES ('Gimnasia');
INSERT INTO deporte (nombre) VALUES ('Ciclismo');
INSERT INTO deporte (nombre) VALUES ('Boxeo');
INSERT INTO deporte (nombre) VALUES ('Hockey');

--Se insertan datos en la tabla genero
INSERT INTO genero (genero) VALUES ('Hombre');
INSERT INTO genero (genero) VALUES ('Mujer');
INSERT INTO genero (genero) VALUES ('No Binario');

--Se insertan datos en la tabla usuario
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (20451379632, 'Tomas', 'Rando',
6, 6, '2003-10-05', 'M', 'Hombre', NULL, NULL, 4);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (20403895033, 'Juan',
'González', 7, 4, '1985-05-15', 'M', 'Hombre', NULL, NULL, 2);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (27439583490, 'María', 'López',
6, 5, '1992-07-20', 'F', 'Mujer', NULL, NULL, 1);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (20175983008, 'Carlos',
'Martínez', 8, 6, '1988-03-10', 'M', 'Hombre', NULL, NULL, 5);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (27451374546, 'Laura',
'García', 7, 7, '1995-11-25', 'F', 'Mujer', NULL, NULL, 3);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (20205983849, 'Diego',
'Rodríguez', 6, 4, '1982-09-05', 'M', 'Hombre', NULL, NULL, 2);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (27334596848, 'Ana',
'Fernández', 8, 5, '1998-12-12', 'F', 'Mujer', NULL, NULL, 4);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (20451356438, 'Javier',
'Pérez', 7, 6, '1987-06-30', 'M', 'Hombre', NULL, NULL, 3);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (23464534954, 'Lucía', 'Gómez',
6, 7, '1993-04-18', 'F', 'Mujer', NULL, NULL, 2);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (20395434579, 'Pablo',
'Hernández', 8, 4, '1989-08-08', 'M', 'Hombre', NULL, NULL, 5);
INSERT INTO usuario (cuil, nombre, apellido, suenoPromedio, aguaPromedio, fechaNacimiento,
sexo, genero, imc, estadoPeso, horasDeporteSemanales) VALUES (27431294856, 'Marina', 'Torres', 7, 5, '1996-02-28', 'F', 'Mujer', NULL, NULL, 3);

--Se insertan datos en la tabla telefonoUsuario
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2613400481, 20451379632);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2618910423, 20403895033);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2614492875, 27439583490);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2615829378, 20175983008);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2613627459, 27451374546);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2617038294, 20205983849);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2612950386, 27334596848);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2615038496, 20451356438);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2617382940, 23464534954);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2614059283, 20395434579);
INSERT INTO telefonoUsuario (telefono, cuil) VALUES (2619283650, 27431294856);

--Se insertan datos en la tabla dieta
INSERT INTO dieta (objetivodieta) VALUES ('Perdida De Peso');
INSERT INTO dieta (objetivodieta) VALUES ('Ganancia Muscular');
INSERT INTO dieta (objetivodieta) VALUES ('Control de Peso');

--Se insertan datos en la tabla comida
INSERT INTO comida (comida) VALUES ('Pavo');
INSERT INTO comida (comida) VALUES ('Pechuga de pollo');
INSERT INTO comida (comida) VALUES ('Sopa de lentejas');
INSERT INTO comida (comida) VALUES ('Verduras mixtas');
INSERT INTO comida (comida) VALUES ('Sopa de verduras');
INSERT INTO comida (comida) VALUES ('Palta');
INSERT INTO comida (comida) VALUES ('Pollo a la parrilla');
INSERT INTO comida (comida) VALUES ('Salmón al horno');
INSERT INTO comida (comida) VALUES ('Vegetales asados');
INSERT INTO comida (comida) VALUES ('Tofu');
INSERT INTO comida (comida) VALUES ('Arroz integral');
INSERT INTO comida (comida) VALUES ('Carne magra');
INSERT INTO comida (comida) VALUES ('Huevos duros');
INSERT INTO comida (comida) VALUES ('Ensalada de garbanzos');

--Se insertan datos en la tabla dietaTieneComida
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Pavo', 1);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Pechuga de pollo', 1);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Sopa de lentejas', 1);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Verduras mixtas', 1);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Sopa de verduras', 2);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Palta', 2);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Pollo a la parrilla', 2);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Salmón al horno', 2);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Vegetales asados', 2);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Salmón al horno', 3);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Tofu', 3);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Pollo a la parrilla', 3);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Arroz integral', 3);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Carne magra', 3);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Huevos duros', 3);
INSERT INTO dietatienecomida (comida, nrodieta) VALUES ('Ensalada de garbanzos', 3);

--Se insertan datos en la tabla iniciaDeporte
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20451379632,
'2024-01-05', 'Fútbol');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20403895033,
'2024-02-26', 'Fútbol');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (27439583490,
'2024-01-02', 'Baloncesto');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20175983008,
'2024-03-05', 'Tenis');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (27451374546,
'2024-05-05', 'Voleibol');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20205983849,
'2023-12-12', 'Atletismo');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (27334596848,
'2023-10-05', 'Natación');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20451356438,
'2022-11-23', 'Gimnasia');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (23464534954,
'2024-01-24', 'Ciclismo');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20395434579,
'2024-03-29', 'Boxeo');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (27431294856,
'2022-08-18', 'Hockey');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20451356438,
'2020-12-20', 'Gimnasia');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20451379632,
'2020-12-20', 'Gimnasia');
INSERT INTO iniciadeporte (cuil, fechainiciodeporte, nombredeporte) VALUES (20451379632,
'2023-04-27', 'Baloncesto');

--Se insertan datos en la tabla mailusuario
INSERT INTO mailusuario (mail, cuil) VALUES ('tomasrando@gmail.com', 20451379632);
INSERT INTO mailusuario (mail, cuil) VALUES ('juangonzalez@gmail.com', 20403895033);
INSERT INTO mailusuario (mail, cuil) VALUES ('marialopez@gmail.com', 27439583490);
INSERT INTO mailusuario (mail, cuil) VALUES ('carlosmartinez@gmail.com', 20175983008);
INSERT INTO mailusuario (mail, cuil) VALUES ('lauragarcia@gmail.com', 27451374546);
INSERT INTO mailusuario (mail, cuil) VALUES ('diegorodriguez@gmail.com', 20205983849);
INSERT INTO mailusuario (mail, cuil) VALUES ('anafernandez@gmail.com', 27334596848);
INSERT INTO mailusuario (mail, cuil) VALUES ('javierperez@gmail.com', 20451356438);
INSERT INTO mailusuario (mail, cuil) VALUES ('luciagomez@gmail.com', 23464534954);
INSERT INTO mailusuario (mail, cuil) VALUES ('pablohernandez@gmail.com', 20395434579);
INSERT INTO mailusuario (mail, cuil) VALUES ('marinatorres@gmail.com', 27431294856);

--Se insertan datos en la tabla realizadieta
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (20451379632, '2024-05-05', 1);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (20403895033, '2023-12-15', 1);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (27439583490, '2023-11-22', 3);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (20175983008, '2024-01-30', 2);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (27451374546, '2023-09-05', 1);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (20205983849, '2023-10-18', 3);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (27334596848, '2024-04-07', 2);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (20451356438, '2024-02-12', 1);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (23464534954, '2023-08-25', 3);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (20395434579, '2023-11-03', 2);
INSERT INTO realizadieta (cuil, fechainicio, nrodieta) VALUES (27431294856, '2024-03-28', 1);

--Se insertan datos en la tabla medicion
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (20451379632, '2024-05-05', 1.75);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (20403895033, '2024-03-20', 1.82);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (27439583490, '2023-11-10', 1.65);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (20175983008, '2023-12-05', 1.70);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (27451374546, '2024-01-22', 1.75);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (20205983849, '2023-09-28', 1.68);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (27334596848, '2024-03-10', 1.73);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (20451356438, '2023-10-15', 1.80);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (23464534954, '2024-02-08', 1.76);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (20395434579, '2023-08-20', 1.72);
INSERT INTO medicion (cuil, fechamedicion, altura) VALUES (27431294856, '2023-12-30', 1.69);

--Se insertan datos en la tabla pesaje
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (20451379632, '2024-05-05', 80);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (20403895033, '2024-03-20', 85.0);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (27439583490, '2023-11-10', 67.5);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (20175983008, '2023-12-05', 70.0);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (27451374546, '2024-01-22', 75.0);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (20205983849, '2023-09-28', 68.0);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (27334596848, '2024-03-10', 72.5);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (20451356438, '2023-10-15', 79.0);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (23464534954, '2024-02-08', 76.5);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (20395434579, '2023-08-20', 74.0);
INSERT INTO pesaje (cuil, fechapesaje, peso) VALUES (27431294856, '2023-12-30', 53.0);

--Crea el rol de administrador con todos los permisos para gestionar la base de datos
CREATE ROLE administrador;
ALTER ROLE administrador
LOGIN
SUPERUSER
CREATEROLE
CREATEDB
REPLICATION
BYPASSRLS
PASSWORD '1234';
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO administrador;

--Crea el rol de empleado con permisos para modificar datos de la base de datos
CREATE ROLE empleado;
ALTER ROLE empleado
LOGIN
PASSWORD '1234';
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO empleado;

--Crea el rol de cliente con permisos para consultar datos de algunas tablas de la base
CREATE ROLE cliente;
ALTER ROLE cliente
LOGIN
PASSWORD '1234';
GRANT SELECT ON realizadieta TO cliente;
GRANT SELECT ON dietatienecomida TO cliente;
GRANT SELECT ON dieta TO cliente;