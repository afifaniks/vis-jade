import React, { useEffect } from 'react'
import './Header.css'
import SearchIcon from '@material-ui/icons/Search';
import ShoppingBasketIcon from '@material-ui/icons/ShoppingBasket';
import { Link } from 'react-router-dom';
import { useStateValue } from '../StateManager/StateProvider';
import { GetUser } from '../App';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';

function Header() {

    const [state, dispatch] = useStateValue();

    // useEffect( async () => {
    //     const user = await GetUser(state);
    //     dispatch({
    //         type: 'SET_USER_INFO',
    //         user: user
    //     });
    // }, [])

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
        <div className="header">
            <img 
                className="header__logo"
                src="http://pngimg.com/uploads/hummingbird/hummingbird_PNG66.png"
                alt=""
            />
            
            <div className="header__search" >
                <input className="header__searchInput" type="text"/>
                <SearchIcon className="header__searchIcon" />
            </div>

            <div className="header__nav">
                
                {state.user ? (
                    <div className="header__option">

                        <div class="dropdown">

                            <div className="dropbtn"><AccountCircleIcon/></div>
                            <div class="dropdown-content">
                                <span className="header__optionLineTwo">Hi {state.user.name? state.user.name : state.user.title}! </span>
                                <a href="/dashboard">Profile</a>
                                <button className="header__option__button" onClick={logout}>Sign out</button>
                            </div>

                        </div>
                        </div>

                    )

                :   (<Link to="/login">
                        <div className="header__option">
                        <span className="header__optionLineTwo"> Sign in </span>
                        </div>
                    </Link>)
                }   

            </div>
        </div>
    )
}

export default Header
