import React, { useEffect, useState } from "react";
import API from "../api";

export default function OrderManager() {
  const [orders, setOrders] = useState([]);
  const [routes, setRoutes] = useState([]);
  const [value, setValue] = useState(100);
  const [routeId, setRouteId] = useState(null);
  const [deliveryTs, setDeliveryTs] = useState(
    new Date().toISOString().slice(0, 19)
  );

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [oRes, rRes] = await Promise.all([
        API.get("/orders"),
        API.get("/routes"),
      ]);
      setOrders(oRes.data);
      setRoutes(rRes.data);
      if (rRes.data.length > 0 && !routeId) setRouteId(rRes.data[0].id);
    } catch (e) {
      console.error(e);
      alert("Load failed");
    }
  };

  const create = async () => {
    try {
      // send route as object with id (backend expects object)
      const body = {
        valueRs: parseFloat(value),
        route: { id: parseInt(routeId) },
        deliveryTimestamp: new Date(deliveryTs).toISOString(),
      };
      await API.post("/orders", body);
      setValue(100);
      setDeliveryTs(new Date().toISOString().slice(0, 19));
      fetchData();
    } catch (e) {
      alert("Create order failed");
    }
  };

  const remove = async (id) => {
    if (!confirm("Delete order?")) return;
    try {
      await API.delete(`/orders/${id}`);
      fetchData();
    } catch (e) {
      alert("Delete failed");
    }
  };

  return (
    <div className="card">
      <h2>Orders</h2>
      <label>Order Value (₹)</label>
      <input
        type="number"
        value={value}
        onChange={(e) => setValue(e.target.value)}
      />
      <label>Route</label>
      <select
        value={routeId || ""}
        onChange={(e) => setRouteId(e.target.value)}
      >
        <option value="" disabled>
          Select route
        </option>
        {routes.map((r) => (
          <option key={r.id} value={r.id}>
            {r.name} ({r.distanceKm}km)
          </option>
        ))}
      </select>
      <label>Delivery timestamp</label>
      <input
        type="datetime-local"
        value={deliveryTs}
        onChange={(e) => setDeliveryTs(e.target.value)}
      />
      <div style={{ marginTop: 8 }}>
        <button onClick={create}>Create Order</button>
      </div>

      <h3 style={{ marginTop: 12 }}>Order List</h3>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Value</th>
            <th>Route</th>
            <th>DeliveryTs</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {orders.map((o) => (
            <tr key={o.id}>
              <td>{o.id}</td>
              <td>₹{o.valueRs}</td>
              <td>{o.route?.name || (o.route && o.route.id) || "—"}</td>
              <td>{o.deliveryTimestamp}</td>
              <td>
                <button onClick={() => remove(o.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
