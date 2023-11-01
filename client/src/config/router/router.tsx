import { NotFound, Films, FilmDetail } from "pages";
import { createBrowserRouter } from "react-router-dom";
import { getFilm } from "./loaders";
// import { getFilm } from "./loaders";

const router = createBrowserRouter([
  {
    path: "peliculas",
    element: <Films />
  },
  {
    path: "peliculas/:filmName",
    element: <FilmDetail />,
    loader: (data) => getFilm(data)

  },
  {
    path: "*",
    element: <NotFound />
  }
])

export { router }