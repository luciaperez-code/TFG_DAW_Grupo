import { LoadingOverlay, Paper, Title , Text, Group, Button} from "@mantine/core";
import { useGetAllProducts } from "api/products";
import { Consumable } from "lib/definitions";
import { useGlobalStore } from "lib/global-store";
import { useState } from "react";

export default function Consumables(){
    const {data, isLoading} = useGetAllProducts()

    const {store} = useGlobalStore()

   
   


    const [total, setTotal] = useState<number>( store.cart.totalAmount)

    if (isLoading) return <LoadingOverlay visible={true}></LoadingOverlay>

    if (!data) return <h1>Ha ocurrido un error</h1>

    function onAction(addition: number){
        setTotal(total + addition)
    }


    return (<>
    <Paper w={'70vw' } mt={'10vh'} mx={'auto'}>

    {data.map(product => {
        return (
           <ProductDisplay onAction={onAction} key={product.idProduct} product={product}  />
        )
    })}
    <Title>Precio total: {total}€</Title>
    </Paper>
    </>)
}

function ProductDisplay({product, onAction}: {product: Consumable, onAction: (number: number) => void}){
    const [amount, setAmount] = useState(0)

    function handleRemove() {
        setAmount(amount - 1)
        onAction(-(product.price))
    }

    function handleAddition(){
        setAmount(amount +1)
        onAction((product.price))
    }
    return (
        <Paper p={'xl'} my={'2vh'} key={product.idProduct} shadow="xl" radius={'xl'} >
        <Group position="apart">
            <div>
                <Title>{product.name}</Title>
                <Text c={'dimmed'}>{product.description}</Text>
                <Title order={3}>{product.price} €</Title>
            </div>
            {product.stock == 0 ? <h3>Sin Stock!</h3> : null}
            <Group>
                
                <>
                <Button onClick={handleRemove} disabled={amount == 0}>-</Button>
                <Paper p={'lg'}>{amount}</Paper>
                <Button onClick={handleAddition} disabled={amount == product.stock}>+</Button>
                </>
            </Group>
        </Group>
    </Paper>
    )
}