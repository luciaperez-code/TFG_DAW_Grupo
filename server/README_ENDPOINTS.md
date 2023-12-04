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
        "startDate": "29-11-2023 18:00",
        "endDate": "31-12-2023 00:00",
        "price": 6.50,
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
    (GET) http://localhost:8087/projections/delete/2
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
- getCesta 
- /addCesta" @RequestBody lista
- /buy" @RequestBody lista, card
    (POST) http://localhost:8087/shopping/buy

    Sólo proyección:
    [
        {
            "idProjection": 5,
            "occupiedNormalSeats": "[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
            "occupiedSpecialSeats": "[0, 1, 0, 0, 0, 0, 0, 0, 0, 0]",
            "idCard":1
        }
    ]

    Sólo producto:
    [
        {
            "idProduct": 1,
            "quantity": 4,
            "idCard":1
        }
    ]

    Ambas:
    [
        {
            "idProjection": 5,
            "occupiedNormalSeats": "[0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
            "occupiedSpecialSeats": "[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]",
            "idCard":1
        },
        {
            "idProduct": 1,
            "quantity": 4,
            "idCard":1
        }
    ]


### CARDS ("/cards")
- /all
    (GET) http://localhost:8087/cards/all
- /add-card
    (POST) http://localhost:8087/cards/add-card
    {
       "number": 5540500001000004,
       "holderName": "Lucía Pérez",
       "expirationDate": "31-12-2024",
       "cvv": 123
    }
- /delete-card/{id} @PathVariable("id") int idTarjeta
    (GET) http://localhost:8087/cards/delete-card/1
- /edit-card/{id}
    (POST) http://localhost:8087/cards/edit-card/1
	{
        "number": 5540500001000004,
        "holderName": "Yolanda Carrasco",
        "expirationDate": "31-12-2025",
        "cvv": 456
    }

### LOGIN
- /login
    (POST) http://localhost:8087/login
    {
    "email": "yoli@gmail.com",
    "password": "1234"
    }

    Respuesta: 
    {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5b2xpQGdtYWlsLmNvbSIsImV4cCI6MTcwMDUwODM0NiwiaWF0IjoxNzAwNDIxOTQ2fQ.AkoQAd8IExL2DdTmrxoIdAZrBzmNP9Hfyz6g-jpy2rN0zdm2HlLFtkbuTidUnJe-uQ8gzsi3zTTj_Gp5gn-isQ"
    }
    Importante: sólo funciona si el usuario tiene la contraseña encriptada
