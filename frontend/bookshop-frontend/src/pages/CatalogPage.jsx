import { MDBBtnGroup, MDBCol, MDBContainer, MDBInput, MDBRow } from 'mdb-react-ui-kit';
import React from 'react';

export default function CatalogPage() {
    const userData = {isadmin: true}; 
    const bookList = [
        {}
    ];

    return (
        <>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6'>
                        <MDBInput className='my-2 bg-white w-100' label='Szukaj książki' />
                        {userData.isadmin && <MDBBtnGroup toolbar className='my-2' role='toolbar'>
                            
                        </MDBBtnGroup>}
                    </MDBCol>
                    <MDBCol md='3'></MDBCol>
                </MDBRow>
            </MDBContainer>

        </>
    );
}