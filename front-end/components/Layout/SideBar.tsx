import React from 'react';
import styles from '@/styles/Components/SideBar.module.scss';

export default function SideBar() {
  return (
    <aside className={styles.sidebar}>
      <div className={styles.brand}>
        <h2>社區共享平台</h2>
      </div>

      <nav className={styles.nav}>
        <ul>
            
          <li>
            <a href="/rent">
              <span className={styles.label}>物品借用</span>
            </a>
          </li>
          <li>
            <a href="/teach">
              <span className={styles.label}>技能交換</span>
            </a>
          </li>
        </ul>
      </nav>

      {/* 底部使用者資訊區 */}
      <div className={styles.footer}>
        <div className={styles.userProfile}>
          <div className={styles.avatar}>A</div>
          <div className={styles.userInfo}>
            <p className={styles.name}>Andy</p>
            <p className={styles.role}>社區居民</p>
          </div>
        </div>
      </div>
    </aside>
  );
}