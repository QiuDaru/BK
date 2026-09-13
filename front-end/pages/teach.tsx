import { useState } from "react";
import { useRouter } from "next/router";
import Layout from "@/components/Layout/Layout";
import DateStrip from "@/components/Teach/DateStrip";
import ClassCard from "@/components/Teach/ClassCard";
import { mockDays, mockClasses } from "@/lib/data/teach";
import styles from "@/styles/Pages/Teach.module.scss";

export default function TeachPage() {
  const [selectedDate, setSelectedDate] = useState(mockDays[0].date);
  const router = useRouter();

  const classesForDay = mockClasses.filter((c) => c.date === selectedDate);

  return (
    <Layout>
      <div className={styles.wrapper}>
        <h2 className={styles.pageTitle}>課程</h2>
        <div className={styles.dateLabel}>
          Today &nbsp;&nbsp; {selectedDate.replace(/-/g, "/")}
        </div>

        <DateStrip
          days={mockDays}
          selectedDate={selectedDate}
          onSelect={setSelectedDate}
        />

        <div className={styles.list}>
          {classesForDay.length === 0 ? (
            <p style={{ color: "#999" }}>這天沒有課程</p>
          ) : (
            classesForDay.map((session) => (
              <div
                key={session.id}
                onClick={() => router.push(`/teach/${session.id}`)}
              >
                <ClassCard session={session} />
              </div>
            ))
          )}
        </div>
      </div>
    </Layout>
  );
}