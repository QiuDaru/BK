import Image from "next/image";
import { FiX, FiTag, FiFileText } from "react-icons/fi";
import { RentItem } from "@/lib/types/rentType";
import styles from "@/styles/Components/rent/RentModal.module.scss";
import borrowAPI from "@/services/borrowAPI";
import { useRouter } from "next/router";
import { useState } from "react";
import Modal, { ModalTypes } from "@/components/common/Modal";

interface RentModalProps {
  rent: RentItem;
  onClose: () => void;
}

export default function RentModal({ rent, onClose }: RentModalProps) {
  const router = useRouter();
  const [isShow, setIsShow] = useState(false);
  const [modalProps, setModalProps] = useState({
    type: ModalTypes.SUCCESS,
    message: "",
  });
  const handleBorrow = async (rentId: number) => {
    const userId = localStorage.getItem("authToken");
    if (!userId) {
      router.push("/login");
      return;
    }
    const dueDate = new Date();
    dueDate.setDate(dueDate.getDate() + 20);
    // console.log(dueDate.toISOString().slice(0, 19));

    try {
      const response = await borrowAPI.borrow({
        rentId,
        dueDate: dueDate.toISOString().slice(0, 19).replace("T", " "),
      });

      if (!response.result) {
        setModalProps({
          type: ModalTypes.ERROR,
          message: response.message || "借用失敗",
        });
        setIsShow(true);
        return;
      }

      setModalProps({
        type: ModalTypes.SUCCESS,
        message: "借用成功",
      });
      setIsShow(true);
    } catch {
      setModalProps({
        type: ModalTypes.ERROR,
        message: "借用失敗，請稍後再試",
      });
      setIsShow(true);
    }
  };

  return (
    <div className={styles.overlay} onClick={onClose}>
      <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
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

            <p className={styles.remark}>{rent.remark || "尚無物品說明"}</p>
          </div>

          <button
            type="button"
            className={styles.rentButton}
            onClick={() => handleBorrow(rent.id)}
          >
            我要借用
          </button>
        </div>
      </div>
    </div>
  );
}
