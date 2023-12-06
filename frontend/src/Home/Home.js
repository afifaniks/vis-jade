import React, { useEffect } from 'react'
import { GetUser } from '../App'
import Product from '../Products/Product'
import { useStateValue } from '../StateManager/StateProvider'
import './Home.css'
import Header from '../Header/Header'

function Home() {
    const [state, dispatch] = useStateValue()
    let user;

    // useEffect( async () => {
    //     user = await GetUser(state);
    //     await dispatch({
    //         type: 'SET_USER_INFO',
    //         user: user
    //     });
    // }, [] )
    
    return (
        <div className="home">
            <div className="home__container">
                <div className="home__describe">
                    Vehicle Insurance System
                </div>
            </div>
        </div>
    )
}

export default Home
