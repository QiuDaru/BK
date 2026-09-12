import type { AppProps } from "next/app";
import "@/styles/main.scss"; // 引入全域樣式（路徑依你的專案配置而定）

export default function App({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />;
}
