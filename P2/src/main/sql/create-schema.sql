DROP TABLE IF EXISTS Transfer;
DROP TABLE IF EXISTS Reception;
DROP TABLE IF EXISTS Stock;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Warehouse;

CREATE TABLE Warehouse (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR (256) NOT NULL,
    location TEXT NOT NULL
);

CREATE TABLE Product (
    sku INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(256) NOT NULL,
    type VARCHAR(256) NOT NULL
);

CREATE TABLE Stock (
    quantity INTEGER NOT NULL,
    max_quantity INTEGER NOT NULL,
    min_quantity INTEGER NOT NULL,
    warehouse_id INTEGER NOT NULL,
    product_sku INTEGER NOT NULL,
    PRIMARY KEY (warehouse_id, product_sku),
    CONSTRAINT valid_quantity CHECK (quantity >= 0),
    CONSTRAINT valid_min_quantity CHECK (min_quantity >= 0),
    CONSTRAINT valid_max_quantity CHECK (max_quantity >= 0 AND max_quantity > min_quantity),
    CONSTRAINT product_warehouse_id FOREIGN KEY (warehouse_id) REFERENCES Warehouse(id),
    CONSTRAINT reception_product_id FOREIGN KEY (product_sku) REFERENCES Product(sku)
);

CREATE TABLE Reception (
    id SERIAL PRIMARY KEY NOT NULL,
    quantity INTEGER NOT NULL,
    supplier VARCHAR(256) NOT NULL,
    date DATE NOT NULL,
    warehouse_id INTEGER NOT NULL,
    product_sku INTEGER NOT NULL,
    CONSTRAINT valid_quantity CHECK (quantity >= 0),
    CONSTRAINT reception_warehouse_id FOREIGN KEY (warehouse_id) REFERENCES Warehouse(id),
    CONSTRAINT reception_product_id FOREIGN KEY (product_sku) REFERENCES Product(sku)
);

CREATE TABLE Transfer (
    id SERIAL PRIMARY KEY NOT NULL,
    date DATE NOT NULL,
    quantity INTEGER NOT NULL,
    origin_warehouse_id INTEGER,
    destination_warehouse_id INTEGER NOT NULL,
    product_sku INTEGER NOT NULL,
    CONSTRAINT valid_quantity CHECK (quantity >= 0),
    CONSTRAINT order_origin_warehouse_id FOREIGN KEY (origin_warehouse_id) REFERENCES Warehouse(id),
    CONSTRAINT order_destination_warehouse_id FOREIGN KEY (destination_warehouse_id) REFERENCES Warehouse(id),
    CONSTRAINT order_product_id FOREIGN KEY (product_sku) REFERENCES Product(sku)
);

INSERT INTO Warehouse VALUES (1, 'A Coruña', 'Polígono de POCOMACO');
INSERT INTO Warehouse VALUES (2, 'Santiago', 'Polígono del Tambre');
INSERT INTO Warehouse VALUES (3, 'Vigo', 'Polígono de Miraflores');
INSERT INTO Warehouse VALUES (4, 'Pontevedra', 'Polígono O Campiño');
INSERT INTO Warehouse VALUES (5, 'Lugo', 'Polígono Industrial das Gandaras');
INSERT INTO Warehouse VALUES (6, 'Orense', 'Polígono Industrial de San Cibrao');

INSERT INTO Product VALUES (1, 'Ahusoisw', 'Silla');
INSERT INTO Product VALUES (2, 'Bdajhub', 'Silla');
INSERT INTO Product VALUES (3, 'Gnashghn', 'Mesa');
INSERT INTO Product VALUES (4, 'Nabipu', 'Cama');
INSERT INTO Product VALUES (5, 'Arbnlib', 'Lámpara');
INSERT INTO Product VALUES (6, 'Las meninas', 'Cuadro');

INSERT INTO Stock (quantity, max_quantity, min_quantity, warehouse_id, product_sku)
VALUES
    (10, 1000, 5, 1, 1),
    (20, 110, 6, 1, 2),
    (30, 120, 7, 1, 3),
    (40, 130, 8, 1, 4),
    (50, 140, 9, 1, 5),
    (60, 150, 10, 1, 6),

    (15, 105, 6, 2, 1),
    (25, 1150, 7, 2, 2),
    (35, 125, 8, 2, 3),
    (45, 135, 9, 2, 4),
    (55, 145, 10, 2, 5),
    (65, 155, 11, 2, 6),

    (18, 108, 5, 3, 1),
    (28, 118, 6, 3, 2),
    (38, 1280, 7, 3, 3),
    (48, 138, 8, 3, 4),
    (58, 148, 9, 3, 5),
    (68, 158, 10, 3, 6),

    (42, 1320, 7, 4, 4),

    (19, 109, 5, 5, 1),
    (29, 119, 6, 5, 2),
    (39, 129, 7, 5, 3),
    (49, 139, 8, 5, 4),
    (59, 1490, 9, 5, 5),
    (69, 159, 10, 5, 6),

    (13, 103, 3, 6, 1),
    (23, 113, 4, 6, 2),
    (43, 133, 6, 6, 4),
    (53, 143, 7, 6, 5),
    (63, 1530, 8, 6, 6);

