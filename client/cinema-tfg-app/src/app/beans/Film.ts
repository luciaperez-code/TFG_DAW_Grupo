export class Film {
    idFilm : number;
    title : string;
    year : number;
    released : string;
    runtime : string;
    genre : string;
    director : string;
    writer : string;
    actors : string;
    plot : string;
    language : string;
    country : string;
    awards : string;
    score : number;
    comingSoon : boolean;
    images : string;

    constructor(data : any) {
        this.idFilm = data.idFilm;
        this.title = data.title;
        this.year = data.year;
        this.released = data.released;
        this.runtime = data.runtime;
        this.genre = data.genre;
        this.director = data.director;
        this.writer = data.writer;
        this.actors = data.actors || null;
        this.plot = data.plot;
        this.language = data.language;
        this.country = data.country;
        this.awards = data.awards || null; // Si viene, se setea a null
        this.score = data.score || null; 
        this.comingSoon = data.comingSoon || false; 
        this.images = data.images || null; 
    }


}