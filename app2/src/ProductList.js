import React from "react";
import './ProductList.css';
export default class ProductList extends React.Component {
    constructor() {
        super();
        this.state = {
            prod: []
        }
    }

    componentDidMount() {
        this.refreshList();
        console.log(this.state.prod);
    }

    refreshList() {
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => {
                this.setState({ prod: data })
            })
    }

    render() {
        const { prod } = this.state;
        return (
            <div>
                <h1>Available Products</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Product Type</th>
                            <th>Product Company</th>
                        </tr>
                    </thead>
                    <tbody>
                        {prod.map(prod =>
                            <tr key={prod.id}>
                                 <td>{prod.id}</td>
                                <td>{prod.name}</td>
                                <td>{prod.type}</td>
                                <td>{prod.company}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        );
    }
}