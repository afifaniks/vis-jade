import React from 'react'
import "./Product.css"

function Product(props) {
    return (
        <div className="product">
            <div className="product__info">
                <p>{props.title}</p>
                {/* <div className="product__rating">
                    {Array(props.rating).fill().map((_,i) => (
                        <p>⭐</p>
                    ))}
                </div> */}
                <p>Rate: {props.num_of_ratings != 0 ? props.rating : "-"}/5</p>
                <p>Rent fee : {props.needed_deposit}</p>
            </div>

            <img src={props.image} alt=""/>
        </div>
    )
}

export default Product
