import {MDBSpinner} from "mdb-react-ui-kit";

export default function LoadingPage() {
    return (<div className='d-flex justify-content-center p-5'>
        <MDBSpinner role='status'>
            <span className='visually-hidden'>Ładowanie...</span>
        </MDBSpinner>
    </div>);
}