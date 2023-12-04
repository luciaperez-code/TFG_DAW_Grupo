import { Button, Card, Grid, Paper, Group, Text, Badge, Image, LoadingOverlay } from "@mantine/core";
import { Link } from "react-router-dom";
import { useGetAllFilms } from "api/films";

export default function Film() {

  const { data : films, isLoading } = useGetAllFilms()

  if (isLoading) return <LoadingOverlay visible={true} />

  return (
    <Paper w={'60vw'} mx={'auto'} py={'xl'}>
      <Grid justify="space-around" align="stretch">
        {films!.map(film => {
          return (<Grid.Col span={4} key={film.idFilm}>
            <Card shadow="lg" padding={'lg'} radius={'md'}>
              <Card.Section>
                <Image
                  src={film.images}
                  height={'500'}
                  fit="contain"
                  alt={`Poster for the film ${film.title}`}
                />
              </Card.Section>
              <Group position="apart" mt={'md'} mb={'xs'}>
                <Text fw={500}>{film.title}</Text>
              
              </Group>
            {film.genre.split(', ').map((genre: string) => {
              return <Badge color="blue" key={genre} m={'xs'}>{genre}</Badge>
            })}
              <Text size="sm" mt={'xl'} c={'dimmed'}> {film.plot}</Text>
              <Button variant="light" color="blue" fullWidth mt={'md'} radius={'md'}>
                <Link state={film.idFilm} to={`${film.idFilm}`}>Ver horarios</Link></Button>
            </Card>
          </Grid.Col>
          )
        })}
      </Grid>
    </Paper>
  )
}