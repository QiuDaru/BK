import { useState } from "react";
import styles from "@/styles/Components/ClassDetailModalContent.module.scss";

export default function ClassDetailModalContent() {
  const [form, setForm] = useState({
    name: "",
    gender: "",
    phone: "",
    email: "",
    courseName: "",
    capacity: "",
    datetime: "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    console.log(form);
    // TODO: 送出資料到後端
  };

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>我要教課</h2>

      <div className={styles.field}>
        <label className={styles.label}>姓名</label>
        <input
          className={styles.input}
          name="name"
          value={form.name}
          onChange={handleChange}
        />
      </div>

      <div className={styles.field}>
        <label className={styles.label}>性別</label>
        <select
          className={styles.input}
          name="gender"
          value={form.gender}
          onChange={handleChange}
        >
          <option value="">請選擇</option>
          <option value="male">男</option>
          <option value="female">女</option>
          <option value="other">其他</option>
        </select>
      </div>

      <div className={styles.field}>
        <label className={styles.label}>電話</label>
        <input
          className={styles.input}
          name="phone"
          type="tel"
          value={form.phone}
          onChange={handleChange}
        />
      </div>

      <div className={styles.field}>
        <label className={styles.label}>Gmail</label>
        <input
          className={styles.input}
          name="email"
          type="email"
          value={form.email}
          onChange={handleChange}
        />
      </div>

      <div className={styles.field}>
        <label className={styles.label}>教什麼名稱</label>
        <input
          className={styles.input}
          name="courseName"
          value={form.courseName}
          onChange={handleChange}
        />
      </div>

      <div className={styles.field}>
        <label className={styles.label}>人數</label>
        <input
          className={styles.input}
          name="capacity"
          type="number"
          min={1}
          value={form.capacity}
          onChange={handleChange}
        />
      </div>

      <div className={styles.field}>
        <label className={styles.label}>日期時間</label>
        <input
          className={styles.input}
          name="datetime"
          type="datetime-local"
          value={form.datetime}
          onChange={handleChange}
        />
      </div>

      <button className={styles.enrollButton} onClick={handleSubmit}>
        送出
      </button>
    </div>
  );
}