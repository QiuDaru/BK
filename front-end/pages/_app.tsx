import "@/styles/main.scss";
import { useRouter } from "next/router"; 
import { useEffect } from "react"; 
import type { AppProps } from "next/app"; 

function AuthGuard({ children }: { children: React.ReactNode }) { 
  return <>{children}</>; 
} 

export default function App({ Component, pageProps }: AppProps) { 
  return ( 
    <AuthGuard>
      <Component {...pageProps} /> 
    </AuthGuard>
  );
}
