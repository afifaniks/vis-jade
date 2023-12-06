export const initialState = {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : null,
    user: null
};

export const Reducer = (state, action) => {
    switch(action.type) {
        case 'SET_USER':
            return {
                ...state,
                token: action.token
            }

        case 'SET_USER_INFO':
            return {
                ...state,
                user: action.user
            }

        default:
            return state;
    }
};
