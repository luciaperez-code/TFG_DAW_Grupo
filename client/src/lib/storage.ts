export const storage = {
    getAccessToken: () => JSON.parse(window.localStorage.getItem('access_token') || 'null'),
    setAccessToken: (token: string) =>
      window.localStorage.setItem('access_token', JSON.stringify(token)),
    clearAccessToken: () => window.localStorage.removeItem('access_token'),
  }