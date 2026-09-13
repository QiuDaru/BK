import { Enrollment } from "@/lib/data/enrollments";
import styles from "@/styles/Components/EnrollmentTimeline.module.scss";

interface Props {
  enrollments: Enrollment[];
}

export default function EnrollmentTimeline({ enrollments }: Props) {
  if (enrollments.length === 0) {
    return <p className={styles.empty}>目前還沒有報名任何課程</p>;
  }

  return (
    <div className={styles.timeline}>
      {enrollments.map((item) => (
        <div key={item.id} className={styles.bubble}>
          <div className={styles.bubbleHeader}>
            <span className={styles.date}>{item.date.replace(/-/g, "/")}</span>
            <span className={styles.time}>{item.time}</span>
          </div>
          <div className={styles.bubbleBody}>
            <span className={styles.courseTitle}>{item.courseTitle}</span>
            <span className={styles.instructor}>講師：{item.instructor}</span>
          </div>
        </div>
      ))}
    </div>
  );
}