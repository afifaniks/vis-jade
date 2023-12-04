import React, { createContext, useContext, useReducer } from 'react';

export const SteteContext = createContext();

export const StateProvider = ({ reducer, initialState, children }) => (
    <SteteContext.Provider value={useReducer(reducer, initialState)}>
        {children}
    </SteteContext.Provider>
);

export const useStateValue = () => useContext(SteteContext);
