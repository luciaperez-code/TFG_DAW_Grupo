# CLIENTE DE LA APLICACION DE CINES

## Como iniciar la aplicación

Para ejecutar esta parte del proyecto, es necesario tener instalado en el entorno Node v18+

Después, con cualquier terminal que se encuentre en el directorio `[raiz_del_proyecto]/client`, ejecutamos el comando `npm run dev`. Se inicializará la aplicación en su entorno de desarrollo, pudiendo acceder con el navegador en la URI http://localhost:5173/

El resto de comandos disponibles en la aplicación se encuentran disponibles en el archivo `package.json`, en `scripts`


## Organización de la parte cliente

### Archivos de configuración
El proyecto está escrito en Typescript. La razón principal de esta decisión técnica es la posibilidad de inferir tipado, así como su sintaxis, mucho mas amable que la de JSDoc.

Typescript necesitar compilarse en javascript. Para eso se utiliza Vite. Se ha decidido utilizar Vite por su facilidad de configuración, la experiencia de usuario y las ventajas que ofrece al compilar el código, minificando y haciendo _tree shacking_ por defecto.

Los archivos de configuración necesarios para llevar a cabo lo anterios son `tsconfig.node.json`, `tsconfig.json` y `vite.config.ts`.

Existe otro archivo de configuración, `.eslintrc.cjs`. Se emplea para configurar el linter, de manera que todo el código tenga consistencia semántica y de estilo.

### React como librería
Como cualquier otra aplicación web, todo se ejecuta en archivos `.html`.
En este caso, la aplicación es una _SPA_, por lo que existe un único html (`index.html`) en el que se carga un script que incluye toda la aplicación. Existen dos archivos principales que realizan esta tarea, ubicados en `/src/config/app-initalizer`:

   - `entrypoint.tsx`: Encargado de modificar el DOM para renderizar el script dentro de `index.html`
   - `initializer.tsx`: Primer nodo de REACT del cual depende el resto del proyecto. El es lugar en el que alojar información que se quiere a nivel global, como las rutas que tendrá la aplicación.

### Mantine como librería de componentes
Todas las aplicación tienes componentes esenciales, como botones, modales, cards, etc. Gente con más habilidad que nosotros se han esforzado en generar librerías de componentes para todos esos casos básicos. Hemos elegido `Mantine` por su fantástica experiencia de desarrollo, tener por defecto _type definitions_ en Typescript y dar la posibilidad de utilizar CSS Modules para estilarlos (además de la API propia que ofrecen sus componentes). Con estos bloques fundamentales (y reutilizables) nos abstraemos evitamos tener que pensar en problemas de accesibilidad y de utilizar las etiquetas semánticamente más adecuadas para cada situación. `Mantine` realiza todo eso por defecto.

### Organización del código
Esencialmente, el código está organizado de la siguiente manera:
```
client/
├── src/
|   ├── config/
|   |   ├──app-initializer
|   |   ├──routes
|   ├── pages/
|   ├── ui/
|   ├── utils/
├── README.md
```
- `pages`: Cada una de las vistas que tendrá la aplicación
- `ui`: Elementos reutilizables para generar las vistas.
- `utils`: Funciones y utilidades que no tienen entidad suficiente como para generar un módulo propio. También se emplea para alojar los _mocks_ mientras se construye la API.