import React from 'react';
import { homeContent } from "@/lib/data/home";
import Link from 'next/link';

import styles from '@/styles/Components/NavBar.module.scss';

export default function NavBar() {  
  return (
    <header className={styles.navbar}>
          <div className={styles.navInner}>
            <Link className={styles.logo} href="/">
      {homeContent.brand}
    </Link>
            <nav className={styles.nav}>
              <Link href="/rent">工具</Link>
              <Link href="/teach">課程</Link>
              <Link href="/share">分享</Link>
            </nav>
            <Link className={styles.loginButton} href="/login">
      登入
    </Link>
          </div>
        </header>
  );
}