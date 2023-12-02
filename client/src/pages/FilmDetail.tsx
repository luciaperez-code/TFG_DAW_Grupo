import { Image, Paper, SimpleGrid, Stack, Title, Text, Badge, Group, Loader, Accordion } from "@mantine/core";
import { useGetFilm } from "api/films";
import { useLocation } from "react-router-dom";
import Projections from "ui/Projections";
import { getDate } from "utils/dates";


export default function FilmDetail() {
  const idFilm = useLocation().state

  const {data: film, isLoading} = useGetFilm(idFilm)

  console.log(film)

  if (isLoading) return <Loader/>

  if (!film) return <></>

  console.log(getDate(film.released))
  
  return (
    <Paper p={'xl'}>
      <SimpleGrid cols={3} maw={'100rem'} spacing={'xl'} mx={'auto'}>
        <Image
          radius={'md'}
          src={film?.images}
        />
        <Stack>
          <Title>{film.title}</Title>
          <Title order={3}>Director</Title>
          <Text c={'dimmed'}>{film.director}</Text>
          <Title order={3}>Actores</Title>
          <Text c={'dimmed'}>{film.actors}</Text>
          <Title order={3}>Sinopsis</Title>
          <Text c={'dimmed'}>{film.plot}</Text>
        </Stack>
        <Stack>
          <Title order={3}>Duracion</Title>
          <Text c={'dimmed'}>{film.runtime}</Text>
          <Title order={3}>Fecha de estreno</Title>
          <Text c={'dimmed'}>{getDate(film.released).year}</Text>
          <Group>

            {film.genre.split(', ').map((genre: string) => {
              return <Badge color="gray" key={genre}>{genre}</Badge>
            })}
          </Group>
        </Stack>
      </SimpleGrid>
      <Accordion>
        <Accordion.Item value="schedule">
          <Accordion.Control>
            <h2>Ver Horarios</h2>
          </Accordion.Control>
          <Accordion.Panel>
            <Projections title={film.title}/>
          </Accordion.Panel>
        </Accordion.Item>
      </Accordion>
    </Paper>
  )
}
