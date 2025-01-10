# Desafío LiterAlura

**Objetivo:** Crear una aplicación de consola en Java, utilizando Spring y PostgreSQL, que permita a los usuarios buscar libros en una API externa (Gutendex), registrarlos en una base de datos y realizar diversas operaciones sobre los datos almacenados.

**Funcionalidades principales:**
- **Búsqueda de libros:** El usuario ingresa el título de un libro y la aplicación lo busca en la API Gutendex. Si se encuentra, se registra en la base de datos con información como título, autor, idioma y número de descargas.
- **Listado de libros:** Permite listar todos los libros registrados, los autores, los autores vivos en un año específico y los libros por idioma.
- **Manejo de errores:** La aplicación debe manejar casos en los que el libro no se encuentre en la API o si el usuario intenta registrar el mismo libro dos veces.

**Tecnologías y herramientas:**
- **Java:** Lenguaje de programación principal.
- **Spring:** Framework para desarrollo de aplicaciones Java.
- **Spring Data JPA:** Para interactuar con la base de datos PostgreSQL.
- **Gutendex API:** API externa para obtener información sobre libros.
- **Trello:** Herramienta de gestión de proyectos para organizar las tareas del desafío.

**Pasos iniciales:**
1. **Crear un proyecto Spring Boot:** Utilizar Spring Initializer para generar un nuevo proyecto con las dependencias necesarias (Spring Data JPA y PostgreSQL Driver).
2. **Consumir la API Gutendex:** Utilizar una librería HTTP (como RestTemplate o Retrofit) para hacer las llamadas a la API y obtener los datos de los libros.
3. **Crear entidades:** Modelar las entidades (Libro, Autor) que representarán los datos en la base de datos.
4. **Implementar los servicios:** Crear los servicios que encapsularán la lógica de negocio, como buscar libros, guardarlos en la base de datos y realizar las consultas.
5. **Crear la interfaz de usuario:** Desarrollar una sencilla interfaz de consola para permitir al usuario interactuar con la aplicación.

---
# Ejemplo de Uso

![[Pasted image 20250110082207.png]]

![[Pasted image 20250110082337.png]]

![[Pasted image 20250110082402.png]]

![[Pasted image 20250110082433.png]]

![[Pasted image 20250110082639.png]]

---
# Clase `Menu`

La clase `Menu` es responsable de la interfaz principal de usuario para la aplicación de gestión de libros. Proporciona opciones para interactuar con las entidades de libros y autores almacenadas en la base de datos, y permite la integración con la API externa **Gutendex**.

---
## Dependencias

La clase utiliza los siguientes servicios para ejecutar su lógica:

- `BookService`: Gestiona las operaciones relacionadas con los libros.
- `AuthorService`: Gestiona las operaciones relacionadas con los autores.
- `ApiService`: Proporciona métodos para consumir la API externa **Gutendex**.

---
## Métodos Públicos

#### `void showMenu()`

Inicia un menú interactivo de consola que permite al usuario seleccionar entre las siguientes opciones:

1. Buscar un libro por título.
2. Listar todos los libros.
3. Listar todos los autores.
4. Listar autores vivos en un año específico.
5. Listar libros por idioma.
6. Salir.

---
## Métodos Privados

#### `void searchBook()`

Permite buscar un libro por título.

1. Consulta primero la base de datos para evitar duplicados.
2. Si no existe, consulta la API **Gutendex**.
3. Si el libro es encontrado en la API:
    - Crea un registro del autor.
    - Guarda el libro en la base de datos.

#### `void listBooks()`

Lista todos los libros almacenados en la base de datos, mostrando únicamente los títulos.

#### `void listAuthors()`

Muestra todos los autores registrados, incluyendo:

- Nombre
- Año de nacimiento
- Año de fallecimiento (si aplica)

#### `void listAuthorsAliveInYear()`

Filtra y muestra los autores que estaban vivos en un año específico proporcionado por el usuario.

#### `void listBooksByLanguage()`

Lista los libros filtrados por el idioma especificado (código de idioma, como `en` o `es`).

#### `void printBookDetails(Book book)`

Muestra los detalles de un libro, incluyendo:

- Título
- Autor
- Idiomas disponibles

#### `void printAuthorDetails(Author author)`

Muestra los detalles de un autor, incluyendo:

- Nombre
- Año de nacimiento
- Año de fallecimiento (si aplica)
- Cantidad de libros asociados
## Lógica Interna del Menú
1. **Entrada del Usuario:**
    - Lee la entrada usando `Scanner` para capturar opciones y datos ingresados por el usuario.
    - Maneja entradas incorrectas con un mensaje de error.
2. **Persistencia:**
    - Depende de los servicios `BookService` y `AuthorService` para interactuar con la base de datos usando **Spring Data JPA**.
3. **Consumo de API:**
    - Utiliza `ApiService` para buscar información de libros en la API externa **Gutendex**.

---
## Dependencias Relacionadas

- `BookService`
- `AuthorService`
- `ApiService`
- Clases de entidad: `Book`, `Author`
