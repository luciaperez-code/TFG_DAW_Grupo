# Docker

## Instalando Docker
__Si ya tienes docker instalado, este paso no es necesario__

---
Para poder lanzar el contenedor de Docker, es preciso tenerlo instalado. Si estás usando Linux o WSL en Windows, [esta guía puede ser de utilidad](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04). Es muy recomendable hacerlo en un entorno linux, pero, en caso de realizar la instalación puramente en windows (no en WSL) [esta es la documentación oficial](https://docs.docker.com/desktop/install/windows-install/).

## Como lanzar la base de datos de la aplicación

Es necesario tener el daemon (programa que se ejecuta en segundo plano) corriendo. [(documentacion)](https://docs.docker.com/config/daemon/start/).

`sudo dockerd`

Con el daemon corriendo, hay que construir la imagen. Para ello, desde una terminal en el directorio de la aplicación (al mismo nivel que el archivo dockerfile del proyecto), ejecutamos: 

`docker build -t nombre_de_tu_imagen .`

Donde nombre_de_tu_imagen es lo que tú quieras (en mi caso, fp_database) y `.` haciendo referencia al único archivo de docker existente en el directorio en el que se ejecuta el comando.

Una vez construida la imagen, podremos verla con `docker images`

Con la imagen creada, es hora de lanzarla, para ello, se ejecuta el siguiente comando: 

`docker run --name nombre_del_contenedor -d nombre_de_tu_imagen`

El nombre del contenedor será donde realmente se esté ejecutando la base de datos, creada a partir de la imagen que hemos generado anteriormente.

Podemos comprobar que está correctamente lanzado con el comando `docker ps`

Si se desea ver la base de datos con alguna GUI como [beekeeper](https://github.com/beekeeper-studio/beekeeper-studio) o el de MySQL, es necesario la IP del contenedor. Para eso puede ser de ayuda [la primera respuesta de este pregunta en stackOverflow](https://stackoverflow.com/questions/17157721/how-to-get-a-docker-containers-ip-address-from-the-host)

En `application properties` también debe aparecer esa IP, ya que nos estamos conectando a un sistema distribuido y no es una base de datos local.

## Comprobar que está todo correcto

Si todo ha salido bien, al inicial el servidor y dirigirse a http://localhost:8087/films/all nos debería devolver un `JSON` con el _El Rey León_