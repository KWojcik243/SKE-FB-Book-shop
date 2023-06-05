import { MDBCol, MDBContainer, MDBIcon, MDBListGroup, MDBListGroupItem, MDBRow, MDBTooltip } from 'mdb-react-ui-kit';

export default function BasketPage() {
    const basket = [
        { id: 0, title: "Pan Tadeusz", author: "Adam Mickiewicz" },
        { id: 1, title: "cccv c ", author: "ddd" }
    ];
    // const basket = [];

    if (basket.length < 1)
        return (<>
            <MDBContainer className='my-5'>
                <MDBRow>
                    <MDBCol md='3'></MDBCol>
                    <MDBCol md='6' className='text-center'>
                        <h2 className='text-center mb-5'>Twój koszyk</h2>
                        <p>Twój koszyk jest pusty. Dodaj do niego książki, aby móc złożyć zamówienie.</p>
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
                    <h2 className='text-center mb-5'>Twój koszyk</h2>

                    <MDBListGroup light numbered className='mb-3'>
                        {basket.map((book, i) => {
                            return (<MDBListGroupItem key={i} className='d-flex justify-content-between align-items-start'>
                                <div className='ms-2 me-auto'>
                                    <div className='fw-bold'>{book.title}</div>{book.author}
                                </div>

                                <MDBTooltip wrapperProps={{ color: "danger", floating: true }} placement='top' title='Usuń z koszyka'>
                                    <MDBIcon fas icon="trash" />
                                </MDBTooltip>
                            </MDBListGroupItem>)
                        })}
                    </MDBListGroup>

                    <h2 className='text-center mb-5'>Złóż zamówienie</h2>

                    <form className="needs-validation" novalidate="">
                        <div className="row g-3">
                            <div className="col-12">
                                <label for="address" className="form-label">Adres</label>
                                <input type="text" className="form-control" id="address" placeholder="" required="" />
                                <div className="invalid-feedback">
                                    Podaj swój adres.
                                </div>
                            </div>

                            <div className="col-sm-6">
                                <label for="address" className="form-label">Miejscowość</label>
                                <input type="text" className="form-control" id="city" placeholder="" required="" />
                                <div className="invalid-feedback">
                                    Podaj miejscowość.
                                </div>
                            </div>

                            <div className="col-sm-6">
                                <label for="zip" className="form-label">Kod pocztowy</label>
                                <input type="text" className="form-control" id="zip" placeholder="" required="" />
                                <div className="invalid-feedback">
                                    Podaj poprawny kod pocztowy.
                                </div>
                            </div>
                        </div>

                        <hr className="my-4"></hr>

                        <h4 className="mb-3">Płatność</h4>

                        <div className="row gy-3">
                            <div className="col-md-6">
                                <label for="cc-name" className="form-label">Name on card</label>
                                <input type="text" className="form-control" id="cc-name" placeholder="" required="" />
                                <small className="text-muted">Full name as displayed on card</small>
                                <div className="invalid-feedback">
                                    Name on card is required
                                </div>
                            </div>

                            <div className="col-md-6">
                                <label for="cc-number" className="form-label">Credit card number</label>
                                <input type="text" className="form-control" id="cc-number" placeholder="" required="" />
                                <div className="invalid-feedback">
                                    Credit card number is required
                                </div>
                            </div>

                            <div className="col-md-3">
                                <label for="cc-expiration" className="form-label">Expiration</label>
                                <input type="text" className="form-control" id="cc-expiration" placeholder="" required="" />
                                <div className="invalid-feedback">
                                    Expiration date required
                                </div>
                            </div>

                            <div className="col-md-3">
                                <label for="cc-cvv" className="form-label">CVV</label>
                                <input type="text" className="form-control" id="cc-cvv" placeholder="" required="" />
                                <div className="invalid-feedback">
                                    Security code required
                                </div>
                            </div>
                        </div>

                        <hr className="my-4"></hr>

                        <button className="w-100 btn btn-success btn-lg" type="submit"><MDBIcon fas icon="truck" className='me-2' />Zapłać i zamów</button>
                    </form>
                </MDBCol>
                <MDBCol md='3'></MDBCol>
            </MDBRow>
        </MDBContainer>
    </>);
}