import API from "@/lib/api/api";
import { Response } from "@/lib/types/requestType";

const loginAPI = {
  login: async (
    account: string,
    password: string
  ): Promise<Response<unknown>> => {
    const res = await API.post("/login", {
      account,
      password,
    });

    const token = res.headers["x-auth-token"];

    if (token) {
      localStorage.setItem("authToken", token);
    }

    return res.data;
  },

  logout: async (): Promise<Response<unknown>> => {
    const res = await API.get("/logout");

    localStorage.removeItem("authToken");

    return res.data;
  },
};

export default loginAPI;