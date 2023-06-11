import {
    MDBBtn,
    MDBIcon,
    MDBModal,
    MDBModalBody, MDBModalContent,
    MDBModalDialog,
    MDBModalFooter,
    MDBModalHeader,
    MDBModalTitle,
    MDBTable,
    MDBTableBody,
    MDBTableHead
} from "mdb-react-ui-kit";
import {useEffect, useState} from "react";
import axios from "axios";
import {showErrorMessage} from "./ErrorMessage.jsx";

export default function DashboardAuthors() {
    const [search, setSearch] = useState('');
    const [authorList, setAuthorList] = useState([]);
    const [newAuthorName, setNewAuthorName] = useState('');
    const [newAuthorSurname, setNewAuthorSurname] = useState('');
    const [addAuthorModalOpen, setAddAuthorModalOpen] = useState(false);
    const [editAuthorModalOpen, setEditAuthorModalOpen] = useState(false);
    const [editAuthorId, setEditAuthorId] = useState(0);
    const [editAuthorName, setEditAuthorName] = useState('');
    const [editAuthorSurname, setEditAuthorSurname] = useState('');

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/authors');
            setAuthorList(response.data);
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const addAuthor = async () => {
        if (!newAuthorName || !newAuthorSurname) {
            showErrorMessage('Wprowadź imię i nazwisko autora');
            return;
        }

        try {
            await axios.post(`http://localhost:8080/authors?name=${newAuthorName}&surname=${newAuthorSurname}`);
            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas dodawania autora: ' + error);
        }
    };

    const toggleAddAuthorModal = () => setAddAuthorModalOpen(!addAuthorModalOpen);
    const toggleEditAuthorModal = () => setEditAuthorModalOpen(!editAuthorModalOpen);

    const showEditAuthorDialog = (authorId, authorName, authorSurname) => {
        setEditAuthorId(authorId);
        setEditAuthorName(authorName);
        setEditAuthorSurname(authorSurname);

        toggleEditAuthorModal();
    };

    const editAuthor = async () => {
        toggleEditAuthorModal();

        if (!editAuthorName || !editAuthorSurname) {
            showErrorMessage('Wprowadź imię i nazwisko autora');
            return;
        }

        try {
            await axios.put(`http://localhost:8080/authors/${editAuthorId}`, {
                name: editAuthorName,
                surname: editAuthorSurname
            });
            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas edycji autora: ' + error);
        }
    };

    const deleteAuthor = async (authorId) => {
        try {
            await axios.delete(`http://localhost:8080/authors/${authorId}`);
            fetchData();
        } catch (error) {
            showErrorMessage('Błąd podczas usuwania autora: ' + error);
        }
    };

    return (
        <>
            <div className="mb-3">
                <input className='form-control mb-3' type='text' placeholder="Szukaj autora..."
                       onChange={(e) => setSearch(e.target.value.toLowerCase())}/>
                <MDBBtn color="dark" onClick={toggleAddAuthorModal}><MDBIcon fas icon="plus-circle" className="me-2" />Dodaj autora</MDBBtn>
            </div>

            <div className="table-responsive">
                <MDBTable align='middle' className="table-hover">
                    <MDBTableHead light>
                        <tr>
                            <th scope='col'>Imię</th>
                            <th scope='col'>Nazwisko</th>
                            <th scope='col'></th>
                        </tr>
                    </MDBTableHead>
                    <MDBTableBody>
                        {authorList.filter((author) => {
                            return search.trim() === '' ? author : (author.name + author.surname).toLowerCase().includes(search.trim());
                        }).map((author, i) => {
                            return (
                                <tr key={i}>
                                    <td>{author.name}</td>
                                    <td>{author.surname}</td>
                                    <td className="text-center">
                                        <MDBBtn outline rounded color="success" size="sm" className="m-1"
                                                onClick={() => showEditAuthorDialog(author.id, author.name, author.surname)}>
                                            <MDBIcon icon="edit" className="me-2" />Edytuj
                                        </MDBBtn>
                                        <MDBBtn outline rounded color="danger" size="sm" className="m-1" onClick={() => deleteAuthor(author.id)}>
                                            <MDBIcon icon="trash" className="me-2" />Usuń
                                        </MDBBtn>
                                    </td>
                                </tr>
                            );
                        })}
                    </MDBTableBody>
                </MDBTable>
            </div>

            <MDBModal show={addAuthorModalOpen} setShow={setAddAuthorModalOpen} tabIndex='-1'>
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Dodaj autora</MDBModalTitle>
                        </MDBModalHeader>
                        <MDBModalBody>
                            <div className="mb-3">
                                <label htmlFor="newAuthorName" className="form-label">Imię:</label>
                                <input type="text" className="form-control" id="newAuthorName" value={newAuthorName}
                                       onChange={(e) => setNewAuthorName(e.target.value)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="newAuthorSurname" className="form-label">Nazwisko:</label>
                                <input type="text" className="form-control" id="newAuthorSurname" value={newAuthorSurname}
                                       onChange={(e) => setNewAuthorSurname(e.target.value)}/>
                            </div>
                        </MDBModalBody>
                        <MDBModalFooter>
                            <MDBBtn color="link" onClick={toggleAddAuthorModal}>Anuluj</MDBBtn>
                            <MDBBtn color="success" onClick={addAuthor}>Zapisz</MDBBtn>
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>

            <MDBModal show={editAuthorModalOpen} setShow={setEditAuthorModalOpen} tabIndex='-1'>
                <MDBModalDialog>
                    <MDBModalContent>
                        <MDBModalHeader>
                            <MDBModalTitle>Edytuj autora</MDBModalTitle>
                        </MDBModalHeader>
                        <MDBModalBody>
                            <div className="mb-3">
                                <label htmlFor="editAuthorName" className="form-label">Imię:</label>
                                <input type="text" className="form-control" id="editAuthorName" value={editAuthorName}
                                       onChange={(e) => setEditAuthorName(e.target.value)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="editAuthorSurname" className="form-label">Nazwisko:</label>
                                <input type="text" className="form-control" id="editAuthorSurname" value={editAuthorSurname}
                                       onChange={(e) => setEditAuthorSurname(e.target.value)}/>
                            </div>
                        </MDBModalBody>
                        <MDBModalFooter>
                            <MDBBtn color="link" onClick={toggleEditAuthorModal}>Anuluj</MDBBtn>
                            <MDBBtn color="success" onClick={editAuthor}>Zapisz</MDBBtn>
                        </MDBModalFooter>
                    </MDBModalContent>
                </MDBModalDialog>
            </MDBModal>
        </>
    );
}
