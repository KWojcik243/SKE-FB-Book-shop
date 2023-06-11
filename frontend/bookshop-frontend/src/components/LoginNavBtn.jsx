import { MDBBtn, MDBDropdown, MDBDropdownItem, MDBDropdownMenu, MDBDropdownToggle, MDBIcon } from 'mdb-react-ui-kit';
import React, {useContext, useState} from 'react';
import { NavLink } from 'react-router-dom';
import {AuthContext} from "./AuthContext.jsx";

export default function LoginNavBtn() {
    const { user } = useContext(AuthContext);

    if (user) {
        const isAdmin = user.role === '[ADMIN]';

        return (<>
            <MDBDropdown className='btn-group' >
                <MDBBtn className='bg-success btn-success' style={{paddingInline: "15px", pointerEvents: "none"}} ><MDBIcon far icon="user-circle" className='me-2' />{user.name + " " + user.surname}</MDBBtn>
                <MDBDropdownToggle split className='btn-success' style={{paddingInline: "15px"}}></MDBDropdownToggle>
                <MDBDropdownMenu>
                    {isAdmin ? <></> : <li><NavLink to="/basket" className='dropdown-item'><MDBIcon fas icon="shopping-basket" className='me-3' />Koszyk</NavLink></li>}
                    <li><NavLink to={isAdmin ? "/dashboard" : "/orders"} className='dropdown-item'><MDBIcon fas icon={isadmin ? "tools" : "th-list"}  className='me-3' />{isAdmin ? "Panel administratora" : "Zamówienia"}</NavLink></li>
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