import { ClassSession, mockInstructors } from "@/lib/data/teach";
import styles from "@/styles/Components/ClassCard.module.scss";

interface Props {
  session: ClassSession;
}

export default function ClassCard({ session }: Props) {
  const instructor = mockInstructors.find((i) => i.id === session.instructorId);

  return (
    <div className={styles.card}>
      <div className={styles.timeColumn}>
        <span className={styles.time}>{session.startTime}</span>
      </div>
      <div className={styles.content}>
        <h3 className={styles.title}>{session.title}</h3>
        <span className={styles.timeRange}>
          {session.startTime} – {session.endTime}　講師：{instructor?.name}
        </span>
      </div>
    </div>
  );
}