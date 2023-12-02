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