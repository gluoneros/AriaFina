# Gestión de Usuarios y Tareas - Ariafina

Bienvenido(a) al proyecto **Ariafina**, una aplicación web sencilla y funcional para gestionar usuarios y tareas. Este proyecto está desarrollado con **Spring Boot**, **Hibernate**, **Thymeleaf** y **Tailwind CSS**, y permite a los usuarios crear, editar, eliminar y listar tanto usuarios como tareas desde una interfaz amigable.

## Descripción del Proyecto

Ariafina es una aplicación CRUD (Create, Read, Update, Delete) diseñada para:
- **Gestionar Usuarios**: Registrar nuevos usuarios con información como nombre, fecha de nacimiento, estado (activo/inactivo), dependencia y perfil.
- **Gestionar Tareas**: Crear y administrar tareas, con la posibilidad de asignarlas a usuarios (funcionalidad en desarrollo).
- **Interfaz Intuitiva**: Navegar fácilmente entre secciones gracias a una barra de navegación y formularios estilizados.

El proyecto está pensado para ser una base extensible que puedes personalizar según tus necesidades.

## Características

- **Usuarios**:
  - Crear, editar y eliminar usuarios.
  - Listar todos los usuarios en una tabla con acciones rápidas.
- **Tareas**:
  - Crear, editar y eliminar tareas.
  - Listar tareas con botones de acción.
- **Navegación**: Barra superior con enlaces a Home, Usuarios, Tareas y Consulta.
- **Estilos**: Diseño limpio y moderno con Tailwind CSS.
- **Base de Datos**: Persistencia con Hibernate y soporte para cualquier base de datos compatible con JPA (predeterminado: H2 en memoria).

## Tecnologías Utilizadas

- **Backend**: Spring Boot 3.x, Hibernate (JPA)
- **Frontend**: Thymeleaf, Tailwind CSS (CDN)
- **Base de Datos**: H2 (en memoria por defecto, configurable)
- **Lenguaje**: Java 17+
- **Gestión de Dependencias**: Maven

## Requisitos Previos

Para instalar y ejecutar el proyecto, asegúrate de tener instalado:

- **Java 17** o superior (JDK).
- **Maven** (para gestionar dependencias y compilar el proyecto).
- Un IDE como **IntelliJ IDEA**, **Eclipse** o **VS Code** (opcional, pero recomendado).
- Conexión a internet (para descargar dependencias y el CDN de Tailwind CSS).

## Instalación y Ejecución

Sigue estos pasos para poner en marcha el proyecto en tu máquina local:

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/ariafina.git
cd ariafina

spring.datasource.url=jdbc:mysql://localhost:3306/ariafina
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

mvn clean install

mvn spring-boot:run

http://localhost:8080/
