import React ,{useState, useEffect }from 'react'
import { useStateValue } from '../StateManager/StateProvider';
import { GetUser } from '../App';
import './Dashboard_MyAccount.css';
import EditSharpIcon from '@material-ui/icons/EditSharp';

function Dashboard_MyAccount() {

    const [state, dispatch] = useStateValue();
    const [password, setPassword] = useState('');
    const [cpassword, setCpassword] = useState('');
    const [CurrPassword, setCurrPassword] = useState('');
    useEffect( async () => {
        const user = await GetUser(state);
        dispatch({
            type: 'SET_USER_INFO',
            user: user
        });
    }, [])

    function openPhoneForm() {
        document.getElementById("Adr-form").style.display = "none";
        document.getElementById("card-form").style.display = "none";
        document.getElementById("password-form").style.display = "none";

        document.getElementById("phone-form").style.display = "block";
    }
    function closePhoneForm() {
        document.getElementById("phone-form").style.display = "none";
    }

    function openAdrForm() {
        document.getElementById("phone-form").style.display = "none";
        document.getElementById("card-form").style.display = "none";
        document.getElementById("password-form").style.display = "none";

        document.getElementById("Adr-form").style.display = "block";
    }
    function closeAdrForm() {
        document.getElementById("Adr-form").style.display = "none";
    }

    function openCardForm() {
        document.getElementById("Adr-form").style.display = "none";
        document.getElementById("phone-form").style.display = "none";
        document.getElementById("password-form").style.display = "none";

        document.getElementById("card-form").style.display = "block";
    }
    function closeCardForm() {
        document.getElementById("card-form").style.display = "none";
    }

    function openPasswordForm() {
        document.getElementById("Adr-form").style.display = "none";
        document.getElementById("card-form").style.display = "none";
        document.getElementById("phone-form").style.display = "none";

        document.getElementById("password-form").style.display = "block";
    }
    function closePasswordForm() {
        document.getElementById("password-form").style.display = "none";
    }
    
    return (
        <div className="dashboard_MyAccount">
            <div className="dashboard_MyAccount_infoLine">
                <span className="dashboard_MyAccount_item">Username </span>
                <div className="dashboard_MyAccount_input"> {state.user? state.user.name : '-----'} </div>
                <span className="dashboard_MyAccount_infoLine_change"></span>
                
                <span className="dashboard_MyAccount_item">Email </span>
                <div className="dashboard_MyAccount_input"> {state.user? state.user.email : '-----'} </div>
                <span className="dashboard_MyAccount_infoLine_change"></span>
            </div>

            <div className="dashboard_MyAccount_infoLine">
                <span className="dashboard_MyAccount_item">Phone No.</span>
                <div className="dashboard_MyAccount_input"> {state.user? state.user.contact_info : '-----'} </div>
                <span className="dashboard_MyAccount_infoLine_change" onClick={(event) => openPhoneForm()}> <EditSharpIcon/></span>
                
                <div class="dashboard_MyAccount_infoLine_formPopup" id="phone-form">
                    <form action="/action_page.php" class="dashboard_MyAccount_infoLine_formPopup_formContainer">
                        <label for="psw"><b> Phone Number</b></label>
                        <input 
                            placeholder="Phone number"
                            type='password' 
                            value={CurrPassword} 
                            // onChange={e => setCurrPassword(e.target.value)}
                            required
                        />
                        
                        <button type="submit" class="btn" >Change phone number</button>
                        <button type="button" class="btn cancel" onClick={(event) => closePhoneForm()}>Cancel</button>
                    </form>
                </div>



                <span className="dashboard_MyAccount_item">Address</span>
                <div className="dashboard_MyAccount_input"> {state.user? state.user.contact_info : '-----'} </div>
                <span className="dashboard_MyAccount_infoLine_change" onClick={(event) => openAdrForm()}> <EditSharpIcon/></span>
            
                <div class="dashboard_MyAccount_infoLine_formPopup" id="Adr-form">
                    <form action="/action_page.php" class="dashboard_MyAccount_infoLine_formPopup_formContainer">
                        <label for="psw"><b> Address</b></label>
                        <input 
                            placeholder="Address"
                            type='password' 
                            value={CurrPassword} 
                            // onChange={e => setCurrPassword(e.target.value)}
                            required
                        />
                        
                        <button type="submit" class="btn" >Change phone number</button>
                        <button type="button" class="btn cancel" onClick={(event) => closeAdrForm()}>Cancel</button>
                    </form>
                </div>
            
            
            </div>

            <div className="dashboard_MyAccount_infoLine">
                <span className="dashboard_MyAccount_item">Credit card </span>
                <div className="dashboard_MyAccount_input"> {state.user? state.user.contact_info : '-----'} </div>
                <span className="dashboard_MyAccount_infoLine_change" onClick={(event) => openCardForm()}> <EditSharpIcon/></span>

                <div class="dashboard_MyAccount_infoLine_formPopup" id="card-form">
                    <form action="/action_page.php" class="dashboard_MyAccount_infoLine_formPopup_formContainer">

                        <label for="psw"><b> Card Number</b></label>
                        <input 
                            placeholder="card number"
                            type='password' 
                            value={CurrPassword} 
                            // onChange={e => setCurrPassword(e.target.value)}
                            required
                        />
                        
                        <button type="submit" class="btn" >Change Card</button>
                        <button type="button" class="btn cancel" onClick={(event) => closeCardForm()}>Cancel</button>
                    </form>
                </div>




                <span className="dashboard_MyAccount_item">Password </span>
                <div className="dashboard_MyAccount_input"> {state.user? state.user.password : '-----  '} </div>
                <span className="dashboard_MyAccount_infoLine_change" onClick={(event) => openPasswordForm()}> <EditSharpIcon/></span>

                <div class="dashboard_MyAccount_infoLine_formPopup" id="password-form">
                    <form action="/action_page.php" class="dashboard_MyAccount_infoLine_formPopup_formContainer">

                        <label for="psw"><b>Current Password</b></label>
                        <input 
                            placeholder="Enter Current Password"
                            type='password' 
                            value={CurrPassword} 
                            onChange={e => setCurrPassword(e.target.value)}
                            required
                        />
                        
                        <label for="psw"><b>New Password</b></label>
                        <input 
                            placeholder="Enter New Password"
                            type='password' 
                            value={password} 
                            onChange={e => setPassword(e.target.value)}
                        />

                        <label for="conf_psw"><b>Confirm New Password</b></label>
                        <input 
                            placeholder="Enter New Password again"
                            type='password' 
                            value={cpassword} 
                            onChange={e => setCpassword(e.target.value)}
                        />
                        {(password === cpassword) ? null : <span>Password is not the same!</span>}

                        <button type="submit" class="btn" >Change password</button>
                        <button type="button" class="btn cancel" onClick={(event) => closePasswordForm()}>Cancel</button>
                    </form>
                </div>

            
            </div>
        </div>
    )
}

export default Dashboard_MyAccount
