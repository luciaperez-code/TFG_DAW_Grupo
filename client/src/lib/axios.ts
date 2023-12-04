import Axios from 'axios'
import { storage } from "./storage";

const API_URL = 'http://localhost:8087/'

const axios = Axios.create({
  baseURL: API_URL,
  // withCredentials: true,
  
  // headers: {
    //   "Content-Type": 'application/json'
    // }
  })
  

// Auth logic
axios.interceptors.request.use(
    (config) => {
       const token = storage.getAccessToken()
       if (token){
        config.headers.Authorization = `Bearer ${token}`
       }
        return config
    }
)

axios.interceptors.response.use(
    (response) => {
        if (response.data.token){
            storage.setAccessToken(response.data.token)
        }
        return response
    }
)

export { axios }