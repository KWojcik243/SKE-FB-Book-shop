import { MDBBtn, MDBIcon, MDBTable, MDBTableBody, MDBTableHead } from "mdb-react-ui-kit";
import { useState } from "react";


export default function DashboardOrders() {
    const [search, setSearch] = useState('');

    const orders = [
        {id: 0, user: "X XXX", status: 1},
        {id: 10, user: "Xcff XXX", status: 2},
    ];

    const [statusDialogVisible, setStatusDialogVisible] = useState(false);
    const [statusId, setStatusId] = useState(0);

    const toggleStatusDialog = () => setStatusDialogVisible(!statusDialogVisible);

    const statusDialog = <>
    
    </>;

    const changeStatus = (id) => {
        setStatusId(id);
        toggleStatusDialog();
    }

    return (<>
        <div className="mb-3">
            <input className='form-control' type='text' placeholder="Szukaj zamówienia..." onChange={(e) => setSearch(e.target.value.toLowerCase())} />
        </div>
        <div className="table-responsive">
            <MDBTable align='middle' className="table-hover">
                <MDBTableHead light>
                    <tr>
                        <th scope='col'>Numer</th>
                        <th scope='col'>Użytkownik</th>
                        <th scope='col'>Status</th>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    {orders.filter((item) => {
                        return search.trim() === '' ? item : (item.id + item.user).toLowerCase().includes(search.trim());
                    }).map((u, i) => {
                        return (<tr key={i}>
                            <td>{u.id}</td>
                            <td>{u.user}</td>
                            <td className="text-center">
                                <MDBBtn outline rounded color='success' size='sm' className="m-1" onClick={() => changeStatus(u.status)}><MDBIcon fas icon="exchange-alt" className='me-2' />Zmień</MDBBtn>
                            </td>
                        </tr>);
                    })}
                </MDBTableBody>
            </MDBTable>
        </div>
    </>);
}