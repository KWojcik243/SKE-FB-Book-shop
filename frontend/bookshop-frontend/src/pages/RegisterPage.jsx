import { MDBBtn, MDBCol, MDBContainer, MDBIcon, MDBInput, MDBRow } from "mdb-react-ui-kit";
import { Link, Navigate } from "react-router-dom";
import { useContext, useState } from "react";
import { AuthContext } from "../components/AuthContext.jsx";
import {showErrorMessage} from "../components/ErrorMessage.jsx";
import axios from "axios";

export default function RegisterPage() {
    const { register, user } = useContext(AuthContext);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");

    if (user)
        return <Navigate to='/catalog'></Navigate>;

    const registerUser = async () => {
        if (password !== password2) {
            showErrorMessage("Hasła się różnią");
            return;
        }

        if (!password || !firstName || !lastName || !email)
        {
            showErrorMessage("Uzupełnij wszystkie pola formularza.");
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/auth/register', {
                name: firstName, surname: lastName, email: email, password: password
            });
            // window.location.href = 'http://localhost:5173/login';
            history.push('/login');
        } catch (error) {
            showErrorMessage('Wystąpił błąd podczas pobierania danych z serwera: ' + error);
        }
    }

    return (
        <>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6'>
                        <h2 className='text-center mb-5'>Rejestracja</h2>

                        <form onSubmit={registerUser}>
                            <MDBRow className='g-2 justify-content-center'>
                                <MDBCol size="8" className="text-center mb-3">
                                    <MDBInput
                                        id="firstname"
                                        label="Imię"
                                        className="my-2"
                                        value={firstName}
                                        onChange={(e) => setFirstName(e.target.value)}
                                    />
                                    <MDBInput
                                        id="lastname"
                                        label="Nazwisko"
                                        className="my-2"
                                        value={lastName}
                                        onChange={(e) => setLastName(e.target.value)}
                                    />
                                    <MDBInput
                                        id="email"
                                        label="Adres email"
                                        className="my-2"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                    />
                                    <MDBInput
                                        id="password"
                                        label="Hasło"
                                        type="password"
                                        className="my-2"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                    <MDBInput
                                        id="password2"
                                        label="Powtórz hasło"
                                        type="password"
                                        className="my-2"
                                        value={password2}
                                        onChange={(e) => setPassword2(e.target.value)}
                                    />
                                    <MDBBtn type="submit" color="success" className="mt-3">
                                        <MDBIcon fas icon="sign-in-alt me-2" />
                                        Zarejestruj się
                                    </MDBBtn>
                                </MDBCol>
                                <MDBCol size="8" className="text-center">
                                    <p>Masz już u nas konto? <br></br>
                                        <Link to="/login">
                                            <MDBBtn color="light" size="sm">Zaloguj się!</MDBBtn>
                                        </Link>
                                    </p>
                                </MDBCol>
                            </MDBRow>
                        </form>
                    </MDBCol>
                    <MDBCol md='3'></MDBCol>
                </MDBRow>
            </MDBContainer>
        </>
    );
}
