import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import "@fortawesome/fontawesome-free/css/all.min.css";
import './App.css'
import NavBar from './components/NavBar'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import HomePage from './pages/HomePage'
import LoginPage from './pages/LoginPage'
import NotFoundPage from './pages/NotFoundPage';
import Footer from './components/Footer';
import ContactPage from './pages/ContactPage';
import CatalogPage from './pages/CatalogPage';
import '@smastrom/react-rating/style.css';
import RegisterPage from './pages/RegisterPage';
import BookPreviewPage from './pages/BookPreviewPage';
import BasketPage from './pages/BasketPage';
import OrdersPage from './pages/OrdersPage';
import DashboardPage from './pages/DashboardPage';
import ErrorMessage from './components/ErrorMessage';
import LogoutPage from "./pages/LogoutPage.jsx";

function App() {
  return (
    <Router>
      <div className='App d-flex flex-column h-100'>
        <NavBar />
        <div className='content flex-fill'>
          <Routes>
            <Route path='*' element= { <NotFoundPage /> }></Route>
            <Route path='/' element={ <HomePage /> }></Route>
            <Route path='/contact' element={ <ContactPage /> }></Route>
            <Route path='/login' element={ <LoginPage /> }></Route>
            <Route path='/logout' element={ <LogoutPage /> }></Route>
            <Route path='/register' element={ <RegisterPage /> }></Route>
            <Route path='/catalog' element={ <CatalogPage /> }></Route>
            <Route path='/preview' element={ <BookPreviewPage /> }></Route>
            <Route path='/basket' element={ <BasketPage /> }></Route>
            <Route path='/orders' element={ <OrdersPage /> }></Route>
            <Route path='/dashboard' element={ <DashboardPage /> }></Route>
            <Route path='/dashboard/books' element={ <DashboardPage /> }></Route>
            <Route path='/dashboard/orders' element={ <DashboardPage /> }></Route>
            <Route path='/dashboard/users' element={ <DashboardPage /> }></Route>
            <Route path='/dashboard/authors' element={ <DashboardPage /> }></Route>
            <Route path='/dashboard/categories' element={ <DashboardPage /> }></Route>
          </Routes>
          <ErrorMessage />
        </div>
        <Footer />
      </div>
    </Router>
  )
}

export default App
