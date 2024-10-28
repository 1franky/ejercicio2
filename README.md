# API REST - Gestión de Alumnos y Estados  - Ejercicio 2

## Integrantes
- **Francisco Miztli López Salinas**
- **Leonardo Aguirre Ramírez**
- **Carlos Andrés Rodríguez**

---

## Descripción del Proyecto
Este proyecto implementa una API REST para gestionar información de `alumnos` y `estados`, Se se hizo uso de `DTOs` y `ModelMapper`para facilitar la conversion de objetos. La API está construida utilizando Spring Boot y proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la entidad Alumno y Estado.

---

## Modelo de Datos
La entidad principal del modelo `Alumno` con los siguientes atributos:

```java
public class Alumno {
    private String matricula;
    private String nombre;
    private String paterno;
    private Date fnac;
    private Double estatura;
    private Estado estado;
    ....
    Demas atributos
    Getters y
    Setters....
}
```

`AlumnoDto` 
```java
public class AlumnoDto {
    private String matricula;
    private String nombre;
    private String paterno;
    private String fnac;
    private double estatura;
    private String estado;
}
```

La entidad principal del modelo `Estado` con los siguientes atributos:

```java
public class Estado {
    private int idEstado;
    private String estado;
    private String abreviatura;
    private Set<Alumno> alumnos = new HashSet<>();
    ....
    Demas atributos
    Getters y
    Setters....
}
```

`EstadoDto`
```java
public class EstadoDto {
    private int idEstado;
    private String estado;
    private String abreviatura;
}
```

---

## API Endpoints
Todos los endpoints están bajo la URL base: `http://localhost:8080/api/`

## Ejecución del Proyecto
Para ejecutar el proyecto, asegúrate de tener instalado Java y Maven. Luego, sigue estos pasos:

1. Clona el repositorio
2. Navega al directorio del proyecto
3. Ejecuta `mvn spring-boot:run`
4. La API estará disponible en `http://localhost:8080/api/`

---

