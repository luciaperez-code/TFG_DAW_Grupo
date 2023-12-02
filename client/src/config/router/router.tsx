import { FilmDetail, Films } from "pages";
import { Route, Routes } from "react-router-dom";

export default function Router() {
  return (
    <Routes>
      <Route path="/peliculas" element={<Films />}></Route>
      <Route path="/peliculas/:id" element={<FilmDetail />}></Route>
    </Routes>
  )
}