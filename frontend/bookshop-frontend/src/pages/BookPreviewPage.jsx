import { Rating } from '@smastrom/react-rating';
import { MDBBtn, MDBBtnGroup, MDBCard, MDBCardBody, MDBCardImage, MDBCardText, MDBCardTitle, MDBCol, MDBContainer, MDBIcon, MDBRow, MDBTabs, MDBTabsContent, MDBTabsItem, MDBTabsLink, MDBTabsPane } from 'mdb-react-ui-kit';
import { useState } from 'react';
import { Link } from 'react-router-dom';


export default function BookPreviewPage() {
    const [tabsActive, setTabsActive] = useState('general');

    const handleTabsClick = (value) => {
        if (value == tabsActive) return;
        setTabsActive(value);
    }

    const book = {
        id: 0,
        title: "Pan Tadeusz",
        rating: 5,
        author: "Adam Mickiewicz", // list joined by commas
        category: "literatura polska",
        cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg",
        agegroup: 12,
        amount: 10,
        isbn: "12021-1221-12"
    };
    const userData = { isadmin: false };

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
                                <MDBCardImage src={book.cover} alt='...' fluid />
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
                                                <strong>Autor: </strong>{book.author}<br></br>

                                                <div className='w-100 d-flex justify-content-start my-3'>
                                                    <span><strong>Ocena:&nbsp;</strong></span>
                                                    <div style={{ maxWidth: "120px" }}>
                                                        <Rating className='mb-2' value={book.rating} readOnly={true} />
                                                    </div>
                                                </div>

                                                <strong>Grupa wiekowa: </strong>{book.agegroup}<br></br>
                                                <strong>Kategoria: </strong>{book.category}<br></br>

                                            </MDBTabsPane>
                                            <MDBTabsPane show={tabsActive === 'details'}>
                                                <strong>ISBN: </strong>{book.isbn}<br></br>
                                                <strong>Ilość w magazynie: </strong>{book.amount}
                                            </MDBTabsPane>
                                        </MDBTabsContent>
                                        <div className='d-flex justify-content-end'>
                                            {!userData.isadmin && <MDBBtnGroup className='mt-4 text-end'> {/*A dodano do koszyka?*/}
                                            <MDBBtn color='dark'><MDBIcon fas icon="cart-plus me-2" />Dodaj do koszyka</MDBBtn>
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