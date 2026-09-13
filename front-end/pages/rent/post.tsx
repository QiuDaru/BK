import { useState } from "react";
import { useRouter } from "next/router";
import Layout from "@/components/Layout/Layout";
import rentAPI from "@/services/rentAPI";
import {
  RentCategoryId,
  rentCategories,
} from "@/lib/types/rentType";
import styles from "@/styles/pages/rent/Post.module.scss";

export default function PostRent() {
  const router = useRouter();

  const [item, setItem] = useState("");
  const [categoryId, setCategoryId] = useState<RentCategoryId>(4);
  const [year, setYear] = useState(new Date().getFullYear());
  const [remark, setRemark] = useState("");
  const [photoLink, setPhotoLink] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData();

    formData.append("year", String(year));
    formData.append("categoryId", String(categoryId));
    formData.append("item", item);
    formData.append("remark", remark);
    formData.append("photoLink", photoLink);

    try {
      setLoading(true);
      await rentAPI.createRent(formData);
      await router.push("/rent");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Layout>
      <main className={styles.page}>
        <div className={styles.container}>
          <div className={styles.header}>
            <p className={styles.eyebrow}>SHARE A TOOL</p>
            <h1>發布閒置工具</h1>
            <p>讓社區裡暫時用不到的工具，找到需要它的人。</p>
          </div>

          <form className={styles.form} onSubmit={handleSubmit}>
            <div className={styles.field}>
              <label htmlFor="item">物品名稱</label>
              <input
                id="item"
                type="text"
                value={item}
                onChange={(e) => setItem(e.target.value)}
                placeholder="例如：電動鑽"
                required
              />
            </div>

            <div className={styles.row}>
              <div className={styles.field}>
                <label htmlFor="categoryId">分類</label>
                <select
                  id="categoryId"
                  value={categoryId}
                  onChange={(e) =>
                    setCategoryId(Number(e.target.value) as RentCategoryId)
                  }
                >
                  {rentCategories.map((category) => (
                    <option key={category.id} value={category.id}>
                      {category.name}
                    </option>
                  ))}
                </select>
              </div>

              <div className={styles.field}>
                <label htmlFor="year">年份</label>
                <input
                  id="year"
                  type="number"
                  value={year}
                  onChange={(e) => setYear(Number(e.target.value))}
                  min="1900"
                  max="2100"
                  required
                />
              </div>
            </div>

            <div className={styles.field}>
              <label htmlFor="remark">物品說明</label>
              <textarea
                id="remark"
                value={remark}
                onChange={(e) => setRemark(e.target.value)}
                placeholder="例如：附充電器，功能正常。"
                rows={5}
              />
            </div>

            <div className={styles.field}>
              <label htmlFor="photoLink">照片網址</label>
              <input
                id="photoLink"
                type='file'
                // disabled={true}
                value={photoLink}
                onChange={(e) => setPhotoLink(e.target.value)}
                placeholder="https://example.com/drill.jpg"
              />
            </div>

            <button
              type="submit"
              className={styles.submit}
              disabled={loading}
            >
              {loading ? "發布中..." : "發布物品"}
            </button>
          </form>
        </div>
      </main>
    </Layout>
  );
}