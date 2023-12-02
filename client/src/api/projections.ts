import { useQuery } from "@tanstack/react-query";
import { axios } from "lib/axios";
import { Film, Projection } from "lib/definitions";

export const useGetProjectionsByFilm = (filmTitle: Film['title']) => 
    useQuery({
        queryKey: ['projections', filmTitle],
        queryFn: async () => {
            const response = await axios.get(`/projections/search?nombre=${filmTitle}`)
            const data: Projection[] = response.data
            return data
        }
    })