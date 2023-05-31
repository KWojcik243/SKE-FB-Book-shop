import React, { useState } from 'react';
import { MDBNavbar, MDBContainer, MDBNavbarBrand, MDBCollapse, MDBIcon, MDBNavbarNav, MDBNavbarItem, MDBNavbarLink, MDBDropdown, MDBDropdownItem, MDBDropdownToggle, MDBDropdownMenu, MDBNavbarToggler, MDBBtn } from 'mdb-react-ui-kit';
import logo from '../assets/favicon.svg';
import { NavLink } from 'react-router-dom';
import LoginNavBtn from './LoginNavBtn';

export default function NavBar() {
    const [showNav, setShowNav] = useState(false);

    return (
        <>
            <MDBNavbar sticky light expand='lg' style={{ borderBottom: "2px solid var(--accent)", backgroundColor: "#ffffffdd", backdropFilter: "blur(5px)" }}>
                <MDBContainer fluid style={{ paddingInline: "2%" }}>
                    <MDBNavbarBrand href='/' style={{ color: "var(--accent)", marginRight: "20px" }}>
                        <img src={logo} height='30' alt='' loading='lazy' style={{ marginRight: "10px" }} />
                        SKE Księgarnia
                    </MDBNavbarBrand>
                    <MDBNavbarToggler
                        type='button'
                        aria-expanded='false'
                        aria-label='Toggle navigation'
                        onClick={() => setShowNav(!showNav)}
                    >
                        <MDBIcon icon='bars' fas />
                    </MDBNavbarToggler>
                    <MDBCollapse navbar show={showNav} style={{justifyContent: "space-between"}} >
                        <MDBNavbarNav fullWidth={false} className='justify-content-start'>
                            <NavLink className='nav-item nav-link' to='/'>Strona główna</NavLink>
                            <NavLink className='nav-item nav-link' to='/catalog'>Katalog książek</NavLink>
                            <NavLink className='nav-item nav-link' to='/contact'>Kontakt</NavLink>
                        </MDBNavbarNav>
                        <MDBNavbarNav className='justify-content-end w-auto'>
                            <MDBNavbarItem>
                                <LoginNavBtn></LoginNavBtn>
                            </MDBNavbarItem>
                        </MDBNavbarNav>
                    </MDBCollapse>
                </MDBContainer>
            </MDBNavbar>
        </>
    )
}