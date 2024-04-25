export const environment = {
    production: false,

    //Films
    GET_FILMS : 'http://localhost:8087/films/all',
    GET_FILMS_BY_ID : 'http://localhost:8087/films/',
    ADD_FILM : 'http://localhost:8087/films/add-film',
    SEARCH_FILM : 'http://localhost:8087/films',
    DELETE_FILM : 'http://localhost:8087/products/delete-product/',
    EDIT_FILM : 'http://localhost:8087/products/edit-product/',

    //Order
    GET_ORDERS : 'http://localhost:8087/orders/all',
    GET_ORDERS_BY_USER: 'http://localhost:8087/orders/all/',
    GET_ORDER_BY_ID: 'http://localhost:8087/orders/',

    //Product
    GET_PRODUCT: 'http://localhost:8087/products/all',
    GET_PRODUCT_BY_ID : 'http://localhost:8087/products/',
    GET_PRICE_BY_NAME : 'http://localhost:8087/products/verPrecio/',
    GET_PRICE_BY_ID : 'http://localhost:8087/products/verPrecio/',
    ADD_PRODUCT : 'http://localhost:8087/products/add-product',
    DELETE_PRODUCT : 'http://localhost:8087/products/delete-product/',
    EDIT_PRODUCT : 'http://localhost:8087/products/edit-product/',
    GET_PRODUCT_BY_NAME : 'http://localhost:8087/products/search?nombre=',
    GET_PRODUCTS_PRICE_ASC : 'http://localhost:8087/products/list-products/priceAsc',
    GET_PRODUCTS_PRICE_DESC : 'http://localhost:8087/products/list-products/priceDesc',

    //ProductType
    GET_PRODUCTTYPES : 'http://localhost:8087/producttype/all',
    GET_PRODUCTTYPE_BY_ID : 'http://localhost:8087/producttype/',
    ADD_PRODUCTTYPE : 'http://localhost:8087/producttype/add',

    //Projection
    GET_PROJECTIONS : 'http://localhost:8087/projections/all',
    GET_PROJECTION_BY_ID: 'http://localhost:8087/projections/',
    GET_PROJECTION_PRICE : 'http://localhost:8087/projections/verPrecio/',
    ADD_PROJECTION : 'http://localhost:8087/projections/add-projection',
    DELETE_PROJECTION : 'http://localhost:8087/projections/delete/',
    EDIT_PROJECTION:'http://localhost:8087/projections/edit/',
    GET_PROJECTION_BY_NAME : 'http://localhost:8087/projections/list-projections/priceAsc',
    GET_PROJECTION_PRICE_ASC : 'http://localhost:8087/products/list-products/priceAsc',
    GET_PROJECTION_PRICE_DESC : 'http://localhost:8087/projections/list-projections/priceDes',

    //Screen
    GET_SCREENS : 'http://localhost:8087/screens/all',
    GET_SCREEN_BY_ID : 'http://localhost:8087/screens/',
    GET_SCREEN_TYPE : 'http://localhost:8087/screens/type?type=',
    GET_SCREENS_3D : 'http://localhost:8087/screens/type/',
    ADD_SCREEN : 'http://localhost:8087/screens/add',

    //Users
    //GET_USERS: 'http://localhost:8087/users/all',
    GET_USER_BY_ID : 'http://localhost:8087/users/', 
    REGISTER_USER: 'http://localhost:8087/users/register',
    EDIT_USER: 'http://localhost:8087/users/edit-user/',
    LIST_CARDS_BY_USER : 'http://localhost:8087/users/lista-tarjetas/',

    //Cesta
    GET_SHOPPING_CART : 'http://localhost:8087/shopping/getCesta', //WIP
    ADD_ITEM_SHOPPING_CART: 'http://localhost:8087/shopping/addCesta', //WIP
    BUY : 'http://localhost:8087/shopping/buy',

    //Cards
    GET_CARDS : 'http://localhost:8087/cards/all',
    ADD_CARD : 'http://localhost:8087/cards/add-card',
    DELETE_CARD : 'http://localhost:8087/cards/delete-card/',
    EDIT_CARD : 'http://localhost:8087/cards/edit-card/',

    //Login
    LOGIN : 'http://localhost:8087/login', //Importante, sólo funciona si el user tiene la contraseña encriptada

  };