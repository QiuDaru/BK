import { useState } from "react";
import { ClassSession } from "@/lib/data/home";
import styles from "@/styles/Components/ClassCard.module.scss";

interface Props {
  session: ClassSession;
}

const CAPACITY = 15;

export default function ClassCard({ session }: Props) {
  const [remaining, setRemaining] = useState(CAPACITY);

  const handleEnroll = () => {
    if (remaining <= 0) return;
    setRemaining((prev) => prev - 1);
    // TODO: 這裡之後接實際報名 API
  };

  return (
    <div className={styles.card}>
      <span className={styles.time}>{session.startTime}</span>
      <div className={styles.info}>
        <span className={styles.title}>{session.title}</span>
        <span className={styles.meta}>
          {session.startTime} – {session.endTime}　講師：{session.instructor}
        </span>
        <span className={styles.capacity}>剩餘名額：{remaining} 人</span>
      </div>
      <button
        className={styles.enrollButton}
        onClick={handleEnroll}
        disabled={remaining <= 0}
      >
        {remaining <= 0 ? "已額滿" : "我要報名"}
      </button>
    </div>
  );
}