import styles from "@/styles/Components/Modal.module.scss";

export enum ModalTypes {
  SUCCESS = "success",
  ERROR = "error",
}

interface ModalProps {
  type: ModalTypes;
  message: string;
  onClose: () => void;
}

export default function Modal({
  type,
  message,
  onClose,
}: ModalProps) {
  return (
    <div className={styles.overlay} onClick={onClose}>
      <div
        className={styles.modal}
        onClick={(e) => e.stopPropagation()}
      >
        <div
          className={`${styles.status} ${
            type === ModalTypes.SUCCESS
              ? styles.success
              : styles.error
          }`}
        >
          {type === ModalTypes.SUCCESS ? "成功" : "錯誤"}
        </div>

        <p>{message}</p>

        <button type="button" onClick={onClose}>
          確定
        </button>
      </div>
    </div>
  );
}