delete from EjercicioEntity;
delete from LenguajeEntity;
delete from SubmissionEntity;

insert into LenguajeEntity (id, nombre, experiencia) values (100, 'Python', 10);
insert into LenguajeEntity (id, nombre, experiencia) values (200, 'Java', 5); 
insert into LenguajeEntity (id, nombre, experiencia) values (300, 'C#', 7);

insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (100, 'Earthquake Prediction', 'abababab', 'catcat', 1000, 3);
insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (200, 'Thermometer', 'catcat', 'abababab', 300, 2);
insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (300, 'Beat the aliens', 'thothot', 'catcat', 80, 1);

insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (100, 'asdfgh', 96, 103, './data/100', '10/23/19', 'En revision');
insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (200, 'qwerty', 108, 82, './data/200', '10/23/19', 'En revision');
insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (300, 'zxcvb', 76, 34, './data/300', '10/23/19', 'En revision');

