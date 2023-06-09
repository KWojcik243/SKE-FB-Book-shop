import { MDBBtn, MDBIcon, MDBModal, MDBModalBody, MDBModalContent, MDBModalDialog, MDBModalFooter, MDBModalHeader, MDBModalTitle, MDBTable, MDBTableBody, MDBTableHead } from "mdb-react-ui-kit";
import { useEffect, useState } from "react";
import axios from 'axios';
import { showErrorMessage } from "./ErrorMessage";

export default function DashboardOrders() {
    const ordersTest = [
        { id: 0, user: "X XXX", status: "1" },
        { id: 10, user: "Xcff XXX", status: "2" },
    ];

    const [search, setSearch] = useState('');
    const [orders, setOrders] = useState(ordersTest);

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

    const [statusDialogVisible, setStatusDialogVisible] = useState(false);
    const [activeOrder, setActiveOrder] = useState({});

    const toggleStatusDialog = () => setStatusDialogVisible(!statusDialogVisible);

    const changeActiveStatus = async () => {
        toggleStatusDialog();

        try {
            const response = await axios.put(`http://localhost:8080/orders/${activeOrder.id}`, activeOrder, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            setOrders(response.data);
            window.location.reload();
        } catch (error) {
            showErrorMessage('Wystąpił błąd podczas wysyłania danych do serwera: ' + error);
        }
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
                        <input className='form-control' type='text' value={activeOrder.status} onChange={(e) => setActiveOrder({...activeOrder, status: e.target.value}) } />
                    </MDBModalBody>

                    <MDBModalFooter>
                        <MDBBtn color='link' onClick={toggleStatusDialog}>Anuluj</MDBBtn>
                        <MDBBtn color="success" onClick={changeActiveStatus}>Aktualizuj</MDBBtn>
                    </MDBModalFooter>
                </MDBModalContent>
            </MDBModalDialog>
        </MDBModal>
    </>;

    const changeStatus = (order) => {
        setActiveOrder(order);
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
                        <th scope='col'>Ostatnia aktualizacja</th>
                        <th scope='col'>Status</th>
                    </tr>
                </MDBTableHead>
                <MDBTableBody>
                    {orders.filter((item) => {
                        const user = item.user ? item.user.name + " " + item.user.surname : "";
                        return search.trim() === '' ? item : (item.id + user + item.status).toLowerCase().includes(search.trim());
                    }).map((order, i) => {
                        return (<tr key={i}>
                            <td>{order.id}</td>
                            <td>{order.user && order.user.name + " " + order.user.surname}</td>
                            <td>{(new Date(order.lastStatusUpdate)).toLocaleString()}</td>
                            <td className="text-center">
                                <MDBBtn outline rounded color='success' size='sm' className="m-1" onClick={() => changeStatus(order)}><MDBIcon fas icon="exchange-alt" className='me-2' />Zmień</MDBBtn>
                            </td>
                        </tr>);
                    })}
                </MDBTableBody>
            </MDBTable>
        </div>
        {statusDialog}
    </>);
}