import { MDBBtn, MDBBtnGroup, MDBCol, MDBContainer, MDBIcon, MDBListGroup, MDBListGroupItem, MDBRow } from "mdb-react-ui-kit";


export default function DashboardPage() {


    return (<>
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='4' className="mb-3">
                    <MDBListGroup style={{ minWidth: '22rem' }} light>
                        <MDBListGroupItem tag='button' action noBorders active aria-current='true' type='button' className='px-3'>
                            <MDBIcon fas icon="book" className="me-2" />Książki
                        </MDBListGroupItem>
                        <MDBListGroupItem tag='button' action noBorders type='button' className='px-3'>
                            <MDBIcon fas icon="shopping-basket me-2" />Zamówienia
                        </MDBListGroupItem>
                        <MDBListGroupItem tag='button' action noBorders type='button' className='px-3'>
                            <MDBIcon fas icon="user-friends" className="me-2" />Użytkownicy
                        </MDBListGroupItem>
                    </MDBListGroup>
                </MDBCol>
                <MDBCol md='8'>
                    ...
                </MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}