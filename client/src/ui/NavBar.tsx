import { Button, Drawer, Group, Paper, PasswordInput, TextInput, Title, UnstyledButton } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import { useLogin } from "api/users";
import { User } from "lib/definitions";
import { useEffect, useState } from "react";
import { Link, Outlet } from "react-router-dom";

export default function NavBar(){
    const [opened, {open, close}] = useDisclosure()
    const [email, setEmail] = useState<string>()
    const [password, setPassword] = useState<string>()
    const [error, setError] = useState<string>()

    const [loggedUser, setLoggedUser] = useState<User>()


    const {mutate, data: user} = useLogin()



    useEffect(() => {
        if (!opened) {
            setEmail('')
            setPassword('')
        }
    }, [opened])

    useEffect(() => {
        setError(undefined)
    }, [email,password])


    useEffect(() => { //Displaying the result of the log in
        if (user instanceof Object && 'name' in user){
            setLoggedUser(user)
            close()
        } else {
            setError('Credenciales incorrectas')
        }
    }, [user])

    async function handleLogin(){
       mutate({email: email!, password: password!}) as unknown as User | undefined
    }

    return (
        <>
            <Paper w={'90vw'} h={'5vh'} mt={'md'} mx={'auto'} radius={'xl'} style={{background: 'linear-gradient(166deg, rgba(34,195,107,1) 0%, rgba(45,119,253,1) 100%)'}}>
                <Group position="center" w={'100%'} spacing={'xl'}>
                    <Link to={'/peliculas'} style={{color: 'white', textDecoration: 'none', marginTop:'1.5vh'}}>Películas</Link>
                    <UnstyledButton mt={'1.5vh'} c="white" onClick={open}>{loggedUser ? loggedUser.name : 'Log In'}</UnstyledButton>
                </Group>
                <Drawer opened={opened} onClose={close} position="right">
                    <Title align="center">Iniciar sesión</Title>
                    <TextInput m={'xl'} type="email" value={email} onChange={e => setEmail(e.target.value)} label={'Correo electrónico'} />
                    <PasswordInput m={'xl'}  value={password} onChange={e => setPassword(e.target.value)} label={'Contraseña'} />
                    <p style={{color: 'red'}}>{error}</p>
                    <Button disabled={!email || !password} type="submit" onClick={handleLogin} radius={'md'} ml={'xl'} mt={'xl'}>Acceder</Button>                    
                </Drawer>
            </Paper>
            <Outlet/>
        </>
    )
}