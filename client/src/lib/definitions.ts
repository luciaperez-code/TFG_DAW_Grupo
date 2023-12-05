export type Film = {
    idFilm: number,
    title: string,
    released: Date,
    runtime: string,
    genre: string | 'Cartoon',
    director: string,
    writer: string,
    actors: string,
    plot: string,
    language: string,
    country: string,
    awards: string,
    score: string,
    comingsoon: boolean,
    images: string | null
}

export type Screen = {
    idScreen: number,
    screenType: "2D" | "3D",
    normalSeats: string,
    specialSeats : string
}

export type Projection = {
    idProjection : number,
    startDate: string,
    endDate: string,
    price: number,
    occupiedNormalSeats: string,
    occupiedSpecialSeats: string,
    film: Film,
    screen: Screen
}

export type Card = {
    idCard: number,
    number: number,
    holderName: string,
    expirationDate: string,
    cvv: number
}

export type User = {
    idUser: number,
    name: string,
    surname: string,
    birthDate: string,
    email: string,
    password: string,
    enabled: number,
    registerDate: string
    cards: Card[]
}

export type Consumable = {
    idProduct: number,
    description: string,
    image: string | null,
    name : string,
    price: number,
    productType: {
        idProductType: number,
        name: string
    },
    stock: number
}


export type Store = {
    loggedUser? : User,
    cart: {
        films:
            {
                selectedSpecialSeats: number,
                selectedSeats: number,
                projection: Projection
            }[]
        
        consumables: {
            consumable: Consumable,
            cuantity: number
        }[],
        totalAmount: number
    }
}