import { MDBBadge, MDBBtn, MDBIcon, MDBInputGroup, MDBModal, MDBModalBody, MDBModalContent, MDBModalDialog, MDBModalFooter, MDBModalHeader, MDBModalTitle, MDBTable, MDBTableBody, MDBTableHead } from "mdb-react-ui-kit";
import { useState } from "react";


export default function DashboardBooks() {
    const [search, setSearch] = useState('');

    const books = [{
        id: 1,
        title: "Pan Tadeusz",
        rating: 5,
        author: "Adam Mickiewicz",
        category: "literatura polska",
        cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg",
        agegroup: 12,
        amount: 10,
        isbn: "12021-1221-12"
    }];

    const [activeBookId, setActiveBookId] = useState(0);
    const [deleteDialogVisible, setDeleteDialogVisible] = useState(false);
    const [modifyDialogVisible, setModifyDialogVisible] = useState(false);
    const [modifyDialogEdit, setModifyDialogEdit] = useState(false);
    const [modifyData, setModifyData] = useState({});

    const toggleDeleteDialog = () => setDeleteDialogVisible(!deleteDialogVisible);
    const toggleModifyDialog = () => setModifyDialogVisible(!modifyDialogVisible);

    const deleteBook = () => {
        //activeBookId...

        toggleDeleteDialog();
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
        <MDBModal show={modifyDialogVisible} setShow={setModifyDialogVisible} tabIndex='-1'>
            <MDBModalDialog>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>Książka</MDBModalTitle>
                        <MDBBtn className='btn-close' color='none' onClick={toggleModifyDialog}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>XXX</MDBModalBody>

                    <MDBModalFooter>
                        <MDBBtn color='link' onClick={toggleModifyDialog}>Anuluj</MDBBtn>
                        {!modifyDialogEdit && <MDBBtn color="success" onClick={addBook}>Dodaj książkę</MDBBtn>}
                        {modifyDialogEdit && <MDBBtn color="success" onClick={changeBook}>Zmień książkę</MDBBtn>}
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
    </>;

    const showDeleteDialog = (id) => {
        setActiveBookId(id);
        toggleDeleteDialog();
    };

    const showModifyDialog = (edit, id = 0) => {
        setModifyDialogEdit(edit);

        if (edit)
            setActiveBookId(id);

        toggleModifyDialog();
    };

    return (<>
        <div className="mb-3">
            <MDBInputGroup className='mb-3' textTag='div' noBorder textBefore={<MDBBtn color="dark" size="lg" onClick={() => showModifyDialog(false)}><MDBIcon fas icon="plus-circle" className="me-2" />Dodaj książkę</MDBBtn>}>
                <input className='form-control' type='text' placeholder="Szukaj książki..." onChange={(e) => setSearch(e.target.value.toLowerCase())} />
            </MDBInputGroup>
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
                    {books.filter((item) => {
                        return search.trim() === '' ? item : (item.title + item.author + item.category + item.isbn).toLowerCase().includes(search.trim());
                    }).map((book, i) => {
                        return (<tr key={i}>
                            <td>
                                <p className='fw-bold mb-1'>{book.title}</p>
                            </td>
                            <td>
                                <p className='fw-normal mb-1'>{book.author}</p>
                            </td>
                            <td>{book.category}</td>
                            <td>{book.isbn}</td>
                            <td className="text-center">
                                <MDBBtn outline rounded color='success' size='sm' className="m-1" onClick={() => showModifyDialog(true, book.id)}><MDBIcon fas icon="pen" className="me-2" />Edytuj</MDBBtn>
                                <MDBBtn outline rounded color='danger' size='sm' className="m-1" onClick={() => showDeleteDialog(book.id)}><MDBIcon fas icon="trash" className="me-2" />Usuń</MDBBtn>
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