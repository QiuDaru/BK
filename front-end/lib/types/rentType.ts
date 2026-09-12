export interface RentItem {
  id: number;
  item: string;
  categoryName: string;
  photoLink: string | null;
  remark: string | null;
}

export type CreateRentInput = Omit<RentItem, "id">;

export interface UpdateRentInput extends Partial<CreateRentInput> {
  id: number;
}

export interface RentIdParam {
  id: number;
}
