INSERT INTO level_access (id,access_code, description)
VALUES (1,1, 'corresponde a los administradores');
INSERT INTO level_access (id,access_code, description)
VALUES (2,2, 'corresponde a los usuarios normales');


INSERT INTO users (id,email, name, password, dni, phone_number, address, is_active,level_access_id)
VALUES (1,'sharon@test.com', 'Sharon', 'password', '123456789', 5551234, 'calarca1', true,2);

INSERT INTO users (id, email, name, password, dni, phone_number, address, is_active,level_access_id)
VALUES (2,'diego@test.com', 'diego', 'password', '123456788', 5555678, 'armenia1', true,1);

INSERT INTO users (id, email, name, password, dni, phone_number, address, is_active, level_access_id)
VALUES (3,'sebas@test.com', 'sebas', 'password', '123456777', 5555678, 'armenia1', true, 2);

INSERT INTO users (id, email, name, password, dni, phone_number, address, is_active, level_access_id)
VALUES (4,'optitudes@test.com', 'optt', 'password', '123456666', 5555678, 'armenia1', true, 1);

INSERT INTO users (id, email, name, password, dni, phone_number, address, is_active, level_access_id)
VALUES (5,'test@test.com', 'test', 'password', '123457777', 5555678, 'armenia1', true, 2);

INSERT INTO coupon (id, coupon_value, description, is_active, limit_date, security_code, user_id)
VALUES (1,125000.0, 'Cupon generado por que si', true, '2023-12-12', '555-5678', 1);
INSERT INTO coupon (id, coupon_value, description, is_active, limit_date, security_code, user_id)
VALUES (2,130000.0, 'Cupon generado como prueba', true, '2023-12-12', '555-56784', 2);
INSERT INTO coupon (id, coupon_value, description, is_active, limit_date, security_code, user_id)
VALUES (3,125000.0, 'Cupon generado automaticamente', false, '2023-12-12', '555-56783', 3);
INSERT INTO coupon (id, coupon_value, description, is_active, limit_date, security_code, user_id)
VALUES (4,125000.0, 'Cupon generado automativamente', true, '2023-12-12', '555-56785', 4);


INSERT INTO product (id, description, image, is_active, is_available,stacks, name, price)
VALUES (1,'un pan muy rico', 'image/url', true, true,1, 'pan', 4.0);

INSERT INTO product (id, description, image, is_active, is_available,stacks, name, price)
VALUES (2,'Lampara de noche muy luminosa', 'image/url', true, true,1, 'Lampara de noche', 200.0);

INSERT INTO product (id, description, image, is_active, is_available,stacks, name, price)
VALUES (3,'LLanta de carro', 'image/url', true, true,1, 'Llanta carro', 4000.0);

INSERT INTO product (id, description, image, is_active, is_available,stacks, name, price)
VALUES (4,'Guitarra gypson 2022', 'image/url', true, true,1, 'gypson guitarra', 499000.0);


INSERT INTO comment (id, comment, product_id, user_id)
VALUES (1,'El pan está muy rico', 1, 1);
INSERT INTO comment (id, comment, product_id, user_id)
VALUES (2,'Alumbra bastante bien', 2, 2);
INSERT INTO comment (id, comment, product_id, user_id)
VALUES (3,'Me sirvió mucho esa llanta',3,3);
INSERT INTO comment (id, comment, product_id, user_id)
VALUES (4,'Yo tengo una igual y son muy buenas', 4, 4);

INSERT INTO category (id, description, name)
VALUES (1,'Productos familiares', 'Familiar');
INSERT INTO category (id, description, name)
VALUES (2,'Productos para el cuidado del hogar', 'Hogar');
INSERT INTO category (id, description, name)
VALUES (3,'Productos al aire libre ', 'Aire libre');
INSERT INTO category (id, description, name)
VALUES (4,'Productos de iluminacion', 'Iluminacion');

INSERT INTO favorite (id, user_id)
VALUES (1,1);
INSERT INTO favorite (id, user_id)
VALUES (2,2);
INSERT INTO favorite (id, user_id)
VALUES (3,3);
INSERT INTO favorite (id, user_id)
VALUES (4,4);

INSERT INTO shopping_cart (id,full_value, user_id)
VALUES (1,0.0,1);
INSERT INTO shopping_cart (id,full_value, user_id)
VALUES (2,0.0,2);
INSERT INTO shopping_cart (id,full_value, user_id)
VALUES (3,0.0,3);
INSERT INTO shopping_cart (id,full_value, user_id)
VALUES (4,0.0,4);


INSERT INTO shopping_cart_product_list (shopping_cart_list_id,product_list_id)
VALUES (1,1);
INSERT INTO shopping_cart_product_list (shopping_cart_list_id,product_list_id)
VALUES (2,2);
INSERT INTO shopping_cart_product_list (shopping_cart_list_id,product_list_id)
VALUES (3,3);
INSERT INTO shopping_cart_product_list (shopping_cart_list_id,product_list_id)
VALUES (4,4);

INSERT INTO favorite_product_list (favorite_list_id,product_list_id)
VALUES (1,1);
INSERT INTO favorite_product_list (favorite_list_id,product_list_id)
VALUES (2,2);
INSERT INTO favorite_product_list (favorite_list_id,product_list_id)
VALUES (3,3);
INSERT INTO favorite_product_list (favorite_list_id,product_list_id)
VALUES (4,4);

INSERT INTO category_product_list (category_list_id,product_list_id)
VALUES (1,1);
INSERT INTO category_product_list (category_list_id,product_list_id)
VALUES (2,2);
INSERT INTO category_product_list (category_list_id,product_list_id)
VALUES (3,3);
INSERT INTO category_product_list (category_list_id,product_list_id)
VALUES (4,4);

INSERT INTO bill (id, bill_code, total, user_id)
VALUES (1, '001', 100.50, 1);
INSERT INTO bill (id, bill_code, total, user_id)
VALUES (2, '002', 100.50, 2);
INSERT INTO bill (id, bill_code, total, user_id)
VALUES (3, '003', 100.50, 3);
INSERT INTO bill (id, bill_code, total, user_id)
VALUES (4, '004', 100.50, 4);
INSERT INTO bill (id, bill_code, total, user_id)
VALUES (5, '005', 100.50, 1);

INSERT INTO bill_detail (id, amount, price, bill_id, product_id)
VALUES (1, 2, 51.00, 1, 1);
INSERT INTO bill_detail (id, amount, price, bill_id, product_id)
VALUES (5, 6, 52.00, 1, 1);
INSERT INTO bill_detail (id, amount, price, bill_id, product_id)
VALUES (6, 4, 52.00, 5, 1);
INSERT INTO bill_detail (id, amount, price, bill_id, product_id)
VALUES (2, 2, 50.00, 2, 2);
INSERT INTO bill_detail (id, amount, price, bill_id, product_id)
VALUES (3, 2, 50.00, 3, 3);
INSERT INTO bill_detail (id, amount, price, bill_id, product_id)
VALUES (4, 2, 50.00, 4, 4);








