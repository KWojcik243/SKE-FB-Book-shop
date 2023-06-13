import { MDBCol, MDBContainer, MDBIcon, MDBListGroup, MDBListGroupItem, MDBRadio, MDBRow, MDBTabs, MDBTabsContent, MDBTabsItem, MDBTabsLink, MDBTabsPane, MDBTooltip } from 'mdb-react-ui-kit';
import {useContext, useEffect, useState} from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import {AuthContext} from "../components/AuthContext.jsx";
import axios from "axios";
import {showErrorMessage} from "../components/ErrorMessage.jsx";
import LoadingPage from "./LoadingPage.jsx";

export default function BasketPage() {
    const { user, loading } = useContext(AuthContext);

    if (loading)
        return <LoadingPage />;

    const isUser = user && user.role !== '[ADMIN]';

    if (!isUser)
        return <Navigate to="/"></Navigate>;

    const [basket, setBasket] = useState([]);

    const [paymentType, setPaymentType] = useState(0);
    const [correspondence, setCorrespondence] = useState({address: "", city: "", postCode: ""})

    const handlePaymentType = (value) => {
        if (value === paymentType) return;

        setPaymentType(value);
    };

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/carts/' + user.email);

            let items = [];
            for (const [key, value] of Object.entries(response.data.cartItems)) {
                const book = await axios.get(`http://localhost:8080/books/${key}`);
                items.push({...book.data, quantity: value});
            }

            setBasket(items);
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const deleteBookFromBasket = async (id) => {
        try {
            const response = await axios.post('http://localhost:8080/carts/' + user.email, {bookId: id, quantity: 0});
            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas wysyłania danych do serwera: ' + error);
        }
    }

    const createOrder = async () => {
        try {
            const response = await axios.post('http://localhost:8080/orders/', {...correspondence, userEmail: user.email, status: "nowe", paymentType: paymentType});

            for (const book of basket) {
                await axios.put(`http://localhost:8080/orders/${response.data.body}/items`, {bookId: book.id, quantity: book.quantity});
            }

            window.location.reload();
        } catch (error) {
            showErrorMessage('Błąd podczas wysyłania danych do serwera: ' + error);
        }
    }

    if (!basket || basket.length < 1)
        return (<>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6' className='text-center'>
                        <h2 className='text-center mb-5'>Twój koszyk</h2>
                        <p>Twój koszyk jest pusty. Dodaj do niego książki, aby móc złożyć zamówienie.</p>
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
                    <h2 className='text-center mb-5'>Twój koszyk</h2>

                    <MDBListGroup light numbered className='mb-3'>
                        {basket && basket.map((book, i) => {
                            return (<MDBListGroupItem key={i} className='d-flex justify-content-between align-items-start'>
                                <div className='ms-2 me-auto'>
                                    <div className='fw-bold'>{book.title}</div>{book.authors && book.authors.map((author) => author.name + " " + author.surname).join(', ')}
                                </div>

                                <MDBTooltip wrapperProps={{ color: "danger", floating: true, onClick: () => deleteBookFromBasket(book.id) }} placement='top' title='Usuń z koszyka'>
                                    <MDBIcon fas icon="trash" />
                                </MDBTooltip>
                            </MDBListGroupItem>)
                        })}
                    </MDBListGroup>

                    <h2 className='text-center mb-5'>Złóż zamówienie</h2>

                    <h4 className="mb-3">Dane korespondencyjne</h4>

                    <form className="needs-validation" noValidate onSubmit={createOrder}>
                        <div className="row g-3">
                            <div className="col-12">
                                <label htmlFor="address" className="form-label">Adres</label>
                                <input type="text" className="form-control" id="address" placeholder="" required="" value={correspondence.address} onChange={ e => setCorrespondence({...correspondence, address: e.target.value})} />
                                <div className="invalid-feedback">
                                    Podaj swój adres.
                                </div>
                            </div>

                            <div className="col-sm-6">
                                <label htmlFor="address" className="form-label">Miejscowość</label>
                                <input type="text" className="form-control" id="city" placeholder="" required="" value={correspondence.city} onChange={ e => setCorrespondence({...correspondence, city: e.target.value})} />
                                <div className="invalid-feedback">
                                    Podaj miejscowość.
                                </div>
                            </div>

                            <div className="col-sm-6">
                                <label htmlFor="zip" className="form-label">Kod pocztowy</label>
                                <input type="text" className="form-control" id="zip" placeholder="" required="" value={correspondence.postCode} onChange={ e => setCorrespondence({...correspondence, postCode: e.target.value})} />
                                <div className="invalid-feedback">
                                    Podaj poprawny kod pocztowy.
                                </div>
                            </div>
                        </div>

                        <h4 className="mb-3 mt-4">Płatność</h4>

                        <MDBRow>
                            <MDBCol size='3'>
                                <MDBTabs className='flex-column text-start fw-bold gap-3'>
                                    <MDBRadio name="payment-delivery" id='payment-delivery-radio' label="Gotówką przy odbiorze" onChange={() => handlePaymentType(0)} onClick={() => handlePaymentType(0)} checked={paymentType === 0}></MDBRadio>
                                    <MDBRadio name="payment-card" id='payment-card-radio' label={"Kartą przy odbiorze"} onChange={() => handlePaymentType(1)} onClick={() => handlePaymentType(1)} checked={paymentType === 1}></MDBRadio>
                                </MDBTabs>
                            </MDBCol>
                            <MDBCol size='9'>
                                <MDBTabsContent>
                                    <MDBTabsPane show={paymentType === 0}>
                                        Płatność <strong>gotówką</strong> zostanie zrealizowana przy odbiorze.
                                    </MDBTabsPane>
                                    <MDBTabsPane show={paymentType === 1}>
                                        Płatność <strong>kartą</strong> zostanie zrealizowana przy odbiorze.
                                    </MDBTabsPane>
                                </MDBTabsContent>
                            </MDBCol>
                        </MDBRow>

                        <hr className="my-4"></hr>

                        <button className="w-100 btn btn-success btn-lg" type="submit"><MDBIcon fas icon="truck" className='me-2' />Zamów książki</button>
                    </form>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}