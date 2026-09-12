import { useEffect, useState } from "react";
import Layout from "@/components/Layout/Layout";
import styles from "@/styles/pages/rent/Rent.module.scss";
import ItemCard from "@/components/rent/ItemCard";
export default function Rent() {
  const [isAdmin, setIsAdmin] = useState(false);
  return (
    <Layout>
      <div className={styles.page}>
        <div className={styles.rent}>
          <div className={styles.header}>分享工具</div>
          <div className={styles.itemList}></div>
        </div>
      </div>
    </Layout>
  );
}
