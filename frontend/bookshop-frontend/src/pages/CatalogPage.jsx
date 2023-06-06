import { Rating } from '@smastrom/react-rating';
import { MDBBtn, MDBBtnGroup, MDBCard, MDBCardBody, MDBCardFooter, MDBCardImage, MDBCardText, MDBCardTitle, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow, MDBTooltip } from 'mdb-react-ui-kit';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';

export default function CatalogPage() {
    const [search, setSearch] = useState('');

    const userData = { name: "X", isadmin: false };
    const bookList = [
        { id: 0, title: "Pan Tadeusz", rating: 5, author: "Adam Mickiewicz", category: "literatura polska", cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg" },
        { id: 1, title: "XX", rating: 2.5, author: "sddf dsffd", category: "literatura zagraniczna", cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg" },
        { id: 2, title: "xd", rating: 4, author: "dfds sfdfsd", category: "literatura polska", cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg" },
        { id: 3, title: "xc", rating: 3, author: "Adam Mickiewicz", category: "fantasy", cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg" },
        // { id: 4, title: "xzcxz", rating: 4.7, author: "ds f", category: "literatura polska", cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg" },
        { id: 5, title: "xczxz", rating: 3.33, author: "fd sd", category: "horror", cover: "https://cdn.pixabay.com/photo/2015/01/24/14/03/book-610189_1280.jpg" },
    ];

    const bookCards = <MDBRow className='row-cols-1 row-cols-md-2 g-4 mt-3'>
        {bookList.filter((item) => {
            return search.trim() === '' ? item : (item.title + item.author + item.category).toLowerCase().includes(search.trim())
        }).map((book) => <>
            <MDBCol>
                <MDBCard className='h-100'>
                    <MDBCardImage
                        src={book.cover}
                        alt={book.title}
                        position='top'
                    />
                    <MDBCardBody>
                        <MDBCardTitle>{book.title}</MDBCardTitle>
                        <MDBCardText className='mb-1'>
                            <strong>{book.author}</strong>
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