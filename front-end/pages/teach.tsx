import { useState } from "react";
import { useRouter } from "next/router";
import Layout from "@/components/Layout/Layout";
import DateStrip from "@/components/Teach/DateStrip";
import ClassCard from "@/components/Teach/ClassCard";
import { mockDays, mockClasses } from "@/lib/data/teach";

export default function TeachPage() {
  const [selectedDate, setSelectedDate] = useState(mockDays[0].date);
  const router = useRouter();

  const classesForDay = mockClasses.filter((c) => c.date === selectedDate);

  return (
    <Layout>
      <div style={{ padding: "0 16px" }}>
        <h2 style={{ padding: "16px 0 0" }}>課程</h2>

        <div style={{ marginBottom: 8, color: "#666" }}>
          Today &nbsp;&nbsp; {selectedDate.replace(/-/g, "/")}
        </div>

        <DateStrip
          days={mockDays}
          selectedDate={selectedDate}
          onSelect={setSelectedDate}
        />

        {classesForDay.length === 0 ? (
          <p style={{ padding: 16, color: "#999" }}>這天沒有課程</p>
        ) : (
          classesForDay.map((session) => (
            <div
              key={session.id}
              onClick={() => router.push(`/teach/${session.id}`)}
              style={{ cursor: "pointer" }}
            >
              <ClassCard session={session} />
            </div>
          ))
        )}
      </div>
    </Layout>
  );
}