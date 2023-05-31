import React from 'react';
import { MDBCard, MDBListGroup, MDBListGroupItem, MDBRow, MDBCol, MDBContainer, MDBCardTitle, MDBCardText, MDBIcon } from 'mdb-react-ui-kit';

export default function ContactPage() {
    return (
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='3'></MDBCol>
                <MDBCol md='6'>
                    <h2 className='text-center mb-5'>Informacje o projekcie</h2>

                    <h3 className='mb-3'>Skład zespołu:</h3>
                    <MDBCard className='mb-5'>
                        <MDBListGroup flush>
                            <MDBListGroupItem>
                                <img src="https://avatars.githubusercontent.com/u/63014344?v=4" className='img-fluid rounded-circle me-2' style={{width: "24px", height: "24px"}}></img> Kacper Wójcik
                            </MDBListGroupItem>
                            <MDBListGroupItem>
                                <img src="https://avatars.githubusercontent.com/u/63063137?v=4" className='img-fluid rounded-circle me-2' style={{width: "24px", height: "24px"}}></img> Paweł Nowak
                            </MDBListGroupItem>
                            <MDBListGroupItem>
                                <img src="https://avatars.githubusercontent.com/u/22080687?v=4" className='img-fluid rounded-circle me-2' style={{width: "24px", height: "24px"}}></img> Marcin Stefanowicz
                            </MDBListGroupItem>
                        </MDBListGroup>
                    </MDBCard>

                    <h3 className='mb-3'>Repozytorium:</h3>
                    <p><a href='https://github.com/KWojcik243/SKE-FB-Book-shop/' target='_blank'><MDBIcon fab icon="github" className='me-2' />Link do repozytorium w serwisie GitHub</a></p>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    )
}