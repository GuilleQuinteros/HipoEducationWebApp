# HipoEducationWebApp

## Descripción
HipoEducationWebApp es una aplicación web educativa diseñada para mejorar la experiencia de aprendizaje de los estudiantes. Proporciona una plataforma interactiva y fácil de usar donde los estudiantes pueden acceder a recursos educativos, realizar ejercicios y seguir su progreso.

## Características
- Plataforma interactiva para estudiantes.
- Acceso a una variedad de recursos educativos.
- Ejercicios y pruebas para evaluar el progreso.
- Seguimiento del rendimiento de los estudiantes.

## Tecnologías Utilizadas
- Java
- Spring Boot
- MySQL
- HTML/CSS/JavaScript

## Instalación
### Prerrequisitos
- Java 11 o superior
- Maven
- MySQL

### Pasos de Instalación
1. Clona el repositorio:
    ```bash
    git clone https://github.com/GuilleQuinteros/HipoEducationWebApp.git
    ```
2. Navega al directorio del proyecto:
    ```bash
    cd HipoEducationWebApp
    ```
3. Configura la base de datos MySQL:
    - Crea una base de datos llamada `hipoeducation`.
    - Actualiza las credenciales de la base de datos en el archivo `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/hipoeducation
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña
        ```
4. Compila y ejecuta la aplicación:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Uso
1. Abre tu navegador y visita `http://localhost:8080` para acceder a la aplicación.
2. Regístrate o inicia sesión para comenzar a usar la plataforma.

## Contribuir
1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-feature`).
3. Realiza tus cambios y commitea (`git commit -am 'Agrega nueva feature'`).
4. Envía tus cambios (`git push origin feature/nueva-feature`).
5. Crea un nuevo Pull Request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT.

## Contacto
Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto conmigo a través de [mi perfil de GitHub](https://github.com/GuilleQuinteros).
