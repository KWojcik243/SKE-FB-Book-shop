import {
    MDBBadge,
    MDBBtn,
    MDBBtnGroup,
    MDBIcon,
    MDBInput,
    MDBInputGroup,
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
import React, { useEffect, useState } from "react";
import axios from "axios";
import { showErrorMessage } from "./ErrorMessage.jsx";
import { Rating } from "@smastrom/react-rating";
import { MultiSelect } from "react-multi-select-component";


export default function DashboardBooks() {
    const [search, setSearch] = useState('');

    const [activeBook, setActiveBook] = useState({});
    const [deleteDialogVisible, setDeleteDialogVisible] = useState(false);
    const [modifyDialogVisible, setModifyDialogVisible] = useState(false);
    const [modifyDialogEdit, setModifyDialogEdit] = useState(false);
    const [modifyData, setModifyData] = useState({});
    const [bookList, setBookList] = useState([]);
    const [categoryList, setCategoryList] = useState([]);
    const [authorList, setAuthorList] = useState([]);
    const [authorsSelect, setAuthorsSelect] = useState([]);
    const [authorsSelected, setAuthorsSelected] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            let response = await axios.get('http://localhost:8080/books');
            setBookList(response.data);
            response = await axios.get('http://localhost:8080/categories');
            setCategoryList(response.data);
            response = await axios.get('http://localhost:8080/authors');
            setAuthorList(response.data);

            let authors = [];
            for (const author of authorList) {
                authors.push({ label: author.name + " " + author.surname, value: author.id });
            }
            setAuthorsSelect(authors);
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const toggleDeleteDialog = () => setDeleteDialogVisible(!deleteDialogVisible);
    const toggleModifyDialog = () => {
        setModifyDialogVisible(!modifyDialogVisible);

        const stars = document.getElementById('book-rating').getElementsByClassName('rr--svg');
        for (const star of stars) {
            star.setAttribute('viewBox', '-1 -1 27 25.81');
        }
    }

    const deleteBook = async () => {
        toggleDeleteDialog();

        try {
            const response = await axios.delete(`http://localhost:8080/books/${activeBook.id}`);
            setBookList(response.data);
            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas usuwania książki: ' + error);
        }
    };

    const addBook = () => {
        toggleModifyDialog();


    };

    const changeBook = () => {
        toggleModifyDialog();


    };

    const deleteDialog = <>
        <MDBModal show={deleteDialogVisible} setShow={setDeleteDialogVisible} tabIndex='-1'>
            <MDBModalDialog>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>Usuwanie</MDBModalTitle>
                        <MDBBtn className='btn-close' color='none' onClick={toggleDeleteDialog}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>Czy na pewno chcesz usunąć tę książkę?</MDBModalBody>

                    <MDBModalFooter>
                        <MDBBtn color='link' onClick={toggleDeleteDialog}>Anuluj</MDBBtn>
                        <MDBBtn color="danger" onClick={deleteBook}>Usuń</MDBBtn>
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
    </>;

    const modifyDialog = <>
        <MDBModal staticBackdrop show={modifyDialogVisible} setShow={setModifyDialogVisible} tabIndex='-1'>
            <MDBModalDialog centered>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>Książka</MDBModalTitle>
                        <MDBBtn className='btn-close' color='none' onClick={toggleModifyDialog}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>
                        <form>
                            <label className="form-label">Dane podstawowe:</label>
                            <MDBInput className='mb-3' labelClass='col-form-label' label='Tytuł' value={activeBook.title ? activeBook.title : ""} onChange={(e) => setActiveBook({ ...activeBook, title: e.target.value })}></MDBInput>
                            <MDBInput className='mb-3' labelClass='col-form-label' label='Adres URL okładki' type="url" value={activeBook.pngPath ? activeBook.pngPath : ""} onChange={(e) => setActiveBook({ ...activeBook, pngPath: e.target.value })}></MDBInput>
                            <MDBInput className='mb-3' labelClass='col-form-label' label='Grupa wiekowa' min={0} max={150} type='number' value={activeBook.ageGroup ? activeBook.ageGroup : 0} onChange={(e) => setActiveBook({ ...activeBook, ageGroup: e.target.value })}></MDBInput>
                            <MDBInput className='mb-3' labelClass='col-form-label' label='ISBN' min={0} type='number' value={activeBook.isbn ? activeBook.isbn : 0} onChange={(e) => setActiveBook({ ...activeBook, isbn: e.target.value })}></MDBInput>
                            <MDBInput className='mb-3' labelClass='col-form-label' label='Ilość' min={0} type='number' value={activeBook.amount ? activeBook.amount : 0} onChange={(e) => setActiveBook({ ...activeBook, amount: e.target.value })}></MDBInput>

                            <div className='mb-3 d-flex justify-content-start align-items-center'>
                                <span className="me-3 form-label mb-0">Ocena: </span>
                                <div style={{ width: "50%" }}>
                                    <Rating id="book-rating" value={activeBook.rating ? activeBook.rating : 0} onChange={(e) => setActiveBook({ ...activeBook, rating: e })} />
                                </div>
                            </div>

                            <label className="form-label">Kategoria:</label>
                            <select className="form-select mb-3" value={activeBook.categoryId} onChange={(e) => setActiveBook({ ...activeBook, categoryId: e.target.value })}>
                                {categoryList.map((category, i) => {
                                    return <option key={i} value={category.id}>{category.category}</option>
                                })}
                            </select>

                            <label className="form-label">Autorzy:</label>
                            <MultiSelect
                                options={authorsSelect}
                                overrideStrings={{
                                    "allItemsAreSelected": "Wszyscy autorzy są wybrani.",
                                    "clearSearch": "Wyczyść wyszukiwanie",
                                    "clearSelected": "Wyczyść zaznaczenie",
                                    "noOptions": "Nie odnaleziono żadnych autorów",
                                    "search": "Szukaj autorów...",
                                    "selectAll": "Zaznacz wszystko",
                                    "selectAllFiltered": "Zaznacz wszystko (filtrowane)",
                                    "selectSomeItems": "Wybierz autorów książki...",
                                    "create": "Utwórz",
                                }}
                                labelledBy="Autorzy"
                                value={authorsSelected}
                                onChange={setAuthorsSelected}>
                            </MultiSelect>
                        </form>
                    </MDBModalBody>

                    <MDBModalFooter>
                        <MDBBtn color='link' onClick={toggleModifyDialog}>Anuluj</MDBBtn>
                        {!modifyDialogEdit && <MDBBtn color="success" onClick={addBook}>Dodaj książkę</MDBBtn>}
                        {modifyDialogEdit && <MDBBtn color="success" onClick={changeBook}>Zmień książkę</MDBBtn>}
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
    </>;

    const showDeleteDialog = (book) => {
        setActiveBook(book);
        toggleDeleteDialog();
    };

    const showModifyDialog = (edit, book = {}) => {
        setModifyDialogEdit(edit);

        setActiveBook(book);
        setAuthorsSelected([]); // zrobić ładowanie autorów

        toggleModifyDialog();
    };

    return (<>
        <div className="mb-3">
            <input className='form-control mb-3' type='text' placeholder="Szukaj książki..." onChange={(e) => setSearch(e.target.value.toLowerCase())} />

            <MDBBtnGroup>
                <MDBBtn color="dark" onClick={() => showModifyDialog(false)}><MDBIcon fas icon="plus-circle" className="me-2" />Dodaj książkę</MDBBtn>
            </MDBBtnGroup>
        </div>

        <div className="table-responsive">
            <MDBTable align='middle' className="table-hover">
                <MDBTableHead light>
                    <tr>
                        <th scope='col'>Tytuł</th>
                        <th scope='col'>Autor</th>
                        <th scope='col'>Kategoria</th>
                        <th scope='col'>ISBN</th>
                        <th scope='col'></th>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    {bookList.filter((item) => {
                        const authors = item.authors ? item.authors.map((author) => author.name + " " + author.surname).join(', ') : "";
                        return search.trim() === '' ? item : (item.title + authors + item.category + item.isbn).toLowerCase().includes(search.trim());
                    }).map((book, i) => {
                        const authors = book.authors ? book.authors.map((author) => author.name + " " + author.surname).join(', ') : "";
                        return (<tr key={i}>
                            <td>
                                <p className='fw-bold mb-1'>{book.title}</p>
                            </td>
                            <td>
                                <p className='fw-normal mb-1'>{authors}</p>
                            </td>
                            <td>{book.category}</td>
                            <td>{book.isbn}</td>
                            <td className="text-center">
                                <MDBBtn outline rounded color='success' size='sm' className="m-1" onClick={() => showModifyDialog(true, book)}><MDBIcon fas icon="pen" className="me-2" />Edytuj</MDBBtn>
                                <MDBBtn outline rounded color='danger' size='sm' className="m-1" onClick={() => showDeleteDialog(book)}><MDBIcon fas icon="trash" className="me-2" />Usuń</MDBBtn>
                            </td>
                        </tr>);
                    })}
                </MDBTableBody>
            </MDBTable>
        </div>

        {deleteDialog}
        {modifyDialog}
    </>);
}