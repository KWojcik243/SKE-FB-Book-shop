import { MDBBtn } from "mdb-react-ui-kit";
import NavBar from "../components/NavBar";
import "../styles/homepage.css";
import { NavLink } from "react-router-dom";


export default function HomePage() {

    return (
        <div className="border-bottom w-100" style={{ height: "calc(100vh - 60px)" }}>
            <div id="mainpanel">
                <p>SKE Księgarnia</p>
                <hr style={{ width: "100px" }} className="mx-auto"></hr>
                <p><span style={{ fontSize: "0.8em", marginLeft: "-10px" }}>Nie czekaj! Sięgnij po nasze książki...</span>
                    <br></br>
                    <span style={{ fontSize: "0.8em", fontVariantCaps: "all-petite-caps" }}><div class="dropping-texts">
                        <div>mrożące krew w żyłach</div>
                        <div>popularno-naukowe</div>
                        <div>historyczne z nutą romansu</div>
                        <div>i stwórz swoją własną bibliotekę!</div>
                    </div></span>
                </p>
                <NavLink to="/catalog">
                    <MDBBtn outline rounded color='light' style={{ fontSize: "15px" }}>Otwórz katalog &gt;&gt;</MDBBtn>
                </NavLink>
            </div>
        </div>
    )
}