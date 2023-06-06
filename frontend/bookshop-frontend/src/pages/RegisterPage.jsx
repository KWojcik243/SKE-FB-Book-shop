import { MDBBtn, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow } from "mdb-react-ui-kit";
import { Link } from "react-router-dom";

export default function RegisterPage() {


    return (<>
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='3'></MDBCol>
                <MDBCol md='6'>
                    <h2 className='text-center mb-5'>Rejestracja</h2>

                    <MDBRow tag="form" className='g-2 justify-content-center' method="POST">
                        <MDBCol size="8" className="text-center mb-3">
                            <MDBInput id="firstname" label="Imię" className="my-2" />
                            <MDBInput id="lastname" label="Nazwisko" className="my-2" />
                            <MDBInput id="email" label="Adres email" className="my-2" />
                            <MDBInput id="password" label="Hasło" type="password" className="my-2" />
                            <MDBInput id="password2" label="Powtórz hasło" type="password" className="my-2" />
                            <MDBBtn type="submit" color="success" className="mt-3"><MDBIcon fas icon="sign-in-alt me-2" />Zarejestruj się</MDBBtn>
                        </MDBCol>
                        <MDBCol size="8" className="text-center">
                            <p>Masz już u nas konto? <br></br><Link to="/login"><MDBBtn color="light" size="sm">Zaloguj się!</MDBBtn></Link></p>
                        </MDBCol>
                    </MDBRow>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    </>)
}