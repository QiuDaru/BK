import Layout from "@/components/Layout/Layout";
import { homeContent } from "@/lib/data/home";

import styles from "@/styles/pages/Home.module.scss";
export default function Home() {
  const { hero, actions, guide } = homeContent;

  return (
    <>
      <main className={styles.page}>
        <header className={styles.navbar}>
          <div className={styles.navInner}>
            <a className={styles.logo} href="/">
              {homeContent.brand}
            </a>
            <nav className={styles.nav}>
              <a>工具</a>
              <a>課程</a>
              <a>分享</a>
            </nav>
            <button className={styles.loginButton}>登入</button>
          </div>
        </header>

        <section className={styles.hero}>
          <div className={styles.heroInner}>
            <p className={styles.eyebrow}>{hero.eyebrow}</p>
            <h1>{hero.title}</h1>
            <p className={styles.subtitle}>{hero.subtitle}</p>
          </div>
        </section>

        <section className={styles.actions} id="actions">
          <div className={styles.sectionHeader}>
            <span>START HERE</span>
            <h2>{actions.title}</h2>
          </div>

          <div className={styles.actionGrid}>
            {actions.items.map((item) => {
              // const Icon = item.icon;
              return (
                <a
                  className={styles.actionCard}
                  href={item.href}
                  key={item.title}
                >
                  <div className={styles.cardTop}>
                    <div className={styles.icon}>
                      {item.icon}
                    </div>
                    <span className={styles.arrow}>↗</span>
                  </div>
                  <div className={styles.cardContent}>
                    <h3>{item.title}</h3>
                    <p>{item.description}</p>
                  </div>
                  <span className={styles.cardLink}>{item.cta}</span>
                </a>
              );
            })}
          </div>
        </section>

        <section className={styles.guide} id="guide">
          <div>
            <p className={styles.guideLabel}>{guide.label}</p>
            <h2>{guide.title}</h2>
          </div>
          <a href={guide.href}>
            {guide.cta}
            <span>→</span>
          </a>
        </section>

        <footer className={styles.footer}>
          <span>{homeContent.brand}</span>
          <span>{homeContent.footer}</span>
        </footer>
      </main>
    </>
  );
}
