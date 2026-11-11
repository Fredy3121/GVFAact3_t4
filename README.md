# Actividad 3 - Tema 4: CRUD con Spring Boot, JPA y Base de Datos Relacional MySQL

Proyecto backend desarrollado con Spring Boot y Spring Data JPA que implementa un CRUD completo persistido en un servidor MySQL real, estableciendo una relación `@OneToMany` / `@ManyToOne` entre dos entidades del modelo de negocio (`Categoria` y `Producto`), desplegado de forma concurrente en un servidor VPS.

---

## 1. Tecnologías y Herramientas

- **Lenguaje:** Java 21
- **Framework:** Spring Boot (Spring Web, Spring Data JPA, DevTools)
- **ORM / Persistencia:** Hibernate  JPA
- **Motor de Base de Datos:** MySQL Server  8.x
- **Gestor de Dependencias:** Apache Maven
- **Cliente HTTP para Pruebas:** Postman 
- **Entorno de Despliegue:** VPS Ubuntu Linux (ejecutado con `nohup` en puerto 8085)

---

## 2. Modelo de Datos y Relación entre Entidades

Se implementaron dos entidades principales con una relación bidireccional:
- **`Categoria` (`@OneToMany`):** Entidad que agrupa bienes por clasificación (ej. Papelería, Tecnología). Contiene una colección de productos asociada.
- **`Producto` (`@ManyToOne`):** Entidad dependiente que contiene nombre, precio, stock y mantiene la llave foránea `categoria_id` (`@JoinColumn`) que la vincula forzosamente a una categoría.




## 3. Capturas de pantalla 

![proyecto corriendo de manera local](img/img1.png)

![proyecto corriendo el la vps](img/vps.png)

![consulta en postman](img/postman.png)
