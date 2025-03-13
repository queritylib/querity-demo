import './App.css'
import OrderTable from "./feature/orders/components/OrderTable";
import logo from './assets/querity-logo.jpg';


function App() {
  return (
      <>
        <div className="flex items-center justify-center my-4">
          <img src={logo} alt="Querity Logo" className="w-20 mr-4" />
          <div>
            <h1 className="text-3xl font-bold text-blue-600">Querity DEMO</h1>
            <p className="italic">Open-source Java query builder for SQL and NoSQL.</p>
          </div>
        </div>
        <OrderTable/>
      </>
  )
}

export default App
