import { NotFound, Films } from "pages";
import { createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Films />
  },
  {
    path: "*",
    element: <NotFound />
  }
])

export { router }