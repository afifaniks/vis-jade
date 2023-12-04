import React from 'react'
import './Dashboard_Orders.css';

function Dashboard_Orders() {
    return (
        <div className="dashboard__orders">
            <p>All orders</p>

            <div className="dashboard__orders__searchOptions">

                <div className="dashboard__orders__dateinput">
                    <p>Filter by date</p>
                    <span>From: </span>
                    <input type="date" 
                        placeholder="Start date" /><br/>
                    <span>To: </span>
                    <input type="date" 
                        placeholder="End date" />
                </div>

                <div className="dashboard__orders__status">
                    <p>Filter By Status</p>
                    <select id="status" name="status">
                        <option value="Pending">Pending</option>
                        <option value="Confirmed">Confirmed</option>
                        <option value="Cancelled">Cancelled</option>
                        <option value="In_Progress">In Progress</option>
                        <option value="Completed">Completed</option>
                        <option value="Refunded">Refunded</option>

                    </select>
                </div>


                <div >
                    <br/><br/>
                    <input className = "dashboard__orders__search"
                    placeholder="Search"/>

                </div>

            </div>

            <div className="dashboard_orders_names">
                <span>Customer</span> 
                <span>Order number</span> 
                <span>Status</span>
                <span>Start date</span> 
                <span>Type</span> 
                <span>Products</span> 
            </div>

        </div>
    )
}

export default Dashboard_Orders
