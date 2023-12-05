import { createContext, useContext } from "react";
import { Store } from "./definitions";

type _Store = {
    store: Store,
    handleStore: (key: string, value: any) => void
}
export const GlobalStore = createContext<_Store | null>(null)

export const useGlobalStore = () => {
    const ctx = useContext(GlobalStore)

    if (!ctx) throw new Error() //Will never be the case, but this avoids null case in the rest of components

    return ctx
}