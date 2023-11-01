import { Button, Card, Grid, Paper, Image, Group, Text, Badge } from "@mantine/core";
import data from '../utils/mocks.json'

export default function Film() {
  const { films } = data

  console.log(films)
  return (
    <Paper w={'60vw'} mx={'auto'} py={'xl'}>
      <Grid justify="space-around" align="stretch">
        {films.map(film => {
          return (<Grid.Col span={4}>
            <Card shadow="sm" padding={'lg'} radius={'md'} withBorder>
              <Card.Section>
                <Image
                  src={film.Images[0]}
                  height={300}
                  alt={`Poster for the film ${film.Title}`}
                />
              </Card.Section>
              <Group justify="space-between" mt={'md'} mb={'xs'}>
                <Text fw={500}>{film.Title}</Text>
                <Badge color="blue">{film.Genre}</Badge>
              </Group>
              <Text size="sm" c={'dimmed'}> {film.Plot}</Text>
              <Button variant="light" color="blue" fullWidth mt={'md'} radius={'md'}>Ver horarios</Button>
            </Card>
          </Grid.Col>
          )
        })}

      </Grid>
    </Paper>
  )
}