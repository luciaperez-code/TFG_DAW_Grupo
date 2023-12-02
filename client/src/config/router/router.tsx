import { FilmDetail, Films } from "pages";
import SeatSelelection from "pages/SeatSelection";
import { Route, Routes } from "react-router-dom";

export default function Router() {
  return (
    <Routes>
      <Route path="/peliculas" element={<Films />}></Route>
      <Route path="/peliculas/:id" element={<FilmDetail />}></Route>
      <Route path="/asientos" element={<SeatSelelection />}></Route>
    </Routes>
  )
}