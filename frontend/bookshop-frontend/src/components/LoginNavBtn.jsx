import { MDBBtn, MDBDropdown, MDBDropdownItem, MDBDropdownMenu, MDBDropdownToggle, MDBIcon } from 'mdb-react-ui-kit';
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';

export default function LoginNavBtn() {
    const userData = {name: "", surname: "Kowalski", isadmin: false};

    if (userData.name && userData.name.length > 0) {
        return (<>
            <MDBDropdown className='btn-group' >
                <MDBBtn className='bg-success btn-success' style={{paddingInline: "15px", pointerEvents: "none"}} ><MDBIcon far icon="user-circle" className='me-2' />{userData.name + " " + userData.surname}</MDBBtn>
                <MDBDropdownToggle split className='btn-success' style={{paddingInline: "15px"}}></MDBDropdownToggle>
                <MDBDropdownMenu>
                    {userData.isadmin ? <></> : <li><NavLink to="/basket" className='dropdown-item'><MDBIcon fas icon="shopping-basket" className='me-3' />Koszyk</NavLink></li>}
                    <li><NavLink to={userData.isadmin ? "/dashboard" : "/orders"} className='dropdown-item'><MDBIcon fas icon={userData.isadmin ? "tools" : "th-list"}  className='me-3' />{userData.isadmin ? "Panel administratora" : "Zamówienia"}</NavLink></li>
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