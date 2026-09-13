import { useState } from "react";
import { useRouter } from "next/router";
import Layout from "@/Components/Layout/Layout";
import { mockClasses, mockInstructors } from "@/lib/data/teach";
import styles from "@/styles/Pages/ClassDetail.module.scss";

export default function ClassDetailPage() {
  const router = useRouter();
  const { id } = router.query;

  const session = mockClasses.find((c) => c.id === id);
  const instructor = mockInstructors.find((i) => i.id === session?.instructorId);

  const [isEnrolled, setIsEnrolled] = useState(false);
  const [hasLiked, setHasLiked] = useState(false);
  const [likeCount, setLikeCount] = useState(instructor?.likeCount ?? 0);

  if (!session || !instructor) {
    return (
      <Layout>
        <p style={{ padding: 16 }}>找不到這堂課的資訊</p>
      </Layout>
    );
  }

  const handleEnroll = () => {
    setIsEnrolled(true);
  };

  const handleLike = () => {
    if (hasLiked) return;
    setHasLiked(true);
    setLikeCount((c) => c + 1);
  };

return (
  <Layout>
    <div className={styles.pageWrapper}>
      <div className={styles.container}>
        <h2 className={styles.title}>{session.title}</h2>
        <p className={styles.meta}>
          {session.startTime} – {session.endTime}　講師：{instructor.name}
        </p>

        <p className={styles.description}>{session.description}</p>

        {!isEnrolled ? (
          <button className={styles.enrollButton} onClick={handleEnroll}>
            報名這堂課
          </button>
        ) : (
          <p className={styles.enrolledText}>已報名成功！</p>
        )}

        {isEnrolled && session.isCompleted && (
          <div className={styles.likeSection}>
            <p>這堂課已結束，覺得講師教得不錯嗎？</p>
            <button
              className={styles.likeButton}
              onClick={handleLike}
              disabled={hasLiked}
            >
              {hasLiked ? "已按讚" : "幫老師按讚"} ❤ {likeCount}
            </button>
          </div>
        )}
      </div>
    </div>
  </Layout>
)}