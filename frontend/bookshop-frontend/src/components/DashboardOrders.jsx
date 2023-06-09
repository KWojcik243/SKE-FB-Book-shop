import { MDBBtn, MDBIcon, MDBModal, MDBModalBody, MDBModalContent, MDBModalDialog, MDBModalFooter, MDBModalHeader, MDBModalTitle, MDBTable, MDBTableBody, MDBTableHead } from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import axios from 'axios';
import { showErrorMessage } from "./ErrorMessage";

export default function DashboardOrders() {
    const [search, setSearch] = useState('');
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        changeActiveStatusData();
    }, []);
    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/orders');
            setOrders(response.data);
        } catch (error) {
            showErrorMessage('Wystąpił błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    // const orders = [
    //     { id: 0, user: "X XXX", status: "1" },
    //     { id: 10, user: "Xcff XXX", status: "2" },
    // ];

    const [statusDialogVisible, setStatusDialogVisible] = useState(false);
    const [activeStatusValue, setActiveStatusValue] = useState('');
    const [activeOrderId, setActiveOrderId] = useState(0);

    const toggleStatusDialog = () => setStatusDialogVisible(!statusDialogVisible);

    const changeActiveStatusData = async () => {

    };

    const changeActiveStatus = async () => {
        await changeActiveStatusData();
        toggleStatusDialog();
    };

    const statusDialog = <>
        <MDBModal show={statusDialogVisible} setShow={setStatusDialogVisible} tabIndex='-1'>
            <MDBModalDialog>
                <MDBModalContent>
                    <MDBModalHeader>
                        <MDBModalTitle>Status zamówienia</MDBModalTitle>
                        <MDBBtn className='btn-close' color='none' onClick={toggleStatusDialog}></MDBBtn>
                    </MDBModalHeader>
                    <MDBModalBody>
                        <p>Zmień status zamówienia:</p>
                        <input className='form-control' type='text' value={activeStatusValue} onChange={(e) => setActiveStatusValue(e.target.value)} />
                    </MDBModalBody>

                    <MDBModalFooter>
                        <MDBBtn color='link' onClick={toggleStatusDialog}>Anuluj</MDBBtn>
                        <MDBBtn color="success" onClick={changeActiveStatus}>Aktualizuj</MDBBtn>
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
    </>;

    const changeStatus = (status, id) => {
        setActiveStatusValue(status);
        setActiveOrderId(id);
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
                    }).map((order, i) => {
                        return (<tr key={i}>
                            <td>{order.id}</td>
                            <td>{order.user}</td>
                            <td className="text-center">
                                <MDBBtn outline rounded color='success' size='sm' className="m-1" onClick={() => changeStatus(order.status, order.id)}><MDBIcon fas icon="exchange-alt" className='me-2' />Zmień</MDBBtn>
                            </td>
                        </tr>);
                    })}
                </MDBTableBody>
            </MDBTable>
        </div>
        {statusDialog}
    </>);
}