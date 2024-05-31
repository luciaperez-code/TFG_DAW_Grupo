# ENDPOINTS SERVIDOR -> RestController

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
	
### FILM ("/films")
- /all
    (GET) http://localhost:8087/films/all
- /allReleasedFilms
	(GET) http://localhost:8087/films/allReleasedFilms
- /id
    (GET) http://localhost:8087/films/1
- /add-film @RequestBody Film Film
    (POST) http://localhost:8087/films/add-film
		{
            "title": "El reino del planeta de los simios",
            "year": 2024,
            "rated": "PC",
            "released": "09-04-2024",
            "runtime": "145 min",
            "genre": "Ciencia ficción",
            "director": "Wes Ball",
            "writer": "null",
            "actors": "Kevin Durand, Freya Allan, Peter Macon, Owen Teague, William H. Macy",
            "plot": "Ambientada varias generaciones en el futuro tras el reinado de César, en la que los simios son la especie dominante que vive en armonía y los humanos se han visto reducidos a vivir en la sombra. Mientras un nuevo y tiránico líder simio construye su imperio, un joven simio emprende un angustioso viaje que le llevará a cuestionarse todo lo que sabe sobre el pasado y a tomar decisiones que definirán el futuro de simios y humanos por igual.",
            "language": "Español",
            "country": "Estados Unidos",
            "score": 0.0,
            "comingsoon": false,
            "images": "Reino-planeta-de-simios.jpg"
        }
- /delete-film/{idFilm}
	(GET) http://localhost:8087/films/delete-film/1
- /edit-film/{idFilm} @RequestBody Film Film
	(POST) http://localhost:8087/films/edit-film/1
- /search @RequestParam("title")
    (GET) http://localhost:8087/films/search?title=El Rey León


### ORDER ("/orders")
- /all
- /allOrders -> ADMIN
- /user/{idUser} -> ADMIN
- /details/{id} @PathVariable("idPedido")


### PRODUCT ("/products")
- /all
    (GET) http://localhost:8087/products/all
- /{id} @PathVariable("idProducto") 
    (GET) http://localhost:8087/products/1
- /type/{idProductType}
    (GET) http://localhost:8087/products/type/1
- /relatedFilm/{idFilm}
    (GET) http://localhost:8087/products/relatedFilm/2
- /add-product @RequestBody producto
    (POST) http://localhost:8087/products/add-product
        {
            "name": "Palomitas pequeñas",
            "image": "Palomitas_pequeña",
            "description": "Riquísimas palomitas saladas",
            "price": 4.5,
            "stock": 187,
            "productType": {
                "idProductType": 1,
                "name": "Comida"
            },
            "idFilm": null
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
- /add-productType @RequestBody producttype
    (POST) http://localhost:8087/producttype/add-productType
    {
        "idProductType": 2,
        "name": "Merchandising"
    }
- /edit-productType/{id} @RequestBody ProductType productType
	(POST) http://localhost:8087/producttype/edit-productType/3
	{
        "idProductType": 3,
        "name": "Gominolas"
    }
- /delete-productType/{id}
	(GET) http://localhost:8087/producttype/delete-productType/3


### PROJECTION ("/projections")
- /all
    (GET) http://localhost:8087/projections/all
- /{id} @PathVariable("idProjection")
    (GET) http://localhost:8087/projections/1
- /idFilm/{id} @PathVariable("idFilm")
	(GET) http://localhost:8087/projections/idFilm/1
- /date/{date} @PathVariable("date")
    (GET) http://localhost:8087/projections/date/2024-05-25
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
- /delete-projection/{id} @PathVariable(idProjection)
    (GET) http://localhost:8087/projections/delete-projection/2
- /edit-projection/{id} @RequestBody Projection, @PathVariable(idProjection)
    (POST) http://localhost:8087/projections/edit-projection/2
    {
		"date" : "21-05-2024",
        "startTime": "18:00",
        "endTime": "20:00",
        "price": 1.50,
        "film": 
        {
            "idFilm": 2
        },
        "screen":
        {
        "idScreen": 1
        }
    }


### SCREEN ("/screens")
- all
    (GET) http://localhost:8087/screens/all
- /{id} @PathVariable("idScreen")
    (GET) http://localhost:8087/screens/1
- /type @RequestParam("type") String type
    (GET) http://localhost:8087/screens/type?type=2D
- /add-screen @RequestBody screen
    (POST) http://localhost:8087/screens/add-screen
    {
    "screenType": "2D",
    "normalSeats": 50,
    "specialSeats": 8
    }
- /delete-screen/{idScreen}
	(GET) http://localhost:8087/screens/delete-screen/3
- /edit-screen @RequestBody screen
    (POST) http://localhost:8087/screens/edit-screen/{idScreen}
    {
    "screenType": "2D",
    "normalSeats": 60,
    "specialSeats": 6
    }


### USER ("/users")
- /{id} @PathVariable("idUser")
    (GET) http://localhost:8087/users/1
- /email/{email} @PathVariable("email")
    (GET) http://localhost:8087/users/email/bobesponja@gmail.com
- /detail -> authenticated
	(GET) http://localhost:8087/users/detail
- /register @RequestBody user
    (POST) http://localhost:8087/users/register
    {
    "name": "Bob",
    "surname": "Esponja",
    "birthDate": "2010-09-09",
    "email": "bobesponja@gmail.com",
    "password": "1234"
    }
- /delete-user/{idUser}
	(GET) http://localhost:8087/users/delete-user/10
- /edit-user/{id} @PathVariable ("idUser")
    (POST) http://localhost:8087/users/edit-user/4
    {
        "name": "Patricio",
        "surname": "Estrella",
        "birthDate": "2010-09-09",
        "email": "patricio@gmail.com",
        "password": "1234"
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


### CESTA ("/shopping")
- getCesta 
- /addBasket" @RequestBody lista
	
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



