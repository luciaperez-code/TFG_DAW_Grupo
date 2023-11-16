# SERVIDOR DE LA APLICACION DE CINES

## Como iniciar la aplicación

Para ejecutar esta parte del proyecto, será necesario un IDE con una extensión de Spring.
Después, arrancamos el proyecto y se inicializará la aplicación en el puerto 8087.


## Organización de la parte servidor

### Archivos de configuración
Este proyecto está construido en Java con Spring Boot, por lo que el archivo de configuración es application.properties, ubicado en la carpeta resources.
Además, se utiliza Maven para inyectar las dependencias por lo que tenemos el archivo pom.xml en la raíz del proyecto.

### Organización del código
Esencialmente, el código está organizado de la siguiente manera:
```
server/
├── src/
|   ├── main/
|   |   ├──dtos
|   |   ├──entities
|   |   ├──respository
|   |   ├──restcontroller
|   |   ├──service
|   ├── resources/
├── README.md
├── pom.xml

```
- `dtos`: Objeto para transportar datos de forma más sencilla.
- `entities`: Objetos a partir de Entidades de Base de datos.
- `repository`: Interfaces que extienden de JPA para conectase a base de datos.
- `restcontroller`: Clases para recibir y responder peticiones tipo REST.
- `service`: Clases encargadas de manejar los datos obtenidos por el respositorio.
