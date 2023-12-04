import React, { useState, useEffect } from 'react';
import './Dashboard.css';
import { useStateValue } from '../StateManager/StateProvider';
import { GetUser } from '../App';
import { Link, useNavigate } from 'react-router-dom';
import Header_dashboard from '../Header/Header_dashboard';
import Dashboard_Inventory from './Dashboard_Inventory';
import Dashboard_Orders from './Dashboard_Orders';
import Dashboard_MyAccount from './Dashboard_MyAccount';

function Dashboard() {
    const history = useNavigate();
    const [state, dispatch] = useStateValue();
    let user;
    let products = [];
    let [p, setProducts] = useState([]);
    const [componentnum, setComponentnum] = useState(1);

    // useEffect( async () => {
    //     user = await GetUser(state);
    //     await dispatch({
    //         type: 'SET_USER_INFO',
    //         user: user
    //     });

    //     if(user?.role != 'company') {
    //         history.push({
    //             pathname: "/login",
    //             state: {massage: 'You have to login to your company account first!'}
    //         })
    //         return;
    //     }

    //     const loadProducts = async () => { return await Promise.all(
    //         user.products.map( async item => {
    //             await fetch(item, {
    //                 method: 'GET',
    //                 headers: {
    //                     'Content-Type': 'application/json',
    //                     'Authorization': `Token ${state.token}`
    //                 },
    //             })
    //             .then(resp => resp.json())
    //             .then(res => {
    //                 // console.log('current list: ', products)
    //                 // console.log('new item: ', res);
    //                 products = [...products, res];
    //                 // console.log('updated list: ', products);
    //             })
    //             .catch(errors => console.log(errors));
    //         })
    //     )};

    //     loadProducts().then(() => {
    //         setProducts(products);
    //         console.log(products);
    //     });
    // }, [] )
    
    return (
        <div className="dashboard">
            <div className="dashboard__left">
                <div className="dashboard__left__header">
                    <Link to="/">
                        <img 
                            className="dashboard__left__logo"
                            src="http://pngimg.com/uploads/hummingbird/hummingbird_PNG66.png"
                            alt="img"
                        />
                    </Link>
                </div>

                <p> My Dashboard</p>
                <div className="dashboard__left__operations">

                    <div onClick={() => setComponentnum(1)}>
                        My insurance
                    </div>
                    
                    <div onClick={() => setComponentnum(2)}>
                        Claim insurance
                    </div>

                </div>
                
                <p> My profile</p>
                <div className="dashboard__left__management">
                    <div onClick={() => setComponentnum(3)}>
                        My Account
                    </div>

                </div>
            </div>

            <div className="dashboard__right">

                <Header_dashboard User={user}/>

                {componentnum === 1 ? (<Dashboard_Inventory  products = {p}/>) : null}

                {componentnum === 2 ? (<Dashboard_Orders />) : null}

                {componentnum === 3 ? (<Dashboard_MyAccount />) : null}

            </div>
        </div>
    )
}

export default Dashboard
