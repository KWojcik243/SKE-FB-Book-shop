import React from 'react';
import { MDBNavbar, MDBContainer, MDBNavbarBrand } from 'mdb-react-ui-kit';
import logo from '../assets/favicon.svg';

export default function NavBar() {


    return (
        <>
            <MDBNavbar sticky light bgColor='light'>
                <MDBContainer fluid>
                    <MDBNavbarBrand href='/'>
                        <img src={logo} height='30' alt='' loading='lazy' />
                        SKE Księgarnia
                    </MDBNavbarBrand>
                </MDBContainer>
            </MDBNavbar>
        </>
    )
}