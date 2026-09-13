import { useEffect, useState } from "react";
import Layout from "@/components/Layout/Layout";
import styles from "@/styles/pages/teach/teach.module.scss"


export default function Teach() {
    const [ isAdmin, setIsAdmin] = useState(false);
//   useEffect(() => {
//     const authority =
//       typeof window !== "undefined" ? localStorage.getItem("authority") : null;
//     const canManageByRole = role === "Administrator" || role === "Manager";
//     const canManageByStorage =
//       authority === "Administrator" || authority === "Manager";
//     setIsAdmin(canManageByRole || canManageByStorage);
//   }, [role]);

//   const showMessage = (type: ModalTypes, message: string) => {
//     setModalProps({
//       type,
//       message,
//     });
//     setIsShow(true);
//   };

//   useEffect(() => {
//     fetchCampusData();
//   }, []);

//   const fetchCampusData = async () => {
//     try {
//       setLoading(true);
//       const response = await campusAPI.getAllCampus();
//       if (response.result) {
//         setCampusData(response.data);
//       }
//     } catch (error) {
//       showMessage(ModalTypes.ERROR, "網路連線異常，請稍後再試");
//     } finally {
//       setLoading(false);
//     }
//   };

//   const handleAddOpen = () => {
//     setModalMode("add");
//     setIsModalOpen(true);
//     setSelectedCampus(null);
//   };

//   const handleEditClick = (cSno: string) => {
//     setModalMode("edit");
//     setSelectedCampus(campusData.find((item) => item.cSno === cSno) || null);
//     setIsModalOpen(true);
//   };

//   const handleDeleteClick = async (cSno: string) => {
//     const isConfirmed = window.confirm("確定要刪除此成員嗎？");
//     if (!isConfirmed) return;

//     try {
//       setLoading(true);
//       const response = await campusAPI.deleteCampus(cSno);
//       if (response.result) {
//         showMessage(ModalTypes.SUCCESS, response.message || "刪除成功");
//         await fetchCampusData();
//       } else {
//         showMessage(ModalTypes.ERROR, response.message || "刪除失敗");
//       }
//     } catch (error: unknown) {
//       showMessage(ModalTypes.ERROR, getErrorMessage(error, "刪除失敗"));
//     } finally {
//       setLoading(false);
//     }
//   };

//   const handleRowClick = async (cSno: string) => {
//     setModalMode("view");
//     setSelectedCampus(campusData.find((item) => item.cSno === cSno) || null);
//     setIsModalOpen(true);
//   };

//   const handleConfirmModal = async (payload: CampusPayload) => {
//     if (!payload.title?.trim() || !payload.image) {
//       showMessage(ModalTypes.ERROR, "標題、附圖為必填欄位");
//       return;
//     }

//     const formData = new FormData();
//     formData.append("title", payload.title.trim());

//     formData.append("subTitle", payload.subTitle || "");
//     formData.append("data", payload.data || "");
//     formData.append("path", payload.path || "");
//     if (payload.image) {
//       formData.append("images", payload.image);
//     }

//     try {
//       setLoading(true);
//       if (modalMode === "add") {
//         const response = await campusAPI.createCampus(formData);
//         if (response.result) {
//           showMessage(ModalTypes.SUCCESS, response.message || "新增成功");
//           await fetchCampusData();
//           setIsModalOpen(false);
//         } else {
//           showMessage(ModalTypes.ERROR, response.message || "新增失敗");
//         }
//       }

//       if (modalMode === "edit" && selectedCampus) {
//         const response = await campusAPI.updateCampus(
//           selectedCampus.cSno,
//           formData,
//         );
//         if (response.result) {
//           showMessage(ModalTypes.SUCCESS, response.message || "修改成功");
//           await fetchCampusData();
//           setIsModalOpen(false);
//         } else {
//           showMessage(ModalTypes.ERROR, response.message || "修改失敗");
//         }
//       }
//     } catch (error: unknown) {
//       showMessage(ModalTypes.ERROR, getErrorMessage(error, "儲存失敗"));
//     } finally {
//       setLoading(false);
//     }
//   };

  return (
    <Layout>
        <></>
    </Layout>
  );
}
