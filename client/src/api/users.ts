import { useMutation, useQueries, useQuery } from "@tanstack/react-query";
import { axios } from "lib/axios";
import { User } from "lib/definitions";

export const useLogin = () =>
    useMutation({
        mutationFn: async (payload: {email: User['email'], password: User['password']}) => {
            const response = await axios.post('/login', payload)
            const data = response
            return data
        }
    })


export const useGetUsers = () => 
    useQuery({
        queryKey:['users'],
        queryFn: async () => {
            const response = await axios.get('/users/all')
            const data: User[] = response.data
            return data
        }

    })