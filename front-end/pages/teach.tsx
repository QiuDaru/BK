import { useState } from "react";
import Layout from "@/Components/Layout/Layout";
import DateStrip from "@/Components/Teach/DateStrip";
import ClassCard from "@/Components/Teach/ClassCard";
import Modal from "@/Components/common/Modal";
import ClassDetailModalContent from "@/Components/Teach/ClassDetailModalContent";
import { mockDays, mockClasses } from "@/lib/data/home";
import styles from "@/styles/Pages/Teach.module.scss";

export default function TeachPage() {
  const [selectedDate, setSelectedDate] = useState(mockDays[0].date);
  const [isModalOpen, setIsModalOpen] = useState(false);

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
              <ClassCard key={session.id} session={session} />
            ))
          )}
        </div>
      </div>

      <button
        className={styles.teachButton}
        onClick={() => setIsModalOpen(true)}
      >
        我要教課
      </button>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <ClassDetailModalContent />
      </Modal>
    </Layout>
  );
}