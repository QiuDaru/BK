export interface Enrollment {
  id: string;
  courseTitle: string;
  instructor: string;
  date: string;      // "2026-09-13"
  time: string;       // "10:00 - 11:15"
}

export const mockEnrollments: Enrollment[] = [
  { id: "1", courseTitle: "課程名稱 A", instructor: "講師 A", date: "2026-09-13", time: "10:00 – 11:15" },
  { id: "2", courseTitle: "課程名稱 B", instructor: "講師 B", date: "2026-09-15", time: "11:30 – 12:45" },
];