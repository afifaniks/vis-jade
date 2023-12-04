import React,{useState, useEffect} from 'react';
import { GetUser } from '../App';
import { useStateValue } from '../StateManager/StateProvider';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { API_URL } from '../EnviormentVariables';
import './ProductForm.css'
import AddBoxIcon from '@material-ui/icons/AddBox';
function ProductForm(props) {
    let user;
    const history = useNavigate();
    const location = useLocation();
    const [state, dispatch] = useStateValue();
    let product = location?.state?.product ? location.state.product : null;
    const [title, setTitle] = useState(product?.title ? product.title : '');
    const [description, setDescription] = useState(product?.description ? product.description : '');
    const [price, setPrice] = useState(product?.price ? product.price : '');
    const [quantity, setQuantity] = useState(product?.quantity ? product.quantity : '');
    const [categories, setCategories] = useState([]);
    let status = null;
    let selected_categories = product ? [...product.category] : []

    const [tags, setTags] = useState([{
        'tag': '',
    }])

    useEffect( async () => {
        user = await GetUser(state);
        dispatch({
            type: 'SET_USER_INFO',
            user: user
        });

        // if(user?.role != 'company') {
        //     history.push({
        //         pathname: "/login",
        //         state: {massage: 'You have to login to your company account first!'}
        //     })
        // }

        const url = `${API_URL}/api/getcategorylist/`;
        fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': `Token ${state.token}`
            }
        })
        .then((resp) => resp.json())
        .then((res) => {
            setCategories(res);
        })
        .catch((errors) => console.log(errors))
    }, [])
    
    // useEffect(() => console.log(categories), [categories])
    // useEffect(() => console.log(selected_categories), [selected_categories])

    const handleResponse = (response) => {
        console.log(response);
        if(status === 201 || status === 200) {
            history.push("/dashboard");
            alert(product ? "Product successfully updated!" : "Product successfully created!");
        } else {
            history.push("/dashboard");
            alert("Somthing went wrong try again!");
        }
    }

    const createupdateProduct_TitDescPriQuan = (event, title, description, price, quantity) => {
        event.preventDefault();
        
        // const url = (product ? `${API_URL}/api/product/${product.id}/` : `${API_URL}/api/product/`);
        // fetch(url, {
        //     method: product ? 'PUT' : 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //         'Authorization': `Token ${state.token}`
        //     },
        //     body: JSON.stringify({
        //         'title' : title,
        //         'description': description, 
        //         'price': price,
        //         'category': arrayTostring(selected_categories)
        //     })
        // })
        // .then(resp => {
        //     status = resp.status;
        //     return resp.json();
        // })
        // .then(res => handleResponse(res))
        // .catch(errors => console.log(errors));
        openForm_CatSubTag();
    }


    const createupdateProduct_CatSubTag = (event, Category, SubCategory, Tags) => {
        event.preventDefault();
        
        openForm_Img();
    }

    const createupdateProduct_Img = (event) => {
        event.preventDefault();
        
    }

    const handleCategories = (event, categoryName) => {        
        if(event.target.checked) {
            if(selected_categories.indexOf(categoryName) === -1)
                selected_categories = [...selected_categories, categoryName]
        } else {
            selected_categories.splice(selected_categories.indexOf(categoryName), 1)
        }
    }

    const arrayTostring = (array) => {
        let string = '';
        array.map(item => string += (item+' '))
        return string;
    }

    function openForm_TitDescPriQuan() {
        document.getElementById("TitDescPriQuan-form").style.display = "block";
        document.getElementById("CatSubTag-form").style.display = "none";
        document.getElementById("Img-form").style.display = "none";
    }

    function openForm_CatSubTag() {
        document.getElementById("TitDescPriQuan-form").style.display = "none";
        document.getElementById("CatSubTag-form").style.display = "block";
        document.getElementById("Img-form").style.display = "none";
    }   

    function openForm_Img() {
        document.getElementById("TitDescPriQuan-form").style.display = "none";
        document.getElementById("CatSubTag-form").style.display = "none";
        document.getElementById("Img-form").style.display = "block";
    }   

    const add_new_Tag_input = (event) =>{
        setTags([...tags, {
            'title': '',
            'address': '',
            'display': 'false'
        }])
    }
    return (
        <div className="productForm">
            <div className="productForm_Container">
                <h1>Information of The New Product</h1>

                {/* <div className="TitDescPriQuanr" id="TitDescPriQuan-form"> */}
                    <form id="TitDescPriQuan-form">
                        <input 
                            placeholder="Title"
                            type='text' 
                            value={title} 
                            onChange={(e) => setTitle(e.target.value)}
                        />
                        <input 
                            placeholder="Description"
                            type='text' 
                            value={description} 
                            onChange={(e) => setDescription(e.target.value)}
                        />
                        <input 
                            placeholder="minimum deposit to rent"
                            type='number' 
                            min ="0"
                            value={price} 
                            onChange={(e) => setPrice(e.target.value)}
                        />
                        <input 
                            placeholder="available quantity"
                            type='number' 
                            min ="0"
                            value={quantity} 
                            onChange={(e) => setQuantity(e.target.value)}
                        />
                        {/* <div className = "buttuns"> */}
                            <button className="productForm_Container_button1"type='submit' onClick={(e) => createupdateProduct_TitDescPriQuan(e, title, description, price, quantity)}>
                                {product? 'Update Product' : 'Continue'}
                            </button>
                            <br></br>
                            <Link to="/dashboard">
                                <button className="productForm_Container_button1"type='submit' >Cancel</button>
                            </Link>
                        {/* </div> */}
                    </form>


                {/* </div> */}

                <form id="CatSubTag-form" className="productForm__CatSubTag">

                    <div className="productForm__CatSubTag__options">
                        <p>Select Category</p>
                        <select id="status" name="status" className="productForm__CatSubTag__dropdown">
                            <option value="...">...</option>
                            <option value="clothing">clothing</option>
                            <option value="Technology">Technology</option>
                            <option value="Sports">Sports</option>
                            <option value="Jewllery">Jewllery</option>
                            <option value="Housing">Housing</option>
                            <option value="Vehicle">Vehicle</option>
                        </select>
                    </div>

                    <div className="productForm__CatSubTag__options">
                        <p>Select Sub Category</p>
                        <select id="status" name="status" className="productForm__CatSubTag__dropdown">
                            <option value="...">...</option>
                            <option value="clothing">Mobiles</option>
                            <option value="Technology">Laptops</option>
                            <option value="Sports">Tablets</option>
                            <option value="Jewllery">Smart watches</option>
                            <option value="Housing">PCs</option>
                            <option value="Vehicle">Microwaves</option>
                            <option value="Vehicle">refrigerators</option>
                        </select>
                    </div>

                    <div className="productForm__CatSubTag__Tag">
                        <p>Special Tags </p>
                        <AddBoxIcon 
                            className = "productForm__CatSubTag__Tag__AddBoxIcon" 
                            onClick={(event) => add_new_Tag_input(event)}/>
                        <input className="productForm__CatSubTag__Tag__input"></input>
                    </div>


                    <button className="productForm_Container_button1"type='submit' onClick={(e) => createupdateProduct_CatSubTag(e, title, description, price, quantity)}>
                            {product? 'Update Product' : 'Continue'}
                    </button>
                    <br></br>
                    <Link to="/dashboard">
                        <button className="productForm_Container_button1"type='submit' >Cancel</button>
                    </Link>

                </form>


                <form id="Img-form" className="productForm__Img">

                    <div className="productForm__getImage__form__header">
                        <h4>Product Images</h4>
                        <button 
                            type='submit' 
                            className="signup__getAdrPhone__container__addButton" >
                            {/* onClick={(event) => add_new_Image_form(event)}> */}
                                add new image
                        </button>

                    </div>

                    <div>
                        <input type="file" id="myfile" name="myfile" />
                    </div>

                    <Link to="/dashboard">
                        <button className="productForm_Container_button1"type='submit' onClick={(e) => createupdateProduct_Img(e)}>
                                {product? 'Update Product' : 'Continue'}
                        </button>
                    </Link>
                    <br></br>
                    <Link to="/dashboard">
                        <button className="productForm_Container_button1"type='submit' >Cancel</button>
                    </Link>

                </form>

            </div>
        </div>
    )
}

export default ProductForm
