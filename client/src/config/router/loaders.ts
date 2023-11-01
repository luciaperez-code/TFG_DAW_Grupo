import data from '../../utils/mocks.json'
export function getFilm({ params }: { params: { filmName: string } }) {
  const { films } = data

  const queryFilm = films.filter(film => film.Title.split(' ').join('-') === params.filmName)

  return queryFilm[0]
}