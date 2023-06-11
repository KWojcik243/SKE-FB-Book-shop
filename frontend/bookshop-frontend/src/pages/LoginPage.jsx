import { MDBBtn, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow } from "mdb-react-ui-kit";
import {Link, Navigate} from "react-router-dom";
import {useContext, useState} from "react";
import {AuthContext} from "../components/AuthContext.jsx";

export default function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const { login, user } = useContext(AuthContext);

    if (user)
        return <Navigate to='/catalog'></Navigate>;

    return (
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='3'></MDBCol>
                <MDBCol md='6'>
                    <h2 className='text-center mb-5'>Logowanie</h2>
                    <form onSubmit={(e) => {
                        e.preventDefault();
                        login(email, password);
                    }}>
                        <MDBRow className='g-2 justify-content-center'>
                            <MDBCol size="8" className="text-center mb-3">
                                <MDBInput id="email" label="Adres email" className="my-2" value={email} onChange={e => setEmail(e.target.value)} />
                                <MDBInput id="password" label="Hasło" type="password" className="my-2" value={password} onChange={e => setPassword(e.target.value)} />
                                <MDBBtn type="submit" color="success" className="mt-3"><MDBIcon fas icon="sign-in-alt me-2" />Zaloguj się</MDBBtn>
                            </MDBCol>
                            <MDBCol size="8" className="text-center">
                                <p>Nie masz jeszcze konta?<br></br><Link to="/register"><MDBBtn color="light" size="sm">Zarejestruj się teraz!</MDBBtn></Link></p>
                            </MDBCol>
                        </MDBRow>
                    </form>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    )
}