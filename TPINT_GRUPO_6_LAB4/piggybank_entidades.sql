CREATE SCHEMA piggybank;

USE piggybank;

CREATE TABLE provincias (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE localidades (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idProvincia INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    FOREIGN KEY (idProvincia) REFERENCES provincias (id)
);

CREATE TABLE tiposUsuarios(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(15) NOT NULL
);

CREATE TABLE usuarios (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idTipoUsuario INT NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(50) NOT NULL,
    estado BIT NOT NULL,
    FOREIGN KEY (idTipoUsuario) REFERENCES tiposUsuarios (id)
);

CREATE TABLE clientes (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) NOT NULL UNIQUE,
    cuil VARCHAR(11) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo CHAR NOT NULL,
    nacionalidad VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    idProvincia INT NOT NULL,
    idLocalidad INT NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idProvincia) REFERENCES provincias(id),
    FOREIGN KEY (idLocalidad) REFERENCES localidades(id),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);

CREATE TABLE tiposCuentas(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(20) NOT NULL
);

CREATE TABLE cuentas(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idTipoCuenta INT NOT NULL,
    cbu VARCHAR(21) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    estado BIT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES clientes(id),
	FOREIGN KEY (idTipoCuenta) REFERENCES tiposCuentas(id)
);

CREATE TABLE prestamos(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    idCuenta INT NOT NULL,
    fechaSolicitud TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    importeAPagar DECIMAL NOT NULL,
    plazoDePago INT NOT NULL,
    importePedido DECIMAL NOT NULL,
    cuotas INT NOT NULL,
    importeMensual DECIMAL NOT NULL,
    estadoValidacion BIT,
    fechaValidacion DATETIME,
    FOREIGN KEY (idCliente) REFERENCES clientes(id),
    FOREIGN KEY (idCuenta) REFERENCES cuentas(id)
);

CREATE TABLE tiposMovimientos(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(20) NOT NULL
);

CREATE TABLE movimientos(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idTipoMovimiento INT NOT NULL,
    concepto VARCHAR(25) NOT NULL,
    idCuenta INT NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idTipoMovimiento) REFERENCES tiposMovimientos(id),
    FOREIGN KEY (idCuenta) REFERENCES cuentas(id)
);

-- DATOS DE PRUEBA

INSERT INTO tiposUsuarios (descripcion)
VALUES ('administrador');
INSERT INTO tiposUsuarios (descripcion)
VALUES ('cliente');

INSERT INTO tiposCuentas (descripcion)
VALUES ('Caja de ahorro');
INSERT INTO tiposCuentas (descripcion)
VALUES ('Cuenta corriente');

INSERT INTO tiposMovimientos (descripcion)
VALUES ('Alta de cuenta');
INSERT INTO tiposMovimientos (descripcion)
VALUES ('Alta de préstamo');
INSERT INTO tiposMovimientos (descripcion)
VALUES ('Pago de préstamo');
INSERT INTO tiposMovimientos (descripcion)
VALUES ('Transferencia');