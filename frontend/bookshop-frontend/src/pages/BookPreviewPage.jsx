import { Rating } from '@smastrom/react-rating';
import { MDBBtn, MDBBtnGroup, MDBCard, MDBCardBody, MDBCardImage, MDBCardText, MDBCardTitle, MDBCol, MDBContainer, MDBIcon, MDBRow, MDBTabs, MDBTabsContent, MDBTabsItem, MDBTabsLink, MDBTabsPane } from 'mdb-react-ui-kit';
import {useContext, useEffect, useState} from 'react';
import {Link, Navigate, useSearchParams} from 'react-router-dom';
import axios from "axios";
import {showErrorMessage} from "../components/ErrorMessage.jsx";
import {AuthContext} from "../components/AuthContext.jsx";


export default function BookPreviewPage() {
    const [searchParams, setSearchParams] = useSearchParams();
    const [tabsActive, setTabsActive] = useState('general');
    const [book, setBook] = useState({});
    const [bookFound, setBookFound] = useState(true);
    const { user } = useContext(AuthContext);

    const isAdmin = user && user.role === '[ADMIN]';

    const handleTabsClick = (value) => {
        if (value === tabsActive) return;
        setTabsActive(value);
    }

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/books/' + searchParams.get('id'));
            setBook(response.data);
            setBookFound(true);
        } catch (error) {
            setBookFound(false);
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const addToBasket = async (id) => {
        try {
            const response = await axios.post('http://localhost:8080/carts/' + user.email, {bookId: id, quantity: 1});
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    if (!bookFound)
        return <Navigate to='/catalog'></Navigate>;

    const authors = book.authors ? book.authors.map((author) => author.name + " " + author.surname).join(', ') : "";

    return (<>
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='2'></MDBCol>
                <MDBCol md='8'>
                    <Link to='/catalog'><MDBBtn color='light' className='mb-3'><MDBIcon fas icon="chevron-circle-left me-2" />Powrót do katalogu</MDBBtn></Link>
                    <br></br>
                    <MDBCard>
                        <MDBRow className='g-0'>
                            <MDBCol md='4'>
                                <MDBCardImage src={book.pngPath} alt='...' fluid onError={({currentTarget}) => {
                                    currentTarget.onerror = null;
                                    currentTarget.src = 'https://upload.wikimedia.org/wikipedia/commons/d/d1/Image_not_available.png?20210219185637';
                                }} />
                            </MDBCol>
                            <MDBCol md='8'>
                                <MDBCardBody>
                                    <MDBCardTitle>{book.title}</MDBCardTitle>
                                    <MDBCardText>
                                        <MDBTabs className='mb-3'>
                                            <MDBTabsItem>
                                                <MDBTabsLink onClick={() => handleTabsClick('general')} active={tabsActive === 'general'}>
                                                    <MDBIcon fas icon="scroll me-2" />Informacje ogólne
                                                </MDBTabsLink>
                                            </MDBTabsItem>
                                            <MDBTabsItem>
                                                <MDBTabsLink onClick={() => handleTabsClick('details')} active={tabsActive === 'details'}>
                                                    <MDBIcon fas icon="info-circle me-2" />Szczegóły
                                                </MDBTabsLink>
                                            </MDBTabsItem>
                                        </MDBTabs>

                                        <MDBTabsContent>
                                            <MDBTabsPane show={tabsActive === 'general'}>
                                                <strong>Autorzy: </strong>{authors}<br></br>

                                                <div className='w-100 d-flex justify-content-start my-3'>
                                                    <span><strong>Ocena:&nbsp;</strong></span>
                                                    <div style={{ maxWidth: "120px" }}>
                                                        <Rating className='mb-2' value={book.rating} readOnly={true} />
                                                    </div>
                                                </div>

                                                <strong>Grupa wiekowa: </strong>{book.ageGroup}<br></br>
                                                <strong>Kategoria: </strong>{book.category}<br></br>

                                            </MDBTabsPane>
                                            <MDBTabsPane show={tabsActive === 'details'}>
                                                <strong>ISBN: </strong>{book.isbn}<br></br>
                                                <strong>Ilość w magazynie: </strong>{book.amount}
                                            </MDBTabsPane>
                                        </MDBTabsContent>
                                        <div className='d-flex justify-content-end'>
                                            {!isAdmin && <MDBBtnGroup className='mt-4 text-end'>
                                            <MDBBtn color='dark' onClick={() => addToBasket(book.id)}><MDBIcon fas icon="cart-plus me-2" />Dodaj do koszyka</MDBBtn>
                                        </MDBBtnGroup>}
                                        </div>
                                    </MDBCardText>
                                </MDBCardBody>
                            </MDBCol>
                        </MDBRow>
                    </MDBCard>
                </MDBCol>
                <MDBCol md='2'></MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}