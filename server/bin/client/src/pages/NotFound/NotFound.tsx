import { Center, Title, Text, Button, Group } from '@mantine/core';
import classes from './NotFound.module.css';
import { Illustration } from '../../ui/Illustration';

export default function NotFound() {
  return (
    <Center className={classes.root} h={'100vh'}>
      <div className={classes.inner}>
        <Illustration className={classes.image} />
        <div className={classes.content}>
          <Title className={classes.title}>Vaya! No se ha podido acceder...</Title>
          <Text c="dimmed" size="lg" ta="center" className={classes.description}>
            La página a la que intentas acceder no existe. Puede que hayas escrito mal la url, o que la página haya cambiado de dirección.
          </Text>
          <Group justify="center" mt={'xl'}>
            <Button size="md">Volver a la cartelera</Button>
          </Group>
        </div>
      </div>
    </Center>
  );
}