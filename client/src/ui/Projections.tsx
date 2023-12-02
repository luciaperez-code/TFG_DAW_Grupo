import { Badge, Button, Card, Center, Divider, Group, Loader,  SimpleGrid, Stack, Title } from "@mantine/core";
import { useGetProjectionsByFilm } from "api/projections";
import { Film } from "lib/definitions";
import { PropsWithChildren } from "react";
import { getDate } from "utils/dates";
import Pill from "./Pill";
import { Link } from "react-router-dom";

type Props = PropsWithChildren & {
    title: Film['title']
}
export default function Projections({title}: Props){
    const {data: projections, isLoading} = useGetProjectionsByFilm(title)

    if(isLoading) return <Loader/>

    if (!projections) return <></>

    return (
       <>
       {projections.map(projection => 
        <SimpleGrid cols={4} key={projection.idProjection}>
        <Card shadow="lg" padding={'lg'} radius={'md'} withBorder>
            <Card.Section>
            <Center>
                <Stack align="center">
                <Title c={'cyan'} mt={'xl'}>{getDate(projection.startDate.split(" ")[0]).textDayOfWeek}</Title>
                <Title order={2}>{projection.startDate.split(" ")[1]}</Title>
                {projection.screen.screenType == '3D' ? <Badge color="cyan">Sala 3D</Badge>:null}
                </Stack>
            </Center>
            </Card.Section>
            <Divider my={'xl'} size={'sm'} color="cyan.4"></Divider>
            <Card.Section>
                <SimpleGrid cols={2}>
                    <Pill text={`${projection.screen.idScreen}`} title="Sala"/>
                    <Pill text={`${projection.price}€`} title="Precio"></Pill>
                </SimpleGrid>
            </Card.Section>
            <Group position="center" w={'100%'} mt={'lg'}>
            <Button color="cyan" radius={'lg'}>
                <Link state={projection} to={'/asientos'}>Comprar entrada</Link>
                </Button>
            </Group>
        </Card>
        </SimpleGrid>
        )}
       </>
    )
}