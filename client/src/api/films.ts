import { useQuery } from "@tanstack/react-query"
import { axios } from "lib/axios"
import { Film } from "lib/definitions"

export const useGetAllFilms = () =>
  useQuery({
    queryKey: ['films'],
    queryFn: async () => {
      const response = await axios.get('films/all')
      const data: Film[] = response.data
      return data
    }
  })


export const useGetFilm = (idFilm: Film['idFilm']) => 
  useQuery({
    queryKey: ['films', idFilm],
    queryFn: async () => {
      const response = await axios.get(`films/${idFilm}`)
      const data: Film = response.data
      return data
    }
  })