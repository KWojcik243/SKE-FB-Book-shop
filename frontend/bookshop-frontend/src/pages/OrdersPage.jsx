import { MDBBadge, MDBCol, MDBContainer, MDBListGroup, MDBListGroupItem, MDBRow } from "mdb-react-ui-kit";
import { Navigate } from "react-router-dom";


export default function OrdersPage() {
    const orders = [
        { id: 0, books: ["Pan Tadeusz", "xxx"], state: 1, lastUpdated: "2023-06-06" },
        { id: 1, books: ["dfd"], state: 2, lastUpdated: "2023-05-16" },
    ];

    const userData = { name: "X" };

    if (!userData.name)
        return <Navigate to="/"></Navigate>;

    if (orders.length < 1)
        return (<>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6'>
                        <h2 className='text-center mb-5'>Twoje zamówienia</h2>

                        <p>Brak zamówień.</p>
                    </MDBCol>
                    <MDBCol md='3'></MDBCol>
                </MDBRow>
            </MDBContainer>
        </>);

    return (<>
        <MDBContainer className='my-5'>
            <MDBRow>
                <MDBCol md='3'></MDBCol>
                <MDBCol md='6'>
                    <h2 className='text-center mb-5'>Twoje zamówienia</h2>

                    <MDBListGroup style={{ minWidth: '22rem' }} light>
                        {orders.map((order, i) => {
                            return <MDBListGroupItem key={i} className='d-flex justify-content-between align-items-center'>
                                <div>
                                    <div className='fw-bold'>Zamówienie #{order.id}</div>
                                    <div className='text-muted'>{order.books.join(', ')}</div>
                                </div>
                                <div className="text-end">
                                    <MDBBadge pill light color='success'>
                                        {order.state}
                                    </MDBBadge>
                                    <br></br> 
                                    <MDBBadge pill light color='dark'>
                                        {order.lastUpdated}
                                    </MDBBadge>
                                </div>
                            </MDBListGroupItem>
                        })}
                    </MDBListGroup>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}