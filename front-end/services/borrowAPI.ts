import API from "@/lib/api/api";
import { BorrowResponse, BorrowInput } from "@/lib/types/borrowType";

const borrowAPI = {
  borrow: async (data: BorrowInput): Promise<BorrowResponse> => {
    const res = await API.post("/borrows", data);
    return res.data;
  },

  returnItem: async (rentId: number): Promise<BorrowResponse> => {
    const res = await API.patch(`/borrows/${rentId}/return`);
    return res.data;
  },

  getOverdue: async () => {
    const res = await API.get("/borrows/overdue");
    return res.data;
  },

  getStats: async () => {
    const res = await API.get("/borrows/stats");
    return res.data;
  },
};

export default borrowAPI;
