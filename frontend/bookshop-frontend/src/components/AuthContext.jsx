import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios';
import {showErrorMessage} from "./ErrorMessage.jsx";
import jwtDecode from "jwt-decode";

export const AuthContext = createContext({});

const AuthContextProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        checkLoggedIn();
    }, []);

    const checkLoggedIn = async () => {
        const accessToken = localStorage.getItem('accessToken');
        if (!accessToken) {
            setLoading(false);
            return;
        }

        let userData = jwtDecode(accessToken);
        setUser({email: userData.sub, role: userData.aud });

        setLoading(false);
    };

    let login = async (email, password) => {
        try {
            const response = await axios.post('http://localhost:8080/auth/authenticate', { email: email, password: password });
            if (response.status === 200) {
                const { token } = response.data;
                localStorage.setItem('accessToken', token);
                let userData = jwtDecode(token);
                setUser({email: userData.sub, role: userData.aud });
            } else {
                showErrorMessage('Błąd logowania.');
            }
        } catch (error) {
            showErrorMessage('Błąd logowania: ' + error);
        }
    };

    const logout = () => {
        localStorage.removeItem('accessToken');
        setUser(null);
    };

    const register = async (name, surname, email, password) => {
        try {
            const response = await axios.post('http://localhost:8080/auth/register', { name: name, surname: surname, email: email, password: password });

            if (response.status === 200) {
                const { token, user } = response.data;
                localStorage.setItem('accessToken', token);
                setUser(user);
            } else {
                showErrorMessage('Błąd rejestracji.');
            }
        } catch (error) {
            showErrorMessage('Błąd rejestracji: ' + error);
        }
    };

    return (
        <AuthContext.Provider value={{ user: user, loading: loading, login: login, logout: logout, register: register }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContextProvider;
