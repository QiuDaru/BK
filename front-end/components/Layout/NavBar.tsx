import React from 'react';
import { homeContent } from "@/lib/data/home";
import Link from 'next/link';

import styles from '@/styles/Components/NavBar.module.scss';

export default function SideBar() {
  return (
    <aside className={styles.sidebar}>
      <a className={styles.logo} href="/">
        {homeContent.brand}
      </a>
      <nav className={styles.nav}>
        <Link href="/rent">工具</Link>
        <Link href="/teach">課程</Link>
        <Link href="/share">分享</Link>
      </nav>
      <button className={styles.loginButton}>登入</button>
    </aside>
  );
}