import React from 'react'
import './Header_dashboard.css'
import NotificationsIcon from '@material-ui/icons/Notifications';
import SettingsApplicationsSharpIcon from '@material-ui/icons/SettingsApplicationsSharp';
import { useStateValue } from '../StateManager/StateProvider';
import { Link } from 'react-router-dom';

function Header_dashboard() {
    const [state, dispatch] = useStateValue();

    const logout = () => {
        dispatch({
            type: 'SET_USER',
            token: null
        });
        dispatch({
            type: 'SET_USER_INFO',
            user: null
        });
        localStorage.removeItem('token');
    }
    
    return (
        <div className="header__dashboard">
            
            <div className="header__dashboard__nav">
                
                <div className="header__nav__notifications">  
                    <NotificationsIcon />
                </div>

                <div className="header__nav__settings">
                    <SettingsApplicationsSharpIcon />
                </div>

                <div className="header__nav_profile">

                    <span className="header__optionLineOne">
                        Hello
                    </span>

                </div>

                <Link to="/">
                    <button 
                        type= 'submit'
                        className="header__nav__logout"
                        onClick= {logout}>
                            Log out
                    </button>
                </Link>
            </div>

        </div>
    )
}

export default Header_dashboard
