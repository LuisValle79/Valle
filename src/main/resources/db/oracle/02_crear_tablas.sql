-- Secuencia para el ID de clientes
CREATE SEQUENCE SEQ_CLIENTES_ID
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Tabla de clientes
CREATE TABLE CLIENTES (
    id NUMBER DEFAULT SEQ_CLIENTES_ID.NEXTVAL PRIMARY KEY,
    fecha TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    nombres VARCHAR2(100) NOT NULL,
    apellidos VARCHAR2(100) NOT NULL,
    dni VARCHAR2(8) NOT NULL UNIQUE,
    correo VARCHAR2(100),
    direccion VARCHAR2(200),
    celular VARCHAR2(9),
    asesor VARCHAR2(50) NOT NULL,
    estado VARCHAR2(20),
    CONSTRAINT chk_dni_length CHECK (LENGTH(dni) = 8),
    CONSTRAINT chk_celular_length CHECK (LENGTH(celular) = 9),
    CONSTRAINT chk_asesor CHECK (asesor IN (
        'Asesor 1', 'Asesor 2', 'Asesor 3', 'Asesor 4',
        'Asesor 5', 'Asesor 6', 'Asesor 7', 'Asesor 8'
    ))
);

-- Índices para mejorar el rendimiento
CREATE INDEX idx_clientes_dni ON CLIENTES(dni);
CREATE INDEX idx_clientes_fecha ON CLIENTES(fecha);

-- Comentarios en las columnas
COMMENT ON TABLE CLIENTES IS 'Tabla para almacenar información de clientes de la inmobiliaria';
COMMENT ON COLUMN CLIENTES.id IS 'Identificador único del cliente';
COMMENT ON COLUMN CLIENTES.fecha IS 'Fecha y hora de registro del cliente';
COMMENT ON COLUMN CLIENTES.nombres IS 'Nombres del cliente';
COMMENT ON COLUMN CLIENTES.apellidos IS 'Apellidos del cliente';
COMMENT ON COLUMN CLIENTES.dni IS 'DNI del cliente (8 dígitos)';
COMMENT ON COLUMN CLIENTES.correo IS 'Correo electrónico del cliente';
COMMENT ON COLUMN CLIENTES.direccion IS 'Dirección del cliente';
COMMENT ON COLUMN CLIENTES.celular IS 'Número de celular del cliente (9 dígitos)';
COMMENT ON COLUMN CLIENTES.asesor IS 'Asesor asignado al cliente';
COMMENT ON COLUMN CLIENTES.estado IS 'Estado del cliente';
