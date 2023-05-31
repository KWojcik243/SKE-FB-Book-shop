import React from 'react';
import { MDBCol, MDBContainer, MDBRow, MDBBtn } from 'mdb-react-ui-kit';
import { useLocation } from 'react-router-dom';

export default function NotFoundPage() {
    const location = useLocation();

    return (
        <>
            <MDBContainer>
                <MDBRow>
                    <MDBCol>
                        <section className="my-5 text-center">
                            <h1 className="display-3">Błąd 404</h1>
                            <h5 className="mb-1">Nie oznaleziono strony</h5>
                            <p className="mb-4 text-muted">{location.pathname}</p>
                            <p className="mb-4">Szukana przez Ciebie strona nie istnieje lub nie masz wystarczających <br></br>uprawnień, aby uzyskać do niej dostęp.</p>
                            <a href="/"><MDBBtn className='btn-success'>Wróć na stronę główną</MDBBtn></a>
                            </section>
                    </MDBCol>
                </MDBRow>
            </MDBContainer>
        </>
    )
}