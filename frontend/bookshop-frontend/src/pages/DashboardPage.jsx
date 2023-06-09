import { MDBCol, MDBContainer, MDBIcon, MDBListGroup, MDBListGroupItem, MDBRow } from "mdb-react-ui-kit";
import { Link, Navigate, Route, Routes, useLocation } from "react-router-dom";
import DashboardBooks from "../components/DashboardBooks";
import DashboardOrders from "../components/DashboardOrders";
import DashboardUsers from "../components/DashboardUsers";
import DashboardAuthors from "../components/DashboardAuthors.jsx";
import DashboardCategories from "../components/DashboardCategories.jsx";

export default function DashboardPage() {
    const location = useLocation();

    const userData = {name: "x"};

    if (!userData.name)
        return <Navigate to='/'></Navigate>;

    if (location.pathname === '/dashboard')
        return <Navigate to='/dashboard/books'></Navigate>;

    return (<>
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='4' className="mb-3">
                    <MDBListGroup light>
                        <Link to='/dashboard/books'>
                            <MDBListGroupItem tag='button' action noBorders active={location.pathname.includes('/books')} type='button' className='px-3'>
                                <MDBIcon fas icon="book" className="me-2" />Książki
                            </MDBListGroupItem>
                        </Link>
                        <Link to='/dashboard/authors'>
                            <MDBListGroupItem tag='button' action noBorders active={location.pathname.includes('/authors')} type='button' className='px-3'>
                                <MDBIcon fas icon="users" className="me-2" />Autorzy
                            </MDBListGroupItem>
                        </Link>
                        <Link to='/dashboard/categories'>
                            <MDBListGroupItem tag='button' action noBorders active={location.pathname.includes('/categories')} type='button' className='px-3'>
                                <MDBIcon fas icon="swatchbook" className="me-2" />Kategorie
                            </MDBListGroupItem>
                        </Link>
                        <Link to='/dashboard/orders'>
                            <MDBListGroupItem tag='button' action noBorders active={location.pathname.includes('/orders')} type='button' className='px-3'>
                                <MDBIcon fas icon="shopping-basket me-2" />Zamówienia
                            </MDBListGroupItem>
                        </Link>
                        <Link to='/dashboard/users'>
                            <MDBListGroupItem tag='button' action noBorders active={location.pathname.includes('/users')} type='button' className='px-3'>
                                <MDBIcon fas icon="user-friends" className="me-2" />Użytkownicy
                            </MDBListGroupItem>
                        </Link>
                    </MDBListGroup>
                </MDBCol>
                <MDBCol md='8'>
                    {location.pathname.includes('/books') && <DashboardBooks />}
                    {location.pathname.includes('/authors') && <DashboardAuthors />}
                    {location.pathname.includes('/categories') && <DashboardCategories />}
                    {location.pathname.includes('/orders') && <DashboardOrders />}
                    {location.pathname.includes('/users') && <DashboardUsers />}
                </MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}