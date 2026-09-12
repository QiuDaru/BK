import Image from "next/image";
import { RentItem } from "@/lib/types/rentType";
import styles from "@/styles/Components/rent/ItemCard.module.scss";

interface ItemCardProps {
  rent: RentItem;
  onClick: (rent: RentItem) => void;
}

export default function ItemCard({
  rent,
  onClick,
}: ItemCardProps) {
  return (
    <button
      type="button"
      className={styles.card}
      onClick={() => onClick(rent)}
    >
      <div className={styles.imageWrapper}>
        <Image
          src={rent.photoLink || "/default.jpg"}
          alt={rent.item}
          fill
          sizes="(max-width: 640px) 100vw, (max-width: 1024px) 50vw, 33vw"
          priority={false}
        />
      </div>

      <div className={styles.content}>
        <h3 className={styles.title}>{rent.item}</h3>

        <div className={styles.footer}>
          <span>{rent.categoryName}</span>
        </div>
      </div>
    </button>
  );
}