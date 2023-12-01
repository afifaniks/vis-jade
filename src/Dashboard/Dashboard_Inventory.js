import React from 'react'
import { Link } from 'react-router-dom';
import Product_dashboard from '../Products/Product_dashboard';
import './Dashboard_Inventory.css';
function Dashboard_Inventory(props) {
    return (
        <div className="dashboard_inventory">
            <div className="dashboard_inventory_header">
                <p>My insurance package</p>
                <Link to="/dashboard/product">
                    <button 
                        type= 'submit'
                        className="dashboard_inventory_header__addProduct" >
                        {/* onClick={(event) => signup(event, username, email, password, role)} > */}
                            Request insurance package
                    </button>
                </Link>
            </div>

            <div className="dashboard_inventory_names">
                <span>Title</span> 
                <span>Car</span> 
                <span>Price</span>
                <span>Expiration data</span> 

            </div>

            <div className="dashboard_inventory_products">
                { props.products.map( item => (
                    <div className="dashboard__item" key={item.id}>
                        <Product_dashboard item = {item}/>
                    </div>
                ) )}

            </div>
            
        </div>
    )
}

export default Dashboard_Inventory
