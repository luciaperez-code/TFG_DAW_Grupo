import { useQuery } from "@tanstack/react-query";
import { axios } from "lib/axios";
import { Consumable } from "lib/definitions";

export const useGetAllProducts = () =>
    useQuery({
        queryKey:['consumables'],
        queryFn: async () => {
            const repsonse = await axios.get('/products/all')
            const data: Consumable[] = repsonse.data
            return data
        }

    })