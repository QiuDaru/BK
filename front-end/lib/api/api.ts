import axios, { InternalAxiosRequestConfig, AxiosResponse } from 'axios';

const API = axios.create({
  // 這裡維持環境變數作為預設值
  baseURL: process.env.NEXT_PUBLIC_API_URL 
});

API.interceptors.request.use(function (config: InternalAxiosRequestConfig) {
  const hostname = window.location.hostname;

  // --- 動態 IP 切換邏輯開始 ---
  if (typeof window !== 'undefined') {
    
    // 判斷是否為機房內網 IP
    if (hostname === "172.17.202.181") {
      config.baseURL = "http://172.17.202.182:8080";
    } else if (hostname === "140.131.115.44") {
      config.baseURL = "http://140.131.115.44:50035";
    }
    
  }
  // --- 動態 IP 切換邏輯結束 ---

  if (!config.headers) {
    config.headers = new axios.AxiosHeaders();
  }
  
  // 如果是 FormData，不設置 Content-Type，讓 axios 自動處理
  const isFormData = config.data instanceof FormData;
  if (!isFormData && !config.headers['Content-Type']) {
    config.headers['Content-Type'] = 'application/json';
  }
  
  const token = localStorage.getItem('token');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  
  return config;
});

API.interceptors.response.use(
  (response: AxiosResponse) => {
    const authToken = response.headers['x-auth-token'];
    if (authToken) {
      localStorage.setItem('token', authToken);
    }
    return response; 
  },
  error => Promise.reject(error.response?.data ?? error)
);

export default API;