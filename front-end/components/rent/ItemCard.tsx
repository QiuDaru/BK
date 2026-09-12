import Image from 'next/image';
import styles from '@/styles/Components/rent/ItemCard.module.scss';

// 定義元件的 Props 介面
interface ItemCardProps {
  id: string | number;
  title: string;
  imageUrl?: string;
  isAvailable?: boolean;
}

export default function ItemCard({ id, title, imageUrl, isAvailable }: ItemCardProps) {
  return (
    <div className={styles.card}>
      {/* 圖片區塊：比例由 CSS 控制 */}
      <div className={styles.imageWrapper}>
        <Image
          src={imageUrl || '/default-image.jpg'} // 如果沒有提供 imageUrl，使用預設圖片
          alt={title}
          fill
          sizes="(max-width: 640px) 100vw, (max-width: 1024px) 50vw, 33vw"
          priority={false}
        />
      </div>

      {/* 文字與標籤區塊 */}
      <div className={styles.content}>
        <h3 className={styles.title}>{title}</h3>
        
        <div className={styles.footer}>
          {isAvailable ? (
            <span className={`${styles.badge} ${styles.available}`}>● 可借用</span>
          ) : (
            <span className={`${styles.badge} ${styles.borrowed}`}>● 已借出</span>
          )}
        </div>
      </div>
    </div>
  );
}