import React from 'react';
import logo from './logo.svg';
import Dashboard from './component/Dashboard';
import AddProject from './component/projects/AddProject';
import UpdateProject from './component/projects/UpdateProject';
import Header from './component/layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router,Route } from "react-router-dom";
import { Provider } from  "react-redux";
import store from "./store";

function App() {
  return (
    <Provider store={store}>
    <Router>
    <div className="App">
    <Header/>
    
    <Route path="/dashboard" component={Dashboard} />
    <Route path="/addProject" component={AddProject} />
    <Route path="/updateProject/:id" component={UpdateProject} />
    </div>
    </Router>
    </Provider>
  );
}

export default App;
