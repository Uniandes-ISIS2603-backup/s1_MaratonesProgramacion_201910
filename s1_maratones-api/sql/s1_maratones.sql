delete from EjercicioEntity;
delete from LenguajeEntity;
delete from SubmissionEntity;
delete from LugarCompetenciaEntity;
delete from CompetenciaEntity;
delete from UsuarioEntity;



insert into LenguajeEntity (id, nombre, experiencia) values (100, 'Python', 10);
insert into LenguajeEntity (id, nombre, experiencia) values (200, 'Java', 5); 
insert into LenguajeEntity (id, nombre, experiencia) values (300, 'C#', 7);

insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (100, 'Earthquake Prediction', 'descripcion ', 'abababab', 'catcat', 1000, 3);
insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (200, 'Thermometer', 'descripcion ','catcat', 'abababab', 300, 2);
insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (300, 'Beat the aliens','descripcion ', 'thothot', 'catcat', 80, 1);

insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (100, 'asdfgh', 96, 103, './data/100', '2019-01-12 15:12:34', 'En revision');
insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (200, 'qwerty', 108, 82, './data/200', '2019-01-12 15:12:35', 'En revision');
insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (300, 'zxcvb', 76, 34, './data/300', '2016-01-12 15:12:36', 'En revision');


insert into CompetenciaEntity (id, esVirtual, fechaInicio, nombre, descripcion, puntos, condiciones, fechaFin,nivel ) values (100,'si','2019-01-12 2:12:35', 'thotho','mehmeh',30,'cabeza','2019-01-12 18:12:35', 10);

insert into LugarCompetenciaEntity (id,fecha, ubicaciones,competencia_id) values (100,'2019-01-12 15:12:35', 'cra 43 # 33 21',100);
insert into LugarCompetenciaEntity (id,fecha, ubicaciones,competencia_id) values (200,'2019-01-12 15:12:35', 'cra 43 # 45 45',100);
insert into LugarCompetenciaEntity (id,fecha, ubicaciones,competencia_id) values (300,'2019-01-12 15:12:35', 'cra 43 # 21 21',100);

insert into UsuarioEntity (id, nombreusuario,nombre, puntaje,rol,correo,clave) values (8,'camilalonart','Maria Camila', 2110,'Participante','camilalonart@losAlpes.edu.co','ABcd23485$sh');
