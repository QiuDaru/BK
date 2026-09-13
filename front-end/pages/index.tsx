import Layout from "@/Components/Layout/Layout";
import { homeContent } from "@/lib/data/home";
import EnrollmentTimeline from "@/Components/home/EnrollmentTimeline";
import { mockEnrollments } from "@/lib/data/enrollments";

import styles from "@/styles/pages/Home.module.scss";

export default function Home() {
  const { hero, actions, guide } = homeContent;

  return (
    <Layout>
      <div className={styles.page}>
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

                  {/* 只有「學技能」(href === /teach) 這張卡片顯示報名時間軸 */}
                  {item.href === "/teach" && (
                    <div
                      style={{
                        flex: 1,
                        display: "flex",
                        alignItems: "flex-start",
                        padding: "12px 0",
                      }}
                    >
                      <EnrollmentTimeline enrollments={mockEnrollments} />
                    </div>
                  )}

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
      </div>
    </Layout>
  );
}