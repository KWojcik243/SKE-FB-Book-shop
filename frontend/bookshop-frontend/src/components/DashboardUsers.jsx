import { MDBBtn, MDBIcon, MDBTable, MDBTableBody, MDBTableHead } from "mdb-react-ui-kit";
import {useEffect, useState} from "react";
import axios from "axios";
import {showErrorMessage} from "./ErrorMessage.jsx";


export default function DashboardUsers() {
    const [search, setSearch] = useState('');
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/auth/users');
            setUsers(response.data);
        } catch (error) {
            showErrorMessage('Wystąpił błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const makeAdmin = async (id) => {

    };

    const removeAdmin = async (id) => {

    };

    return (<>
        <div className="mb-3">
            <input className='form-control' type='text' placeholder="Szukaj użytkownika..." onChange={(e) => setSearch(e.target.value.toLowerCase())} />
        </div>
        <div className="table-responsive">
            <MDBTable align='middle' className="table-hover">
                <MDBTableHead light>
                    <tr>
                        <th scope='col'>Imię</th>
                        <th scope='col'>Nazwisko</th>
                        <th scope='col'>Uprawnienia administratora</th>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    {users.filter((item) => {
                        return search.trim() === '' ? item : (item.name + item.surname).toLowerCase().includes(search.trim());
                    }).map((u, i) => {
                        return (<tr key={i}>
                            <td>{u.name}</td>
                            <td>{u.surname}</td>
                            <td className="text-center">
                                {!u.isadmin && <MDBBtn outline rounded color='success' size='sm' className="m-1" onClick={() => makeAdmin(u.id)}><MDBIcon fas icon="user-check" className='me-2' />Nadaj</MDBBtn>}
                                {u.isadmin && <MDBBtn outline rounded color='danger' size='sm' className="m-1" onClick={() => removeAdmin(u.id)}><MDBIcon fas icon="user-times" className='me-2' />Odbierz</MDBBtn>}
                            </td>
                        </tr>);
                    })}
                </MDBTableBody>
            </MDBTable>
        </div>
    </>);
}