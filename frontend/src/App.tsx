import {QuerityComponentsProvider, defaultQuerityComponents, ComponentOverrides} from "@queritylib/react";
import logo from './assets/querity-logo.jpg';
import OrderTable from "./feature/orders/components/OrderTable";
import './App.css'

const querityComponents: ComponentOverrides = {
  ...defaultQuerityComponents,
  Input: (props) => <input {...props} className={`${props.className} border p-2`} />,
  Select: (props) => <select {...props} className={`${props.className} border p-2`} />,
  Button: (props) => <button {...props} className={`${props.className} bg-gray-200 p-2 cursor-pointer`} />,
  Checkbox: (props) => (
      <label className="inline-flex items-center cursor-pointer">
        <input type="checkbox" {...props} className="sr-only peer"/>
        <div
            className="relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600 dark:peer-checked:bg-blue-600"></div>
        <span className="ms-3 text-sm font-medium text-gray-900 dark:text-gray-300">{props.label}</span>
      </label>
  )
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
