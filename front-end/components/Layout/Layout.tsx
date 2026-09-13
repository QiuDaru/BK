import Head from "next/head";
import SideBar from "@/Components/Layout/NavBar";
import styles from "@/styles/Components/Layout.module.scss";

export default function Layout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Head>
        <title>BIRC HACKATHON</title>
        <meta charSet="UTF-8" />
        <meta name="description" content="NTUBIMD" />
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1.0"
        />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.page}>
        <SideBar />

        {children}
      </main>
    </>
  );
}