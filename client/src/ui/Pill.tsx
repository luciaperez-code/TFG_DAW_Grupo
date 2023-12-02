import { Group, Title, Text, Divider } from "@mantine/core"
import type { ReactNode } from "react"

type Pill = {
  children?: ReactNode
  title?: string
  text?: string
}
const Pill = ({ children, title, text }: Pill) => {
  return (
    <Group pl={'xs'} mb={'xs'}>
      {children}
      <Divider orientation="vertical" color="cyan.4" size={'md'}></Divider>
      <div>
        <Title order={4}>{title}</Title>
        <Text size="md" color="dimmed">{text}</Text>
      </div>
    </Group>
  )
}

export default Pill