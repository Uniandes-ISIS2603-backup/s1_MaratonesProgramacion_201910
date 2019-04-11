
delete from EjercicioEntity;
delete from LenguajeEntity;
delete from SubmissionEntity;
delete from ForoEntity;
delete from ComentarioEntity;

insert into LenguajeEntity (id, nombre, experiencia) values (100, 'Python', 10);
insert into LenguajeEntity (id, nombre, experiencia) values (200, 'Java', 5); 
insert into LenguajeEntity (id, nombre, experiencia) values (300, 'C#', 7);

insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (100, 'Earthquake Prediction', 'descripcion ', 'abababab', 'catcat', 1000, 3);
insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (200, 'Thermometer', 'descripcion ','catcat', 'abababab', 300, 2);
insert into EjercicioEntity (id, nombre, descripcion, inputt, outputt, puntaje, nivel) values (300, 'Beat the aliens','descripcion ', 'thothot', 'catcat', 80, 1);

insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (100, 'asdfgh', 96, 103, './data/100', '2019-01-12 15:12:34', 'En revision');
insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (200, 'qwerty', 108, 82, './data/200', '2019-01-12 15:12:35', 'En revision');
insert into SubmissionEntity (id, codigo, memoria, tiempo, archivo, fecha, veredicto) values (300, 'zxcvb', 76, 34, './data/300', '2016-01-12 15:12:36', 'En revision');

insert into ForoEntity (id, nombre, votosAFavor, votosEnContra, descripcion, fecha, tags) values (100, 'helppppppp', 10, 1, 'No sé cómo hacer esto', '2019-04-11 10:12:12', '#help');
insert into ForoEntity (id, nombre, votosAFavor, votosEnContra, descripcion, fecha, tags) values (101, 'TensorFlow', 1000, 0, 'Machine learning en maratones?', '2019-04-11 11:12:12', '#help');
insert into ForoEntity (id, nombre, votosAFavor, votosEnContra, descripcion, fecha, tags) values (102, 'sdfsdsfsfs', 1, 10, 'sdfsdfdfsd', '2019-04-11 12:12:12', '#help');

insert into ComentarioEntity (id, mensaje, votosAFavor, votosEnContra) values (100, 'no sé', 10, 100);
insert into ComentarioEntity (id, mensaje, votosAFavor, votosEnContra) values (101, 'Eso es el back jodido', 100, 100);
insert into ComentarioEntity (id, mensaje, votosAFavor, votosEnContra) values (102, 'no sé', 10000, 100);


