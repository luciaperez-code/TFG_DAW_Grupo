import Router from "config/router/router"
import { Store } from "lib/definitions"
import { GlobalStore } from "lib/global-store"
import { useEffect, useState } from "react"
import { BrowserRouter } from "react-router-dom"


function App() {

  const [store, setStore] = useState<Store>({
    cart:{
      consumables: [],
      films: [],
      totalAmount: 0
    }
  })

  function handleStore(key: string, value: any){
    setStore({...store, [key]: value})
  }

useEffect(() => {
  console.log({store})
}, [store]
)

  return (
    <BrowserRouter>
      <GlobalStore.Provider value={{store, handleStore}}>

      <Router />
      </GlobalStore.Provider>
    </BrowserRouter>
  )
}

export default App
