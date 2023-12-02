import { Center, Grid, Paper, SimpleGrid, Space, Title, UnstyledButton } from "@mantine/core";
import { IconArmchair2, IconArmchair2Off, IconSofa, IconSofaOff } from "@tabler/icons-react";
import { Projection } from "lib/definitions";
import { useLocation } from "react-router-dom";

export default function SeatSelelection(){
    const projection: Projection =  useLocation().state
    const occupiedSpecialSeats = JSON.parse(projection.occupiedSpecialSeats)
    const occupiedNormalSeats = JSON.parse(projection.occupiedNormalSeats)

    console.log({projection})

    return (
        <>
        <Paper w={'70vw'} mx={'auto'} h={'10vh'} withBorder style={{backgroundColor: '#808080'}} radius={'xl'} mb={'7vh'}>
            <Center h={'100%'}>
                <Title c={'white'}>Pantalla</Title>
            </Center>
        </Paper>
        <Space h={'xl'}></Space>
        <Grid  w={'70vw'} mx={'auto'} justify="center">
            {occupiedSpecialSeats.map((isOccupied:boolean, index:number) => 
                <Grid.Col span={1}key={index}>
                <UnstyledButton >
                {isOccupied 
                ? <IconSofaOff size={'3rem'} />
                :<IconSofa size={'3rem'}/>
            }
                </UnstyledButton>
            </Grid.Col>  
            )}
        </Grid>
        <Grid w={'70vw'} mx={'auto'} justify="center">
            {occupiedNormalSeats.map((isOccupied:boolean, index:number) => 
                <Grid.Col span={1}key={index}>
                <UnstyledButton>
                {isOccupied 
                ? <IconArmchair2Off size={'3rem'} />
                :<IconArmchair2 size={'3rem'}/>
            }
                </UnstyledButton>
            </Grid.Col>  
            )}
        </Grid>
        </>
    )
}