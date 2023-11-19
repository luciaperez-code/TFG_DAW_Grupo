# ENDPOINTS SERVIDOR -> RestController

### FILM ("/films")
- /all
    (GET) http://localhost:8087/films/all
- /id
    (GET) http://localhost:8087/films/1
- /add-film @RequestBody Film Film
    (POST) http://localhost:8087/films/add-film
    {
    "idFilm": 1,
    "title": "El Rey León",
    "year": 1994,
    "rated": "+3",
    "released": "01-01-1994",
    "runtime": "100min",
    "genre": "Cartoon",
    "director": "Rob Minkoff, Roger Allers",
    "writer": "Walt Disney",
    "actors": "Simba, Mufasa, Nala, Scar, Zazú",
    "plot": "Ambientada en la sabana africana donde ha nacido el futuro rey. Simba idolatra a su padre, el rey Mufasa, y se toma muy en serio su propio destino real. Pero en el reino no todos celebran la llegada del nuevo cachorro. Scar, el hermano de Mufasa y antiguo heredero al trono, tiene sus propios planes. La batalla por Pride Rock está asolada por la traición, la tragedia y el drama, y termina con el exilio de Simba. Con la ayuda de un curioso par de nuevos amigos, Simba tendrá que aprender a madurar y a recuperar lo que le pertenece por derecho.",
    "language": "Español",
    "country": "Estados Unidos",
    "awards": "",
    "score": 4.55,
    "comingsoon": false,
    "images": null
    }
- /search @RequestParam("title")
    (GET) http://localhost:8087/films/search?title=El Rey León


### ORDER ("/orders")
- /all
- /all/{id} @PathVariable("idUsuario") 
- /ver-pedido/{id} @PathVariable("idPedido")


### PRODUCT ("/products")
- /all
    (GET) http://localhost:8087/products/all
- /{id} @PathVariable("idProducto") 
    (GET) http://localhost:8087/products/1
- /verPrecio/{name} @PathVariable("name")
    (GET) http://localhost:8087/products/verPrecio/Palomitas
- /add-product @RequestBody producto
    (POST) http://localhost:8087/products/add-product
    {
        "name": "Bolsa de chuches",
        "image": null,
        "description": "Bolsa de chuches de 100g",
        "price": 1.50,
        "stock": 100,
        "productType": {
            "idProductType": 1,
            "name": "Comida"
        }
    }
- /delete-product/{id}" @PathVariable(idProducto)
    (GET) http://localhost:8087/products/delete-product/4
- /edit-product/{id}" @RequestBody producto, @PathVariable idProducto
    (POST) http://localhost:8087/products/edit-product/4
    {
        "name": "Bolsa de chuches",
        "image": null,
        "description": "Bolsa de chuches de 100g",
        "price": 2.00,
        "stock": 10,
        "productType": {
            "idProductType": 1,
            "name": "Comida"
        }
    }
- /search @RequestParam("nombre")
    (GET) http://localhost:8087/products/search?nombre=Toblerone
- /list-products/priceAsc
    (GET) http://localhost:8087/products/list-products/priceAsc
- /list-products/priceDesc
    (GET)  http://localhost:8087/products/list-products/priceDesc


### PRODUCTTYPE ("/producttype")
- /all
    (GET) http://localhost:8087/producttype/all
- /{id} @PathVariable("idProductType")
    (GET) http://localhost:8087/producttype/1
- /add @RequestBody producttype
    (POST) http://localhost:8087/producttype/add
    {
        "idProductType": 2,
        "name": "Merchandising"
    }


### PROJECTION ("/projections")
- all
    (GET) http://localhost:8087/projections/all
- /{id} @PathVariable("idProjection")
    (GET) http://localhost:8087/projections/1
- /verPrecio/{name} @PathVariable("name")
    (GET) http://localhost:8087/projections/verPrecio/El Rey León
- /add-Projection (@RequestBody Projection)
    (POST) http://localhost:8087/projections/add-projection
    {
        "idProjection": 1,
        "startDate": "01-11-2023 18:00",
        "endDate": "31-12-2023 00:00",
        "price": 7.50,
        "occupiedNormalSeats":  "",
        "occupiedSpecialSeats": "",
        "film": 
        {
            "idFilm": 1,
            "title": "El Rey León",
            "year": 1994,
            "released": "31-12-1993",
            "runtime": "100min",
            "genre": "Cartoon",
            "director": "Rob Minkoff, Roger Allers",
            "writer": "Walt Disney",
            "actors": "Simba, Mufasa, Nala, Scar, Zazú",
            "plot": "Ambientada en la sabana africana donde ha nacido el futuro rey. Simba idolatra a su padre, el rey Mufasa, y se toma muy en serio su propio destino real. Pero en el reino no todos celebran la llegada del nuevo cachorro. Scar, el hermano de Mufasa y antiguo heredero al trono, tiene sus propios planes. La batalla por Pride Rock está asolada por la traición, la tragedia y el drama, y termina con el exilio de Simba. Con la ayuda de un curioso par de nuevos amigos, Simba tendrá que aprender a madurar y a recuperar lo que le pertenece por derecho.",
            "language": "Español",
            "country": "Estados Unidos",
            "awards": "",
            "score": 4.55,
            "comingsoon": false,
            "images": null
        },
        "screen":
        {
        "idScreen": 1,
        "screenType": "2D",
        "normalSeats": "50",
        "specialSeats": "8"
        }
    }

    {
        "startDate": "01-11-2023 18:00",
        "endDate": "31-12-2023 00:00",
        "price": 7.50,
        "occupiedNormalSeats":  "",
        "occupiedSpecialSeats": "",
        "film": 
        {
            "idFilm": 1
        },
        "screen":
        {
        "idScreen": 2
        }
    }
- /delete/{id} @PathVariable(idProjection)
- /edit/{id} @RequestBody Projection, @PathVariable(idProjection)
    (POST) http://localhost:8087/projections/edit/2
    {
        "startDate": "01-11-2023 18:00",
        "endDate": "31-12-2023 00:00",
        "price": 9.50,
        "occupiedNormalSeats":  "",
        "occupiedSpecialSeats": "",
        "film": 
        {
            "idFilm": 1
        },
        "screen":
        {
        "idScreen": 2
        }
    }
- /search" @RequestParam("nombre")
    (GET) http://localhost:8087/projections/search?nombre=El Rey León
- /list-projections/priceAsc
    (GET) http://localhost:8087/projections/list-projections/priceAsc
- /list-projections/priceDesc
    (GET) http://localhost:8087/projections/list-projections/priceDesc


### SCREEN ("/screens")
- all
    (GET) http://localhost:8087/screens/all
- /{id} @PathVariable("idScreen")
    (GET) http://localhost:8087/screens/1
- /type @RequestParam("type") String type
    (GET) http://localhost:8087/screens/type?type=2D
- /type/3D
    (GET) http://localhost:8087/screens/type/3D
- /add @RequestBody screen
    (POST) http://localhost:8087/screens/add
    {
    "idScreen" : 1,
    "screenType": "2D",
    "normalSeats": 50,
    "specialSeats": 8
    }

### USER ("/users")
- all
    (GET) http://localhost:8087/users/all
- /{id} @PathVariable("idUser")
    (GET) http://localhost:8087/users/1
- /register @RequestBody user
    (POST) http://localhost:8087/users/register
    {
    "name": "Bob",
    "surname": "Esponja",
    "birthDate": "2010-09-09",
    "email": "bobesponja@gmail.com",
    "password": "1234"
    }
- /edit-user/{id} @PathVariable ("idUser")
    (POST) http://localhost:8087/users/edit-user/4
    {
        "name": "Patricio",
        "surname": "Estrella",
        "birthDate": "2010-09-09",
        "email": "patricio@gmail.com",
        "password": "1234"
    }

- /lista-tarjetas/{id} @PathVariable ("idUser")
    (GET) http://localhost:8087/users/lista-tarjetas/3
- /login


### CESTA ("/shopping")
- getCesta @RequestBody user
- /addCesta" @RequestBody lista, user
- /buy" @RequestBody lista, card, user


### CARDS
Para ejecutar esta parte del proyecto, será necesario un IDE con una extensión de Spring.
Después, arrancamos el proyecto y se inicializará la aplicación en el puerto 8087.