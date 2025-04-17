import {QuerityComponentsProvider, defaultQuerityComponents, ComponentOverrides} from "@queritylib/react";
import logo from './assets/querity-logo.jpg';
import OrderTable from "./feature/orders/components/OrderTable";
import './App.css'

const querityComponents: ComponentOverrides = {
  ...defaultQuerityComponents,
};

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
        <QuerityComponentsProvider value={querityComponents}>
          <OrderTable/>
        </QuerityComponentsProvider>
      </>
  )
}

export default App
