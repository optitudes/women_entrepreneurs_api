--Datos de prueba para la tabla activiy
insert into activiy (id, cost, name, tourist_route_id) values (1, 30.87, 'Madness of King George', 1);
insert into activiy (id, cost, name, tourist_route_id) values (2, 7.54, 'Good Bye', 2);
insert into activiy (id, cost, name, tourist_route_id) values (3, 34.04, 'Smilla', 3);
insert into activiy (id, cost, name, tourist_route_id) values (4, 85.23, 'Garden of Allah', 4);
insert into activiy (id, cost, name, tourist_route_id) values (5, 22.59, 'Celebrity', 5);

--Datos de prueba para la tabla: Blog
insert into blog (id, blog_category_id, micro_site_id) values (1, 1, 1);
insert into blog (id, blog_category_id, micro_site_id) values (2, 2, 2);
insert into blog (id, blog_category_id, micro_site_id) values (3, 3, 3);
insert into blog (id, blog_category_id, micro_site_id) values (4, 4, 4);
insert into blog (id, blog_category_id, micro_site_id) values (5, 5, 5);

--Datos de prueba para la tabla: blog_category
insert into blog_category (id, name) values (1, 'Categoria-1');
insert into blog_category (id, name) values (2, 'Categoria-2');
insert into blog_category (id, name) values (3, 'Categoria-3');

--Datos de prueba para la tabla: Comment
INSERT INTO comment (id, title, content, created_at) VALUES (1, 'Comentario 1', 'Este es el primer comentario.', NOW());
INSERT INTO comment (id, title, content, created_at) VALUES (2, 'Comentario 2', 'Este es el segundo comentario.', NOW());
INSERT INTO comment (id, title, content, created_at) VALUES (3, 'Comentario 3', 'Este es el tercer comentario.', NOW());
INSERT INTO comment (id, title, content, created_at) VALUES (4, 'Comentario 4', 'Este es el cuarto comentario.', NOW());
INSERT INTO comment (id, title, content, created_at) VALUES (5, 'Comentario 5', 'Este es el quinto comentario.', NOW());

--Datos de prueba para la tabla: document
INSERT INTO document (id, created_at, deleted_at, description, name, url, micro_site_id)
VALUES
    (1, NOW(), NULL, 'Descripción 1', 'Nombre 1', 'URL 1', 1),
    (2, NOW(), NULL, 'Descripción 2', 'Nombre 2', 'URL 2', 2),
    (3, NOW(), NULL, 'Descripción 3', 'Nombre 3', 'URL 3', 3),
    (4, NOW(), NULL, 'Descripción 4', 'Nombre 4', 'URL 4', 4),
    (5, NOW(), NULL, 'Descripción 5', 'Nombre 5', 'URL 5', 5);

--Datos de prueba para la tabla: image
INSERT INTO image (id, created_at, deleted_at, url, updated_at, micro_site_id)
VALUES
    (1, NOW(), NULL, 'https://example.com/image1.jpg', NULL, 1),
    (2, NOW(), NULL, 'https://example.com/image2.jpg', NULL, 2),
    (3, NOW(), NULL, 'https://example.com/image3.jpg', NULL, 3),
    (4, NOW(), NULL, 'https://example.com/image4.jpg', NULL, 4),
    (5, NOW(), NULL, 'https://example.com/image5.jpg', NULL, 5);

--Datos de prueba para la tabla: itinerary
INSERT INTO itinerary (id, tourist_route_id, name, description)
VALUES
    (1, 1, 'Itinerario 1', 'Descripción del itinerario 1'),
    (2, 2, 'Itinerario 2', 'Descripción del itinerario 2'),
    (3, 3, 'Itinerario 3', 'Descripción del itinerario 3'),
    (4, 4, 'Itinerario 4', 'Descripción del itinerario 4'),
    (5, 5, 'Itinerario 5', 'Descripción del itinerario 5');

--Datos de prueba para la tabla: itinerary_item

INSERT INTO itinerary_item (id, itinerary_id, title, place, description)
VALUES
    (1, 1, 'Título 1', 'Lugar 1', 'Descripción 1'),
    (2, 2, 'Título 2', 'Lugar 2', 'Descripción 2'),
    (3, 3, 'Título 3', 'Lugar 3', 'Descripción 3'),
    (4, 4, 'Título 4', 'Lugar 4', 'Descripción 4'),
    (5, 5, 'Título 5', 'Lugar 5', 'Descripción 5');

--Datos de prueba para la tabla: level_access;
INSERT INTO level_access(id, access_code, created_at,description)
VALUES
    (1,10, NOW(),'Descripción 1')
    (2,20, NOW(),'Descripción 2')
    (3,30, NOW(),'Descripción 3')

--Datos de prueba para la tabla: location_site
INSERT INTO location_site (id, description, img_url, map_latitude, map_longitude, name, url, tourist_route_id)
VALUES
    (1, 'Descripción 1', 'https://example.com/image1.jpg', 1.234567, 2.345678, 'Nombre 1', 'URL 1', 1),
    (2, 'Descripción 2', 'https://example.com/image2.jpg', 3.456789, 4.567890, 'Nombre 2', 'URL 2', 2),
    (3, 'Descripción 3', 'https://example.com/image3.jpg', 5.678901, 6.789012, 'Nombre 3', 'URL 3', 3),
    (4, 'Descripción 4', 'https://example.com/image4.jpg', 7.890123, 8.901234, 'Nombre 4', 'URL 4', 4),
    (5, 'Descripción 5', 'https://example.com/image5.jpg', 9.012345, 10.123456, 'Nombre 5', 'URL 5', 5);

 --Datos de prueba para la tabla: micro_site
INSERT INTO micro_site (id, address, description, experiences, name, venture_id)
VALUES
    (1, 'Calle 100 # 100 - 100', 'Descripción del micrositio 1', 'Experiencias del micrositio 1', 'Nombre del micrositio 1', 1),
    (2, 'Calle 200 # 200 - 200', 'Descripción del micrositio 2', 'Experiencias del micrositio 2', 'Nombre del micrositio 2', 2),
    (3, 'Calle 300 # 300 - 300', 'Descripción del micrositio 3', 'Experiencias del micrositio 3', 'Nombre del micrositio 3', 3),
    (4, 'Calle 400 # 400 - 400', 'Descripción del micrositio 4', 'Experiencias del micrositio 4', 'Nombre del micrositio 4', 4),
    (5, 'Calle 500 # 500 - 500', 'Descripción del micrositio 5', 'Experiencias del micrositio 5', 'Nombre del micrositio 5', 5);

--Datos de prueba para la tabla: product
INSERT INTO product (id, name, price, description, type, is_active, created_at, updated_at)
VALUES
    (1, 'Producto 1', 10000, 'Descripción del producto 1', 'Tipo 1', 1, NOW(), NULL),
    (2, 'Producto 2', 20000, 'Descripción del producto 2', 'Tipo 2', 1, NOW(), NULL),
    (3, 'Producto 3', 30000, 'Descripción del producto 3', 'Tipo 3', 1, NOW(), NULL),
    (4, 'Producto 4', 40000, 'Descripción del producto 4', 'Tipo 4', 1, NOW(), NULL),
    (5, 'Producto 5', 50000, 'Descripción del producto 5', 'Tipo 5', 1, NOW(), NULL);

--Datos de prueba para la tabla: profile
INSERT INTO profile (id, address, created_at, deleted_at, id_number, last_names, phone_number, picture_url, updated_at, user_id)
VALUES
    (1, 'Calle 100 # 100 - 100', NOW(), NULL, '123456789', 'Apellidos 1', '3101234567', 'https://example.com/image1.jpg', NULL, 1),
    (2, 'Calle 200 # 200 - 200', NOW(), NULL, '987654321', 'Apellidos 2', '3201234567', 'https://example.com/image2.jpg', NULL, 2),
    (3, 'Calle 300 # 300 - 300', NOW(), NULL, '123456789', 'Apellidos 3', '3111234567', 'https://example.com/image3.jpg', NULL, 3),
    (4, 'Calle 400 # 400 - 400', NOW(), NULL, '987654321', 'Apellidos 4', '3211234567', 'https://example.com/image4.jpg', NULL, 4),
    (5, 'Calle 500 # 500 - 500', NOW(), NULL, '123456789', 'Apellidos 5', '3121234567', 'https://example.com/image5.jpg', NULL, 5);

--Datos de prueba para la tabla: user
INSERT INTO user (id, email, password, is_active, level_access_id)
VALUES
    (1, 'johndoe@example.com', 'password', true,1),
    (2, 'janedoe@example.com', 'password', true,1),
    (3, 'mariano@example.com', 'password', true,1),
    (4, 'luisa11@example.com', 'password', true,1),
    (5, 'juan112@example.com', 'password', true,1);


--Datos de prueba para la tabla: review
INSERT INTO review (id, content, created_at, deleted_at, rate_number, title, profile_id, tourist_route_id)
VALUES
    (1, 'Esta fue una experiencia increíble. Lo recomiendo encarecidamente.', NOW(), NULL, 5, 'Título 1', 1, 1),
    (2, 'El lugar era hermoso y el guía fue muy informativo.', NOW(), NULL, 4, 'Título 2', 2, 2),
    (3, 'No me gustó mucho la experiencia. El lugar estaba sucio y el guía no era muy bueno.', NOW(), NULL, 2, 'Título 3', 3, 3),
    (4, 'Fue una experiencia promedio. Ni bueno ni malo.', NOW(), NULL, 3, 'Título 4', 4, 4),
    (5, 'No puedo creer lo increíble que fue esto. ¡Definitivamente volveré!', NOW(), NULL, 5, 'Título 5', 5, 5);

--Datos de prueba para la tabla: service
INSERT INTO service (id, name, description, is_active, price)
VALUES
    (1, 'Tour', 'Recorrido por la ciudad', 1, 'Tour'),
    (2, 'Alquiler de bicicletas', 'Alquiler de bicicletas', 1, 'Actividad'),
    (3, 'Clase de cocina', 'Clase de cocina', 1, 'Clase'),
    (4, 'Entrada al museo', 'Entrada al museo', 1, 'Actividad'),
    (5, 'Paseo en bote', 'Paseo en bote', 1, 'Actividad');

--Datos de prueba para la tabla: tourist_route
INSERT INTO tourist_route (name, description, map_image, micro_site_id)
VALUES
    ('Ruta del café', 'Un recorrido por las fincas cafeteras de Colombia', 'https://www.example.com/images/ruta-del-cafe.jpg', 1),
    ('Ruta del sol', 'Un recorrido por las playas de Colombia', 'https://www.example.com/images/ruta-del-sol.jpg', 2),
    ('Ruta de la aventura', 'Un recorrido por los destinos de aventura de Colombia', 'https://www.example.com/images/ruta-de-la-aventura.jpg', 3),
    ('Ruta de la cultura', 'Un recorrido por los destinos culturales de Colombia', 'https://www.example.com/images/ruta-de-la-cultura.jpg', 4),
    ('Ruta de la gastronomía', 'Un recorrido por los destinos gastronómicos de Colombia', 'https://www.example.com/images/ruta-de-la-gastronomia.jpg', 5);


--Datos de prueba para la tabla: venture
INSERT INTO venture (id, name, address)
VALUES
    (1, 'Empresa 1', 'Calle 123, Bogotá, Colombia'),
    (2, 'Empresa 2', 'Carrera 456, Medellín, Colombia'),
    (3, 'Empresa 3', 'Avenida 789, Cali, Colombia'),
    (4, 'Empresa 4', 'Calle 101, Barranquilla, Colombia'),
    (5, 'Empresa 5', 'Carrera 555, Cartagena, Colombia');

--Datos de prueba para la tabla: video
INSERT INTO video (lid, created_at, deleted_at, url, micro_site_id)
VALUES
    (1, NOW(), NULL, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 1),
    (2, NOW(), NULL, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 2),
    (3, NOW(), NULL, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 3),
    (4, NOW(), NULL, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 4),
    (5, NOW(), NULL, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 5);
