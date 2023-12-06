import React from 'react'
import './Product_dashboard.css';
import EditSharpIcon from '@material-ui/icons/EditSharp';
import DeleteOutlineSharpIcon from '@material-ui/icons/DeleteOutlineSharp';
import { useNavigate } from 'react-router-dom';
import { API_URL } from '../EnviormentVariables';
import { useStateValue } from '../StateManager/StateProvider';

function Product_dashboard(props) {
    const history = useNavigate();
    const [state, dispatch] = useStateValue();
    let status = null;
    
    const handleResponse = (response) => {
        console.log(response);
        if(status === 204) {
            window.location.reload();
            alert("Product successfully deleted!");
        } else {
            window.location.reload();
            alert("Somthing went wrong try again!");
        }
    }

    const deleteProduct = (event, item) => {
        event.preventDefault();
        
        const url = `${API_URL}/api/product/${item.id}/`;
        fetch(url, {
            method: 'DELETE',
            headers: {
                'Authorization': `Token ${state.token}`
            }
        })
        .then(resp => {
            status = resp.status;
            handleResponse(resp);
        })
        // .then(res => )
        .catch(errors => console.log(errors));
    }
    
    return (
        <div className="product__dashboard">
            <p><span>{props.item.title}</span></p>
            {/* <p><span>Description: {props.item.description}</span></p> */}
            <p><span>{props.item.category}</span></p>

            <p><span> {props.item.needed_deposit}</span></p>
            {/* <p><span>avg_rating: {props.item.avg_ratings}</span></p>
            <p><span>number_of_rating: {props.item.number_of_ratings}</span></p> */}
            <p><span> {props.item.quantity}</span></p>
            {/* <br /> */}
            <EditSharpIcon className="product__dashboard__edit" onClick={ () => {
                history.push({
                    pathname: "/dashboard/product",
                    state: {product: props.item}
                })}
            }/>
            <DeleteOutlineSharpIcon 
                className="product__dashboard__delete"
                onClick={event => deleteProduct(event, props.item)}
            />
        </div>
    )
}

export default Product_dashboard
