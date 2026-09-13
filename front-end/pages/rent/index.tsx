import { useEffect, useState } from "react";
import Layout from "@/Components/Layout/Layout";
import styles from "@/styles/pages/rent/Rent.module.scss";
import ItemCard from "@/Components/rent/ItemCard";

import rentAPI from "@/services/rentAPI";
import { RentItem } from "@/lib/types/rentType";
import RentModal from "@/Components/rent/RentModal";
import { useRouter } from "next/router";

export default function Rent() {
  const [rentItems, setRentItems] = useState<RentItem[]>([]);
  const [isAdmin, setIsAdmin] = useState(false);
  const [selectedRent, setSelectedRent] = useState<RentItem | null>(null);

  const router = useRouter();

  const fetchRentItems = async () => {
    try {
      const response = await rentAPI.getAllRent();
      setRentItems(response.data);
    } catch (error) {}
  };

  useEffect(() => {
    fetchRentItems();
  }, []);

  return (
    <Layout>
      <div className={styles.page}>
        <div className={styles.rent}>
          <div className={styles.header}>
            <h2 className={styles.title}>分享工具</h2>
          </div>
          <div className={styles.cardGrid}>
            {rentItems.map((item) => {
              return <ItemCard rent={item} onClick={setSelectedRent} />;
            })}
          </div>
        </div>
        {selectedRent && (
          <RentModal
            rent={selectedRent}
            onClose={() => setSelectedRent(null)}
          />
        )}
        <button
          type="button"
          className={styles.postButton}
          onClick={() => router.push("/rent/post")}
        >
          發布閒置工具
        </button>
      </div>
    </Layout>
  );
}
