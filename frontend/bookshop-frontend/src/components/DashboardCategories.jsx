import {
    MDBBtn,
    MDBIcon,
    MDBModal,
    MDBModalBody,
    MDBModalContent,
    MDBModalDialog,
    MDBModalFooter,
    MDBModalHeader,
    MDBModalTitle,
    MDBTable,
    MDBTableBody,
    MDBTableHead
} from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import axios from "axios";
import { showErrorMessage } from "./ErrorMessage.jsx";

export default function DashboardCategories() {
    const [search, setSearch] = useState('');
    const [categoryList, setCategoryList] = useState([]);
    const [newCategory, setNewCategory] = useState('');
    const [addCategoryModalOpen, setAddCategoryModalOpen] = useState(false);
    const [editCategoryModalOpen, setEditCategoryModalOpen] = useState(false);
    const [editCategoryId, setEditCategoryId] = useState(0);
    const [editCategoryName, setEditCategoryName] = useState('');

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            // const response = await axios.get('http://localhost:8080/categories');
            // const response = await fetch('http://localhost:8080/categories',
            //     {
            //         method:'GET',
            //         mode: 'no-cors',
            //         headers:{
            //             'Content-type':'application/json',
            //             'Authorization':'Bearer ' + localStorage.getItem("accessToken")
            //         },})

            var myHeaders = new Headers();
            myHeaders.append("Authorization", "Bearer " + localStorage.getItem("accessToken"));
            myHeaders.append("Content-Type", "application/json");

            var requestOptions = {
                method: 'GET',
                headers: myHeaders,
                redirect: 'follow'
            };

            const response = await fetch("http://localhost:8080/categories", requestOptions)
            setCategoryList(response.data);
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const addCategory = async () => {
        if (!newCategory) {
            showErrorMessage('Wprowadź nazwę kategorii');
            return;
        }

        try {
            await axios.post('http://localhost:8080/categories', {
                category: newCategory
            });
            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas dodawania kategorii: ' + error);
        }
    };

    const toggleAddCategoryModal = () => setAddCategoryModalOpen(!addCategoryModalOpen);
    const toggleEditCategoryModal = () => setEditCategoryModalOpen(!editCategoryModalOpen);

    const showEditCategoryDialog = (categoryId, categoryName) => {
        setEditCategoryId(categoryId);
        setEditCategoryName(categoryName);
        toggleEditCategoryModal();
    };

    const editCategory = async () => {
        toggleEditCategoryModal();

        if (!editCategoryName) {
            showErrorMessage('Wprowadź nazwę kategorii');
            return;
        }

        try {
            await axios.put(`http://localhost:8080/categories/${editCategoryId}`, {
                category: editCategoryName
            });
            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas edycji kategorii: ' + error);
        }
    };

    const deleteCategory = async (categoryId) => {
        try {
            await axios.delete(`http://localhost:8080/categories/${categoryId}`);
            fetchData();
        } catch (error) {
            showErrorMessage('Błąd podczas usuwania kategorii: ' + error);
        }
    };

    return (
        <>
            <div className="mb-3">
                <input
                    className="form-control mb-3"
                    type="text"
                    placeholder="Szukaj kategorii..."
                    onChange={(e) => setSearch(e.target.value.toLowerCase())}
                />
                <MDBBtn color="dark" onClick={toggleAddCategoryModal}>
                    <MDBIcon fas icon="plus-circle" className="me-2" />
                    Dodaj kategorię
                </MDBBtn>
            </div>

            <div className="table-responsive">
                <MDBTable align="middle" className="table-hover">
                    <MDBTableHead light>
                        <tr>
                            <th scope="col">Nazwa kategorii</th>
                            <th scope="col"></th>
                        </tr>
                    </MDBTableHead>
                    <MDBTableBody>
                        {categoryList && categoryList
                            .filter((category) => {
                                return search.trim() === ''
                                    ? category
                                    : category.category.toLowerCase().includes(search.trim());
                            })
                            .map((category, i) => {
                                return (
                                    <tr key={i}>
                                        <td>{category.category}</td>
                                        <td className="text-center">
                                            <MDBBtn
                                                outline
                                                rounded
                                                color="success"
                                                size="sm"
                                                className="m-1"
                                                onClick={() =>
                                                    showEditCategoryDialog(category.id, category.category)
                                                }
                                            >
                                                <MDBIcon icon="edit" className="me-2" />
                                                Edytuj
                                            </MDBBtn>
                                            <MDBBtn
                                                outline
                                                rounded
                                                color="danger"
                                                size="sm"
                                                className="m-1"
                                                onClick={() => deleteCategory(category.id)}
                                            >
                                                <MDBIcon icon="trash" className="me-2" />
                                                Usuń
                                            </MDBBtn>
                                        </td>
                                    </tr>
                                );
                            })}
                    </MDBTableBody>
                </MDBTable>
            </div>

            <MDBModal show={addCategoryModalOpen} setShow={setAddCategoryModalOpen} tabIndex="-1">
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Dodaj kategorię</MDBModalTitle>
                        </MDBModalHeader>
                        <MDBModalBody>
                            <div className="mb-3">
                                <label htmlFor="newCategory" className="form-label">
                                    Nazwa kategorii:
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="newCategory"
                                    value={newCategory}
                                    onChange={(e) => setNewCategory(e.target.value)}
                                />
                            </div>
                        </MDBModalBody>
                        <MDBModalFooter>
                            <MDBBtn color="link" onClick={toggleAddCategoryModal}>
                                Anuluj
                            </MDBBtn>
                            <MDBBtn color="success" onClick={addCategory}>
                                Zapisz
                            </MDBBtn>
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>

            <MDBModal show={editCategoryModalOpen} setShow={setEditCategoryModalOpen} tabIndex="-1">
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Edytuj kategorię</MDBModalTitle>
                        </MDBModalHeader>
                        <MDBModalBody>
                            <div className="mb-3">
                                <label htmlFor="editCategory" className="form-label">
                                    Nazwa kategorii:
                                </label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="editCategory"
                                    value={editCategoryName}
                                    onChange={(e) => setEditCategoryName(e.target.value)}
                                />
                            </div>
                        </MDBModalBody>
                        <MDBModalFooter>
                            <MDBBtn color="link" onClick={toggleEditCategoryModal}>
                                Anuluj
                            </MDBBtn>
                            <MDBBtn color="success" onClick={editCategory}>
                                Zapisz
                            </MDBBtn>
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>
        </>
    );
}
