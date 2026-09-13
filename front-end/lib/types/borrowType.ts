export interface BorrowInput {
  rentId: number;
  dueDate: string;
}

export interface BorrowResponse {
  result: boolean;
  errorCode: string;
  message: string;
}

export interface BorrowStats {
    totalBorrowCount: number;
    activeBorrowCount: number;
    returnCount: number;
    overdueCount: number;
}