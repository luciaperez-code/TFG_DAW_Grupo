import { Button, Center, Grid, Group, Paper, SimpleGrid, Space, Title, UnstyledButton } from "@mantine/core";
import { IconArmchair2, IconArmchair2Off, IconSofa, IconSofaOff } from "@tabler/icons-react";
import { Projection } from "lib/definitions";
import { useState } from "react";
import { useLocation } from "react-router-dom";
import Pill from "ui/Pill";

export default function SeatSelelection(){
    const projection: Projection =  useLocation().state
    const occupiedSpecialSeats = JSON.parse(projection.occupiedSpecialSeats)
    const occupiedNormalSeats = JSON.parse(projection.occupiedNormalSeats)

    const [selectedSeats, setSelectedSeats] = useState<number[]>([])
    const [selectedSpecialSeats, setSelectedSpecialSeats] = useState<number[]>([])

    console.log({projection})

    function handleSelection(index: number){
        if (selectedSeats.includes(index)) {
            setSelectedSeats(selectedSeats.filter(seat => seat != index))
        } else {
            setSelectedSeats([...selectedSeats, index])
        }
    }
    function handleSelectionSpecial(index: number){
        if (selectedSpecialSeats.includes(index)) {
            setSelectedSpecialSeats(selectedSpecialSeats.filter(seat => seat != index))
        } else {
            setSelectedSpecialSeats([...selectedSpecialSeats, index])
        }
    }

    return (
        <>
        <Paper w={'70vw'} mx={'auto'} h={'10vh'} withBorder style={{backgroundColor: '#808080'}} radius={'xl'} mb={'7vh'}>
            <Center h={'100%'}>
                <Title c={'white'}>Pantalla</Title>
            </Center>
        </Paper>
        <Group position="right" w={'70vw'} mx={'auto'} my={'xl'}>
            <Paper  radius="md" p={'xl'} shadow="md">
                <Pill text={`${selectedSeats.length}`} title="Nº Asientos seleccionados"></Pill>
                <Pill text={`${(selectedSeats.length + selectedSpecialSeats.length) * projection.price}€`} title="Total"></Pill>
                </Paper>           
        </Group>
        <Grid  w={'70vw'} mx={'auto'} justify="center">
            {occupiedSpecialSeats.map((isOccupied:boolean, index:number) => 
                <Grid.Col span={1}key={index}>
                <UnstyledButton onClick={() => handleSelectionSpecial(index)} >
                {isOccupied 
                ? <IconSofaOff size={'3rem'} color="lightgrey" />
                :<IconSofa size={'3rem'} color={selectedSpecialSeats.includes(index) ? "limegreen" : "black"}/>
            }
                </UnstyledButton>
            </Grid.Col>  
            )}
        </Grid>
        <Grid w={'70vw'} mx={'auto'} justify="center">
            {occupiedNormalSeats.map((isOccupied:boolean, index:number) => 
                <Grid.Col span={1}key={index}>
                <UnstyledButton onClick={() => handleSelection(index)}>
                {isOccupied 
                ? <IconArmchair2Off size={'3rem'} color="lightgrey" />
                :<IconArmchair2 size={'3rem'} color={selectedSeats.includes(index) ? "limegreen" : "black"}/>
            }
                </UnstyledButton>
            </Grid.Col>  
            )}
        </Grid>
        <Group w={'70vw'} mt={'xl'} mx={'auto'} position="center">
            <Button size="lg" color="teal" radius={'lg'} mt={'xl'}>Comprar</Button>
        </Group>
        </>
    )
}