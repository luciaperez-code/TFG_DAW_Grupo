// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  
    //Cards
    GET_CARDS: 'http://localhost:8087/cards/all',
    ADD_CARD: 'http://localhost:8087/cards/add-card',
    DELETE_CARD: 'http://localhost:8087/cards/delete-card/',

    //Films
    GET_FILMS: 'http://localhost:8087/films/all',
    GET_RELEASED_FILMS: 'http://localhost:8087/films/allReleasedFilms',
    GET_FILMS_BY_ID: 'http://localhost:8087/films/',
    SEARCH_FILM: 'http://localhost:8087/films/',

    //Order
    GET_ORDERS: 'http://localhost:8087/orders/all',
    GET_ORDER_BY_ID: 'http://localhost:8087/orders/details/',

    //Product
    GET_PRODUCTS: 'http://localhost:8087/products/all',
    GET_PRODUCT_BY_ID: 'http://localhost:8087/products/',
    GET_PRODUCTS_BY_TYPE: 'http://localhost:8087/products/type/',
    GET_PRODUCTS_BY_RELATED_FILM: 'http://localhost:8087/products/relatedFilm/',
    GET_PRODUCT_BY_NAME: 'http://localhost:8087/products/search?nombre=',
    GET_PRODUCTS_PRICE_ASC: 'http://localhost:8087/products/list-products/priceAsc',
    GET_PRODUCTS_PRICE_DESC: 'http://localhost:8087/products/list-products/priceDesc',

    //ProductType
    GET_PRODUCTTYPES: 'http://localhost:8087/producttype/all',
    GET_PRODUCTTYPE_BY_ID: 'http://localhost:8087/producttype/',

    //Projection
    GET_PROJECTIONS: 'http://localhost:8087/projections/all',
    GET_PROJECTION_BY_ID: 'http://localhost:8087/projections/',
    GET_PROJECTION_BY_FILMID: 'http://localhost:8087/projections/idFilm/',
    GET_PROJECTION_BY_DATE: 'http://localhost:8087/projections/date/',

    //Screen
    GET_SCREENS: 'http://localhost:8087/screens/all',
    GET_SCREEN_BY_ID: 'http://localhost:8087/screens/',
    GET_SCREEN_TYPE: 'http://localhost:8087/screens/type?type=',

    //Users
    //GET_USERS: 'http://localhost:8087/users/all',
    //GET_USERS_BY_EMAIL: 'http://localhost:8087/users/email/',
    GET_USER: 'http://localhost:8087/users/detail', 
    REGISTER_USER: 'http://localhost:8087/users/register',
    EDIT_USER: 'http://localhost:8087/users/edit-user/',

    //Cesta
    GET_SHOPPING_CART: 'http://localhost:8087/shopping/getCesta', 
    ADD_SHOPPING_CART: 'http://localhost:8087/shopping/addBasket', 
    BUY: 'http://localhost:8087/shopping/buy',

    //Login
    LOGIN: 'http://localhost:8087/login', //Importante, sólo funciona si el user tiene la contraseña encriptada

};
