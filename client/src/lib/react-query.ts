import { AxiosError } from 'axios'
import {
  QueryClient,
  UseQueryOptions,
  UseMutationOptions,
  DefaultOptions,
} from '@tanstack/react-query'

const queryConfig: DefaultOptions = {
  queries: {
    refetchOnWindowFocus: true,
    // gcTime: 10 * 60 * 1000,
  },
}
export const queryClient = new QueryClient({ defaultOptions: queryConfig })

// Getting types of returned functions
type UnwrapPromise<T> = T extends Promise<infer U> ? U : T
export type ExtractFnReturnType<FnType extends (...args: any) => any> = UnwrapPromise<
  ReturnType<FnType>
>

export type QueryConfig<QueryFnType extends (...args: any) => any> = Omit<
  UseQueryOptions<ExtractFnReturnType<QueryFnType>>,
  'queryKey' | 'queryFn'
>

export type MutationConfig<MutationFnType extends (...args: any) => any> = UseMutationOptions<
  ExtractFnReturnType<MutationFnType>,
  AxiosError,
  Parameters<MutationFnType>[0]
>