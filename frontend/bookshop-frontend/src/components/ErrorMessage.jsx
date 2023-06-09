import React from "react";
import * as bootstrap from 'bootstrap';

export function showErrorMessage(message) {
    document.getElementById('liveToastErrorTime').innerText = (new Date()).toLocaleTimeString();
    document.getElementById('liveToastErrorBody').innerHTML = message;

    const toast = new bootstrap.Toast(document.getElementById('liveToastError'));
    
    if (!toast.isShown())
        toast.show();
}

export default function ErrorMessage() {
    return (<>
        <div className="toast-container position-fixed bottom-0 end-0 p-3" style={{zIndex: "99999999999"}}>
            <div id="liveToastError" className="toast" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="8000" >
                <div className="toast-header">
                    <strong className="me-auto">Błąd</strong>
                    <small id="liveToastErrorTime">X</small>
                    <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div className="toast-body" id="liveToastErrorBody">
                    Msg
                </div>
            </div>
        </div>
    </>);
}