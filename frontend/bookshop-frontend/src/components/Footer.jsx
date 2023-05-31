import { MDBFooter, MDBIcon } from 'mdb-react-ui-kit';
import React from 'react';

export default function Footer() {
    return (
        <MDBFooter bgColor='light' className='text-center text-lg-start text-muted'>
            <section className='d-flex justify-content-center justify-content-lg-between p-4 border-bottom'>
                <div className='me-5 d-none d-lg-block'>
                    <span>Poznaj autorów projektu:</span>
                </div>

                <div>
                    <a href='//github.com/KWojcik243/' className='me-4 text-reset' target='_blank'>
                        <MDBIcon fab icon="github" /> Kacper Wójcik
                    </a>
                    <a href='//github.com/Matek0611/' className='me-4 text-reset' target='_blank'>
                        <MDBIcon fab icon="github" /> Marcin Stefanowicz
                    </a>
                    <a href='//github.com/pawelnoww/' className='me-4 text-reset' target='_blank'>
                        <MDBIcon fab icon="github" /> Paweł Nowak
                    </a>
                </div>
            </section>

            <div className='text-center p-4' style={{ backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
                © {(new Date().getFullYear())} Copyright&nbsp;
                <a className='text-reset fw-bold' href='/contact'>
                    SKE Bookshop Team
                </a>
            </div>
        </MDBFooter>
    );
}