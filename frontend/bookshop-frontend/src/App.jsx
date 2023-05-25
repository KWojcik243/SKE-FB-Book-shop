import { useState } from 'react'
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import "@fortawesome/fontawesome-free/css/all.min.css";
import './App.css'
import NavBar from './components/NavBar'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import HomePage from './pages/HomePage'
import LoginPage from './pages/LoginPage'
import NotFoundPage from './pages/NotFoundPage';

function App() {
//   const [count, setCount] = useState(0)

  return (
    <Router>
      <div className='App'>
        <NavBar />
        <div className='content'>
          <Routes>
            <Route path='*' element= { <NotFoundPage /> }></Route>
            <Route path='/' element={ <HomePage /> }></Route>
            <Route path='/login' element={ <LoginPage /> }></Route>
          </Routes>
        </div>
      </div>
    </Router>
  )
}

export default App
