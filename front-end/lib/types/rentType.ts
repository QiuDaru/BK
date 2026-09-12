export type RentCategoryId = 1 | 2 | 3 | 4;

export interface RentCategory {
  id: RentCategoryId;
  name: string;
}

export const rentCategories: RentCategory[] = [
  { id: 1, name: "家具" },
  { id: 2, name: "廚具" },
  { id: 3, name: "五金" },
  { id: 4, name: "其他" },
];

export interface RentItem {
  id: number;
  item: string;
  categoryName: string;
  photoLink: string | null;
  remark: string | null;
}

export interface RentListResponse {
  result: boolean;
  errorCode: string;
  message: string;
  data: RentItem[];
}

export interface RentDetailResponse {
  result: boolean;
  errorCode: string;
  message: string;
  data: RentItem;
}

export type CreateRentInput = {
  year: number;
  categoryId: RentCategoryId;
  item: string;
  remark: string;
  photoLink: string;
};

export interface UpdateRentInput extends Partial<CreateRentInput> {
  id: number;
}

export interface RentIdParam {
  id: number;
}

export interface ActionResponse<T = null> {
  success: boolean;
  message: string;
  data?: T;
}
