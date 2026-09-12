import API from '@/lib/api/api';
import { RentItem, CreateRentInput, UpdateRentInput } from '@/lib/types/rentType';
import { Response } from '@/lib/types/requestType';

const BASE_URL = '/rents';

const rentAPI = {
    getAllRent: (): Promise<Response<RentItem[]>> =>
        API.get(`${BASE_URL}`).then((res) => res.data),

    getRent: (id: string): Promise<Response<RentItem>> =>
        API.get(`${BASE_URL}/${id}`).then((res) => res.data),

    createRent: (formData: FormData): Promise<Response<CreateRentInput>> =>
        API.post(`${BASE_URL}`, formData).then((res) => res.data),

    deleteRent: (id: string): Promise<Response<null>> =>
        API.delete(`${BASE_URL}/${id}`).then((res) => res.data),

    updateRent: (id: string, formData: FormData): Promise<Response<UpdateRentInput>> =>
        API.patch(`${BASE_URL}/${id}`, formData).then((res) => res.data),

    toggleRentEnable: (id: string, enable: boolean): Promise<Response<null>> =>
        API.patch(`${BASE_URL}/${id}/enable`, null, { params: { enable } }).then((res) => res.data)
};

export default rentAPI;