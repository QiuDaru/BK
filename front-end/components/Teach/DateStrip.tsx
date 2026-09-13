import { DayItem } from "@/lib/data/teach";
import styles from "../../styles/Components/DateStrip.module.scss";

interface Props {
  days: DayItem[];
  selectedDate: string;
  onSelect: (date: string) => void;
}

export default function DateStrip({ days, selectedDate, onSelect }: Props) {
  return (
    <div className={styles.wrapper}>
      <div className={styles.weekdayRow}>
        {days.map((d) => (
          <span key={d.date} className={styles.weekdayLabel}>
            {d.weekday}
          </span>
        ))}
      </div>
      <div className={styles.dayRow}>
        {days.map((d) => {
          const isSelected = d.date === selectedDate;
          return (
            <button
              key={d.date}
              disabled={d.disabled}
              className={`${styles.dayButton} ${
                isSelected ? styles.selected : ""
              } ${d.disabled ? styles.disabled : ""}`}
              onClick={() => onSelect(d.date)}
            >
              {d.day}
            </button>
          );
        })}
      </div>
    </div>
  );
}