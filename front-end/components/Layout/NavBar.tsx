import { useEffect, useState } from "react";
import Link from "next/link";
import { useRouter } from "next/router";

import { homeContent } from "@/lib/data/home";
import loginAPI from "@/services/Login/loginAPI";
import styles from "@/styles/Components/NavBar.module.scss";

export default function NavBar() {
  const router = useRouter();
  const [isLogin, setIsLogin] = useState(false);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const checkLogin = () => {
      setIsLogin(Boolean(localStorage.getItem("authToken")));
    };

    checkLogin();

    router.events.on("routeChangeComplete", checkLogin);

    return () => {
      router.events.off("routeChangeComplete", checkLogin);
    };
  }, [router.events]);

  const handleLogout = async () => {
    try {
      setLoading(true);
      await loginAPI.logout();
    } finally {
      localStorage.removeItem("authToken");
      setIsLogin(false);
      setLoading(false);
      router.push("/");
    }
  };

  return (
    <header className={styles.navbar}>
      <div className={styles.navInner}>
        <Link className={styles.logo} href="/">
          {homeContent.brand}
        </Link>

        <nav className={styles.nav}>
          <Link href="/rent">工具</Link>
          <Link href="/teach">課程</Link>
          <Link href="/share">分享</Link>
        </nav>

        {isLogin ? (
          <button
            type="button"
            className={styles.loginButton}
            onClick={handleLogout}
            disabled={loading}
          >
            {loading ? "登出中..." : "登出"}
          </button>
        ) : (
          <Link className={styles.loginButton} href="/login">
            登入
          </Link>
        )}
      </div>
    </header>
  );
}