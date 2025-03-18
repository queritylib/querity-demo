import {useState} from 'react';
import {Order} from "../models/Order";
import showError from "../../../common/ToastNotification";
import useEffectOnMount from "../../../common/useEffectOnMount";

export interface Result<T> {
  items: T[];
  totalCount: number;
}

export interface ErrorResponse {
  error: string;
  message: string;
  path: string;
  status: number;
  timestamp: string;
  trace?: string;
}

const OrderTable = () => {
  const [query, setQuery] = useState<string>('');
  const [orders, setOrders] = useState<Order[]>([]);
  const [totalCount, setTotalCount] = useState<number>(0);
  const [loading, setLoading] = useState<boolean>(false);

  const fetchOrders = async (query?: string) => {
    setLoading(true);
    try {
      const response = await fetch(`/api/orders?q=${query || ''}`);
      if (response.ok) {
        const data: Result<Order> = await response.json();
        setOrders(data.items);
        setTotalCount(data.totalCount);
      } else {
        try {
          const error: ErrorResponse = await response.json();
          showError(new Error(error.message || error.error));
        } catch (e) {
          console.error('Error reading error response:', e);
          throw new Error(response.statusText);
        }
      }
    } catch (error) {
      console.error('Error fetching orders:', error);
      showError(error);
    } finally {
      setLoading(false);
    }
  };

  useEffectOnMount(() => {
    fetchOrders();
  }, []);

  const predefinedQueries = [
    'orderNumber=500',
    'placementDate>="2024-07-01T00:00:00.000Z" page 1,10',
    'shippingCustomer.address.country="Italy"',
    'distinct and(rows.totalPrice>1000,currency="EUR") sort by placementDate desc page 1,20'
  ];

  const choosePredefinedQuery = async (query: string) => {
    setQuery(query)
    await fetchOrders(query);
  }


  return (
      <div className="container mx-auto p-4">
        <h1 className="text-2xl font-bold mb-4">Orders List</h1>
        <div className="mb-4">
          <input
              type="text"
              value={query}
              onChange={(e) => setQuery(e.target.value)}
              onKeyDown={(e) => e.key === "Enter" && fetchOrders(query)}
              placeholder="Enter a query"
              className="border p-2 mr-2 w-200"
          />
          <button type="submit" className="bg-blue-500 text-white p-2 mr-2 cursor-pointer"
                  onClick={() => fetchOrders(query)}>Search
          </button>
          <select
              value={query}
              onChange={(e) => choosePredefinedQuery(e.target.value)}
              className="border p-2 w-120"
          >
            <option value="" className="text-gray-400">Choose a predefined query</option>
            {predefinedQueries.map((query) => (
                <option key={query} value={query}>
                  {query}
                </option>
            ))}
          </select>
        </div>
        <h2 className="text-xl mb-4">Total Orders: {totalCount}</h2>
        <table className="min-w-full bg-white">
          <thead>
          <tr>
            <th className="py-2 px-4 border-b">Order Number</th>
            <th className="py-2 px-4 border-b">Placement Date</th>
            <th className="py-2 px-4 border-b">IP Address</th>
            <th className="py-2 px-4 border-b">Currency</th>
            <th className="py-2 px-4 border-b">Total Price</th>
          </tr>
          </thead>
          <tbody>
          {
            loading ? (
                <tr>
                  <td colSpan={5}>
                    <div className="flex justify-center items-center">
                      <div className="animate-spin rounded-full h-8 w-8 border-t-4 mt-4" role="status">
                        <span className="invisible">Loading...</span>
                      </div>
                    </div>
                  </td>
                </tr>
            ) : (
                orders.map((order) => (
                    <tr key={order.orderNumber}>
                      <td className="py-2 px-4 border-b">{order.orderNumber}</td>
                      <td className="py-2 px-4 border-b">{order.placementDate}</td>
                      <td className="py-2 px-4 border-b">{order.ipAddress}</td>
                      <td className="py-2 px-4 border-b">{order.currency}</td>
                      <td className="py-2 px-4 border-b">{order.totalPrice}</td>
                    </tr>
                ))
            )
          }
          </tbody>
        </table>
      </div>
  );
};

export default OrderTable;
