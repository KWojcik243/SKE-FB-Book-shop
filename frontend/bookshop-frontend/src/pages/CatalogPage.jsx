import { Rating } from '@smastrom/react-rating';
import { MDBBtn, MDBBtnGroup, MDBCard, MDBCardBody, MDBCardFooter, MDBCardImage, MDBCardText, MDBCardTitle, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow, MDBTooltip } from 'mdb-react-ui-kit';
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { showErrorMessage } from '../components/ErrorMessage';

export default function CatalogPage() {  
    const [search, setSearch] = useState('');

    const userData = { name: "X", isadmin: false };

    const [bookList, setBookList] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/books');
            setBookList(response.data);
        } catch (error) {
            showErrorMessage('Błąd podczas pobierania danych z serwera: ' + error);
        }
    };

    const bookCards = <MDBRow className='row-cols-1 row-cols-md-2 g-4 mt-3'>
        {bookList.filter((item) => {
            return search.trim() === '' ? item : (item.title + item.author + item.category).toLowerCase().includes(search.trim())
        }).map((book, i) => <>
            <MDBCol key={i}>
                <MDBCard className='h-100' key={i}>
                    <MDBCardImage src={book.pngPath} alt={book.title} position='top' />
                    <MDBCardBody>
                        <MDBCardTitle>{book.title}</MDBCardTitle>
                        <MDBCardText className='mb-1'>
                            <strong>{book.authors && book.authors.map((author) => author.name + " " + author.surname).join(', ')}</strong>
                        </MDBCardText>
                        <div className='w-100 d-flex justify-content-end'>
                            <div style={{ width: "50%" }}>
                                <Rating className='mb-2' value={book.rating} readOnly={true} />
                            </div>
                        </div>
                        <MDBCardText className='text-center'>
                            <small className='text-muted'>{book.category}</small>
                        </MDBCardText>
                    </MDBCardBody>
                    <MDBCardFooter className='text-center'>
                        <MDBBtnGroup>
                            <Link to={'/preview?id=' + book.id}><MDBBtn className='btn-success' href='#'><MDBIcon fas icon="book-open me-2" />Pokaż książkę</MDBBtn></Link>
                            {userData.name && !userData.isadmin &&
                                <MDBTooltip wrapperProps={{ color: "dark" }} placement='top' title='Dodaj do koszyka'>
                                    <MDBIcon fas icon="cart-plus" />
                                </MDBTooltip>
                            }
                        </MDBBtnGroup>
                    </MDBCardFooter>
                </MDBCard>
            </MDBCol>
        </>)}
    </MDBRow>;

    return (
        <>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6'>
                        <MDBInput className='my-2 bg-white w-100' label='Szukaj książki' onChange={(e) => setSearch(e.target.value.toLowerCase())} />

                        {bookCards}
                    </MDBCol>
                    <MDBCol md='3'></MDBCol>
                </MDBRow>
            </MDBContainer>
        </>
    );
}