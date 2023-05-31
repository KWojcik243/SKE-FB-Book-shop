import { MDBBtn, MDBDropdown, MDBDropdownItem, MDBDropdownMenu, MDBDropdownToggle } from 'mdb-react-ui-kit';
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';

export default function LoginNavBtn() {
    const userData = {};

    if (userData) {
        return (<>
            <MDBDropdown className='btn-group' >
                <MDBBtn className='btn-success'>Zaloguj się</MDBBtn>
                <MDBDropdownToggle split className='btn-success'></MDBDropdownToggle>
                <MDBDropdownMenu>
                    <MDBDropdownItem link>Action</MDBDropdownItem>
                    <MDBDropdownItem link>Another action</MDBDropdownItem>
                    <MDBDropdownItem link>Something else here</MDBDropdownItem>
                </MDBDropdownMenu>
            </MDBDropdown>
        </>)
    } else {
        return (<>X</>)
    }
}