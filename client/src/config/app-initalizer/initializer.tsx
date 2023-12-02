import React from 'react'
import ReactDOM from 'react-dom/client'
import { MantineProvider } from '@mantine/core'
import App from './entrypoint'
import { QueryClientProvider } from '@tanstack/react-query'
import { queryClient } from 'lib/react-query'


ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <MantineProvider>
      <QueryClientProvider client={queryClient}>
        <App />
      </QueryClientProvider>
    </MantineProvider>
  </React.StrictMode>,
)
