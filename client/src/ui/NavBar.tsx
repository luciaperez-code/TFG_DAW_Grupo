import { Button, Drawer, Group, Paper, PasswordInput, TextInput, Title } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import { useGetUsers, useLogin } from "api/users";
import { User } from "lib/definitions";
import { useEffect, useState } from "react";
import { Link, Outlet } from "react-router-dom";

export default function NavBar(){
    const [opened, {open, close}] = useDisclosure()
    const [email, setEmail] = useState<string>()
    const [password, setPassword] = useState<string>()
    const [error, setError] = useState<string>()

    const [loggedUser, setLoggedUser] = useState<User>()

    const {data: users} = useGetUsers()

    const {mutate} = useLogin()


    console.log({loggedUser})

    useEffect(() => {
        if (!opened) {
            setEmail('')
            setPassword('')
        }
    }, [opened])

    useEffect(() => {
        setError(undefined)
    }, [email,password])


    async function handleLogin(){
        const result  = await mutate({email: email!, password: password!}) as unknown as  {data?:any}
        if (result.data.token){
            console.log('inside!!')
           setLoggedUser(users!.filter(user => user.email == email)[0])
        } else {
            setError('Credenciales incorrectas')
        }
    }

    return (
        <>
            <Paper w={'90vw'} h={'5vh'} mt={'md'} mx={'auto'} radius={'xl'} style={{background: 'linear-gradient(166deg, rgba(34,195,107,1) 0%, rgba(45,119,253,1) 100%)'}}>
                <Group position="center" w={'100%'}>
                    <Link to={'/peliculas'}>Películas</Link>
                    <Button onClick={open}>{loggedUser ? loggedUser.name : 'Log In'}</Button>
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