import { ClassSession } from "@/lib/data/teach";
import styles from "@/styles/Components/ClassCard.module.scss";

interface Props {
  session: ClassSession;
}

export default function ClassCard({ session }: Props) {
  return (
    <div className={styles.card}>
      <div className={styles.header}>
        <span className={styles.time}>{session.startTime}</span>
        <h3 className={styles.title}>{session.title}</h3>
      </div>
      <div className={styles.detail}>
        <span className={styles.timeRange}>
          {session.startTime} – {session.endTime}
        </span>
      </div>
    </div>
  );
}