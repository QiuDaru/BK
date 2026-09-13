import { VscLightbulbSparkle } from "react-icons/vsc";
import { LuDrill } from "react-icons/lu";

export const homeContent = {
  brand: "社區共好",
  footer: "分享工具・學習技能・連結社區",
  hero: {
    eyebrow: "COMMUNITY SHARING",
    title: "讓社區裡的工具與技能，被更多人看見。",
    subtitle: "分享工具・學習技能・連結社區",
  },
  actions: {
    title: "你現在想做什麼？",
    items: [
      {
        icon: <VscLightbulbSparkle />,
        title: "借工具",
        description: "找到或出借附近的工具與設備。",
        cta: "工具專區 →",
        href: "/rent",
      },
      {
        icon: <LuDrill />,
        title: "學技能",
        description: "找到社區裡的人開設的課程與技能，或自己教！",
        cta: "課程專區 →",
        href: "/teach",
      },
    ],
  },
  guide: {
    label: "NEW TO SHARING?",
    title: "不確定怎麼分享嗎？",
    cta: "了解分享前需要準備什麼",
    href: "/share-guide",
  },
};

export interface Instructor {
  id: string;
  name: string;
  likeCount: number;
}

export interface ClassSession {
  id: string;
  date:string;
  startTime: string;
  endTime: string;
  averagetime:string;
  title: string;
  description: string;      // 課堂基本介紹
  instructor: string;
  isCompleted: boolean;      // 這堂課是否已經上完（決定能不能按讚）
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
    averagetime:"1小時15分",
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
    averagetime:"1小時15分",
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
    averagetime:"1小時15分",
    title: "課程名稱 C",
    description: "重點在律動與表演張力訓練。",
    instructorId: "ins3",
    isCompleted: false,
  },
];