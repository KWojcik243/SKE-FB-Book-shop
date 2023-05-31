import { MDBBtn, MDBDropdown, MDBDropdownItem, MDBDropdownMenu, MDBDropdownToggle, MDBIcon } from 'mdb-react-ui-kit';
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';

export default function LoginNavBtn() {
    const userData = {name: "Jan", surname: "Kowalski"};

    if (userData.name && userData.name.length > 0) {
        return (<>
            <MDBDropdown className='btn-group' >
                <NavLink to="/account"><MDBBtn className='btn-success' style={{paddingInline: "15px"}}><MDBIcon far icon="user-circle" className='me-2' />{userData.name + " " + userData.surname}</MDBBtn></NavLink>
                <MDBDropdownToggle split className='btn-success' style={{paddingInline: "15px"}}></MDBDropdownToggle>
                <MDBDropdownMenu>
                    <li><NavLink to="/basket" className='dropdown-item'><MDBIcon fas icon="shopping-basket" className='me-3' />Koszyk</NavLink></li>
                    <li><NavLink to="/orders" className='dropdown-item'><MDBIcon fas icon="th-list"  className='me-3' />Zamówienia</NavLink></li>
                    <li><NavLink to="/logout" className='dropdown-item'><MDBIcon fas icon="sign-out-alt"  className='me-3' />Wyloguj się</NavLink></li>
                </MDBDropdownMenu>
            </MDBDropdown>
        </>)
    } else {
        return (<>
            <NavLink to="/login"><MDBBtn className='btn-success' style={{paddingInline: "15px"}} ><MDBIcon fas icon="sign-in-alt" className='me-2' />Zaloguj się</MDBBtn></NavLink>
        </>)
    }
}