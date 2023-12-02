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
    image: string | null
}