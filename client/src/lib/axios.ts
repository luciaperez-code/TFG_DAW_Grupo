import Axios from 'axios'

const API_URL = 'http://localhost:8087/'

const axios = Axios.create({
  baseURL: API_URL,
  withCredentials: false,
  // headers: {
  //   "Content-Type": 'application/json'
  // }
})

export { axios }