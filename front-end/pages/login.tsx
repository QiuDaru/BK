import Modal, { ModalTypes } from "@/components/common/Modal";
import { useState } from "react";
import { useRouter } from "next/router";

import accountAPI from "@/services/Login/loginAPI";

import styles from "@/styles/login.module.scss";
import { FaArrowRight, FaEye, FaEyeSlash, FaInfoCircle } from "react-icons/fa";

export default function Login() {
  const router = useRouter();
  const { redirect } = router.query;

  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [isShow, setIsShow] = useState(false);

  const [modalProps, setModalProps] = useState({
    type: ModalTypes.ERROR,
    message: "",
  });

  const handleLogin = async () => {
    if (!account || !password) {
      setModalProps({
        type: ModalTypes.ERROR,
        message: "請輸入帳號或密碼",
      });
      setIsShow(true);
      return;
    }

    setLoading(true);

    try {
      const response = await accountAPI.login(account, password);

      setModalProps({
        type: ModalTypes.SUCCESS,
        message: response.message || "登入成功！",
      });

      setIsShow(true);
    } catch (error: unknown) {
      let errorMessage = "登入失敗，請稍後再試。";

      if (error instanceof Error) {
        errorMessage = error.message;
      } else if (
        typeof error === "object" &&
        error !== null &&
        "message" in error
      ) {
        errorMessage = String(error.message);
      }

      setModalProps({
        type: ModalTypes.ERROR,
        message: errorMessage,
      });

      setIsShow(true);
    } finally {
      setLoading(false);
    }
  };



  return (
    <>
      <div className={styles.login}>
        <div className={styles.userLogin}>
          <div className={styles.noticeBanner}>
            <FaInfoCircle className={styles.noticeIcon} />
            <span>系統僅供里民使用</span>
          </div>

          <h1>登入</h1>

          <div className={styles.inputContain}>
            <div className={styles.editBox}>
              <input
                type="text"
                value={account}
                onChange={(e) => setAccount(e.target.value)}
                onKeyDown={(e) => {
                  if (e.key === "Enter") {
                    handleLogin();
                  }
                }}
                placeholder="請輸入帳號"
              />
              <p>帳號</p>
            </div>

            <div className={styles.editBox}>
              <div className={styles.passwordWrapper}>
                <input
                  type={showPassword ? "text" : "password"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  onKeyDown={(e) => {
                    if (e.key === "Enter") {
                      handleLogin();
                    }
                  }}
                  placeholder="請輸入密碼"
                />

                <button
                  type="button"
                  className={styles.passwordToggle}
                  onClick={() => setShowPassword((prev) => !prev)}
                >
                  {showPassword ? <FaEyeSlash /> : <FaEye />}
                </button>
              </div>

              <p>密碼</p>
            </div>

            <button
              className={styles.loginBtn}
              onClick={handleLogin}
              disabled={loading}
            >
              {loading ? "登入中..." : "登 入"}
            </button>

           
          </div>
        </div>
      </div>

      {isShow && (
        <Modal
          type={modalProps.type}
          message={modalProps.message}
          onClose={() => {
            setIsShow(false);

            if (modalProps.type === ModalTypes.SUCCESS) {
              const targetUrl = Array.isArray(redirect)
                ? redirect[0]
                : redirect;

              router.push(targetUrl || "/");
            }
          }}
        />
      )}
    </>
  );
}