import Image from "next/image";
import { FiX, FiTag, FiFileText } from "react-icons/fi";
import { RentItem } from "@/lib/types/rentType";
import styles from "@/styles/Components/rent/RentModal.module.scss";

interface RentModalProps {
  rent: RentItem;
  onClose: () => void;
}

export default function RentModal({ rent, onClose }: RentModalProps) {
  return (
    <div className={styles.overlay} onClick={onClose}>
      <div
        className={styles.modal}
        onClick={(e) => e.stopPropagation()}
      >
        <button
          type="button"
          className={styles.close}
          onClick={onClose}
          aria-label="關閉"
        >
          <FiX />
        </button>

        <div className={styles.imageWrapper}>
          <Image
            src={rent.photoLink || "/default.jpg"}
            alt={rent.item}
            fill
            sizes="(max-width: 768px) 100vw, 560px"
          />
        </div>

        <div className={styles.content}>
          <span className={styles.category}>
            <FiTag />
            {rent.categoryName}
          </span>

          <h2 className={styles.title}>{rent.item}</h2>

          <div className={styles.section}>
            <div className={styles.sectionTitle}>
              <FiFileText />
              <span>物品說明</span>
            </div>

            <p className={styles.remark}>
              {rent.remark || "尚無物品說明"}
            </p>
          </div>

          <button type="button" className={styles.rentButton}>
            我要借用
          </button>
        </div>
      </div>
    </div>
  );
}