import { FilmDetail, Films } from "pages";
import Consumables from "pages/Consumables";
import SeatSelelection from "pages/SeatSelection";
import { Route, Routes } from "react-router-dom";
import NavBar from "ui/NavBar";

export default function Router() {
  return (
    <Routes>
      <Route element={<NavBar />}>

      <Route path="/peliculas" element={<Films />}></Route>
      <Route path="/peliculas/:id" element={<FilmDetail />}></Route>
      <Route path="/asientos" element={<SeatSelelection />}></Route>
      <Route path="/alimentacion" element={<Consumables />}></Route>
      </Route>
    </Routes>
  )
}