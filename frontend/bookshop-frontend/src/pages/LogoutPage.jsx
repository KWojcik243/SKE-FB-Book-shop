import {Navigate} from "react-router-dom";
import {useContext} from "react";
import {AuthContext} from "../components/AuthContext.jsx";

export default function LogoutPage() {
    const { logout } = useContext(AuthContext);

    logout();

    return <Navigate to='/catalog'></Navigate>;
}