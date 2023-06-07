import { MDBBtn, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow } from "mdb-react-ui-kit";
import { Link } from "react-router-dom";


export default function LoginPage() {

    return (
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='3'></MDBCol>
                <MDBCol md='6'>
                    <h2 className='text-center mb-5'>Logowanie</h2>

                    <MDBRow tag="form" className='g-2 justify-content-center' method="POST">
                        <MDBCol size="8" className="text-center mb-3">
                            <MDBInput id="email" label="Adres email" className="my-2" />
                            <MDBInput id="password" label="Hasło" type="password" className="my-2" />
                            <MDBBtn type="submit" color="success" className="mt-3"><MDBIcon fas icon="sign-in-alt me-2" />Zaloguj się</MDBBtn>
                        </MDBCol> 
                        <MDBCol size="8" className="text-center">
                            <p>Nie masz jeszcze konta?<br></br><Link to="/register"><MDBBtn color="light" size="sm">Zarejestruj się teraz!</MDBBtn></Link></p>
                        </MDBCol>
                    </MDBRow>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    )
}