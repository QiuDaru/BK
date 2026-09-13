export interface Instructor {
  id: string;
  name: string;
  likeCount: number;
}

export interface ClassSession {
  id: string;
  date: string;          // "2026-09-13"
  startTime: string;
  endTime: string;
  title: string;
  description: string;
  instructorId: string;
  isCompleted: boolean;   // 課程是否已結束，控制能不能按讚
}

export interface DayItem {
  date: string;
  weekday: string;
  day: number;
  disabled?: boolean;
}

export const mockInstructors: Instructor[] = [
  { id: "ins1", name: "講師 A", likeCount: 12 },
  { id: "ins2", name: "講師 B", likeCount: 5 },
  { id: "ins3", name: "講師 C", likeCount: 8 },
];

export const mockDays: DayItem[] = Array.from({ length: 7 }).map((_, i) => {
  const date = new Date();
  date.setDate(date.getDate() + i);
  const weekdayMap = ["S", "M", "T", "W", "T", "F", "S"];
  return {
    date: date.toISOString().slice(0, 10),
    weekday: weekdayMap[date.getDay()],
    day: date.getDate(),
  };
});

export const mockClasses: ClassSession[] = [
  {
    id: "1",
    date: mockDays[0].date,
    startTime: "10:00",
    endTime: "11:15",
    title: "課程名稱 A",
    description: "這堂課會介紹基礎動作與節奏感練習，適合初學者。",
    instructorId: "ins1",
    isCompleted: false,
  },
  {
    id: "2",
    date: mockDays[0].date,
    startTime: "11:30",
    endTime: "12:45",
    title: "課程名稱 B",
    description: "進階組合技巧與肌肉控制練習。",
    instructorId: "ins2",
    isCompleted: false,
  },
  {
    id: "3",
    date: mockDays[0].date,
    startTime: "13:00",
    endTime: "14:15",
    title: "課程名稱 C",
    description: "重點在律動與表演張力訓練。",
    instructorId: "ins3",
    isCompleted: false,
  },
];