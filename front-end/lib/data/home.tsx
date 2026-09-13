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
