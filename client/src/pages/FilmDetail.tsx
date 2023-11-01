import { Image, Paper, SimpleGrid, Stack, Title, Text, Badge, Group } from "@mantine/core";
import { useLoaderData } from "react-router-dom";


export default function FilmDetail() {
  const film = useLoaderData()
  console.log('FILM => ', film)
  return (
    <Paper p={'xl'}>
      <SimpleGrid cols={3}>

        <Image
          radius={'md'}
          h={'20vh'}
          w={'auto'}
          fit="contain"
          src={film.Images[0]}
        />
        <Stack>
          <Title>{film.Title}</Title>

          <Title order={3}>Director</Title>
          <Text c={'dimmed'}>{film.Director}</Text>
          <Title order={3}>Actores</Title>
          <Text c={'dimmed'}>{film.Actors}</Text>
          <Title order={3}>Sinopsis</Title>
          <Text c={'dimmed'}>{film.Plot}</Text>
        </Stack>
        <Stack>
          <Title order={3}>Duracion</Title>
          <Text c={'dimmed'}>{film.Runtime}</Text>
          <Title order={3}>Fecha de estreno</Title>
          <Text c={'dimmed'}>{film.Released}</Text>
          <Group>

            {film.Genre.split(', ').map((genre: string) => {
              return <Badge color="gray" key={genre}>{genre}</Badge>
            })}
          </Group>

        </Stack>
      </SimpleGrid>
    </Paper>
  )
}