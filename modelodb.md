//DBML- Energy Management System
//Este archivo define:
//-La estructura de la base de datos
//-Las tablas
//-Las relaciones entre ellas
//
//El objetivo es representar un sistema de
//gestion y produccion energético 
//con auditoría de usuario

/*
Tabla: country
Función
-Tabla maestra
-almacena los países donde opera el sistema
-Evita duplicar el nombre de países en otras
tablas
-punto raíz del modelo geográfico
*/
Table country{
  id integer [primary key]
  name varchar [unique]
}
/*
Tabla: region
Función
-Representa regiones o departamentos de un país
-depende directamente de la tabla country
-permite análisis geográficos más detallados
*/
Table region{
  id integer [primary key]
  name varchar
  country_id integer [not null]//FK
  indexes {
    (name,country_id) [unique]//región única por país
  }
}
/*
Tabla: company
Función
-Representa la compañia generadora o
gestora de energía
-cada empresa pertenece a un país
-permite agrupar plantas por empresa
*/
Table company{
  id integer [primary key]
  name varchar
  country_id integer [not null]//FK
  indexes {
    (name,country_id) [unique]
  }
}
/*
Tabla: energy_type
Función
-Tabla catálogo de tipos de energía
-ej: solar, eólica, hidráulica
-permite clasificar las plantas por tipo de
energía
*/
Table energy_type{
  id integer [primary key]
  name varchar [unique]
  renewbable boolean
}
/*
Tabla: power_plant
Función:
-entidad central del sistema
-representa un planta generadora de energía
-conecta empresa, región y tipo de energía
*/
Table power_plant{
  id integer [primary key]
  name varchar
  company_id integer [not null]//FK company
  region_id integer [not null]//FK region
  energy_type_id integer [not null]//FK energytype
  indexes {
    (name, company_id) [unique]
  }
}
/*
Tabla: measurement_type
Función:
-define tipos de medicion energética
-ej: producción, consumo, cpaciadad
-separa el tipo de dato del valor medido
*/
Table measurement_type{
  id integer [primary key]
  name varchar [unique]
  unit varchar//unidad de medida
}
/*
Tabla: energy_record
Función:
-tabla transaccional
-alamcena los valores históricos de energia
-es la base para reportes y anáslisis
*/
Table energy_record{
  id bigint [primary key]
  year integer
  month integer
  value decimal//valor energético medido
  power_plant_id integer [not null]//FK powerplant
  measurement_type_id integer [not null]//FK measurement_type
  indexes {
    (power_plant_id, year, month, measurement_type_id) [unique]
  }
}

//define el enum 
Enum Role{
  ADMIN
  USER
  ANALYST
}
/*
Tabla: users
Función:
-representa los usuariosdel sistema
-gestiona autentificación de roles
-no pertenece al dominio energético
*/
Table users{
  id integer [primary key]
  username varchar [unique]
  role Role [not null] //Rol(admin,analista,usuario)
  email varchar [unique]
  password varchar
  created_at timestamp //Fecha de creación
  updated_at timestamp //Fecha de actualizacion
}
/*
Tabla: audit_log
Función:
-tabla de auditoría 
-registra acciones realizadas por los usuarios
-garantiza trazabilidad y control
*/
Table audit_log{
  id bigint [primary key]
  action varchar
  action_date timestamp
  user_id integer [not null]//FK users
}
/*
Realciones entre tablas
*/
//Realción geográfica
//muchas regiones pertenecen a un país 
//si borra el país se borran regiones
Ref: region.country_id > country.id [delete: cascade]
//Realción empresarial
//muchas empresar pertenecen a un país
//si se borra país se borran empresas
Ref: company.country_id > country.id [delete: cascade]
//Relaciones centrales de la planta generadora
//cada planta pertenece a una empresa region y tipo
//borra empresa se borran plantas
Ref: power_plant.company_id > company.id [delete: cascade]
//borra region se borran plantas
Ref: power_plant.region_id > region.id [delete: cascade]
//borra region se borran plantas
Ref: power_plant.energy_type_id > energy_type.id [delete: cascade]

//Realcion transaccional:
//muchos registro energéticos pertenecen 
//a una planta y a un tipo de medicion
Ref: energy_record.power_plant_id > power_plant.id
Ref: energy_record.measurement_type_id > measurement_type.id
//Relación de auditoría
//un usuario puede generar muchos registros de auditoria
Ref: audit_log.user_id > users.id