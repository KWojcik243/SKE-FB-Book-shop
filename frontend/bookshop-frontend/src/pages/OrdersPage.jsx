import { MDBBadge, MDBCol, MDBContainer, MDBListGroup, MDBListGroupItem, MDBRow } from "mdb-react-ui-kit";
import { Navigate } from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../components/AuthContext.jsx";
import LoadingPage from "./LoadingPage.jsx";
import axios from "axios";
import {showErrorMessage} from "../components/ErrorMessage.jsx";


export default function OrdersPage() {
    const { user, loading } = useContext(AuthContext);

    if (loading)
        return <LoadingPage />;

    const isUser = user && user.role !== '[ADMIN]';

    if (!isUser)
        return <Navigate to="/"></Navigate>;

    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/orders/' + user.email);

            let items = [];
            // for (const [key, value] of Object.entries(response.data.cartItems)) {
            //     const book = await axios.get(`http://localhost:8080/books/${key}`);
            //     items.push({...book.data, quantity: value});
            // }

            setOrders(items);
            console.log(items);
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    if (orders.length < 1)
        return (<>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6'>
                        <h2 className='text-center mb-5'>Twoje zamówienia</h2>

                        <p>Brak zamówień.</p>
                    </MDBCol>
                    <MDBCol md='3'></MDBCol>
                </MDBRow>
            </MDBContainer>
        </>);

    return (<>
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='3'></MDBCol>
                <MDBCol md='6'>
                    <h2 className='text-center mb-5'>Twoje zamówienia</h2>

                    <MDBListGroup style={{ minWidth: '22rem' }} light>
                        {orders && orders.map((order, i) => {
                            return <MDBListGroupItem key={i} className='d-flex justify-content-between align-items-center'>
                                <div>
                                    <div className='fw-bold'>Zamówienie #{order.id}</div>
                                    <div className='text-muted'>{order.books.join(', ')}</div>
                                </div>
                                <div className="text-end">
                                    <MDBBadge pill light color='success'>
                                        {order.state}
                                    </MDBBadge>
                                    <br></br> 
                                    <MDBBadge pill light color='dark'>
                                        {order.lastUpdated}
                                    </MDBBadge>
                                </div>
                            </MDBListGroupItem>
                        })}
                    </MDBListGroup>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}