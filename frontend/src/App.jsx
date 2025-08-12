import React, { useState } from "react";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Simulation from "./pages/Simulation";
import DriverManager from "./pages/DriverManager";
import RouteManager from "./pages/RouteManager";
import OrderManager from "./pages/OrderManager";
import API from "./api";

export default function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [tab, setTab] = useState("dashboard");

  const handleLogout = async () => {
    try {
      await API.post("/auth/logout");
    } catch (e) {
      // ignore
    }
    setLoggedIn(false);
    setTab("dashboard");
  };

  if (!loggedIn) {
    return (
      <div className="card" style={{ maxWidth: 420, margin: "40px auto" }}>
        <h2>Manager Login</h2>
        <Login onLogin={() => setLoggedIn(true)} />
        <p style={{ fontSize: 12, marginTop: 8 }}>
          Use manager / manager123 (or other credentials you set in backend)
        </p>
      </div>
    );
  }

  return (
    <div>
      <header>
        <h1>GreenCart — Manager Panel</h1>
        <div>
          <button onClick={() => setTab("dashboard")}>Dashboard</button>
          <button onClick={() => setTab("simulate")}>Simulation</button>
          <button onClick={() => setTab("drivers")}>Drivers</button>
          <button onClick={() => setTab("routes")}>Routes</button>
          <button onClick={() => setTab("orders")}>Orders</button>
          <button onClick={handleLogout} style={{ marginLeft: 12 }}>
            Logout
          </button>
        </div>
      </header>

      <div className="container">
        <div style={{ flex: 1 }}>
          {tab === "dashboard" && <Dashboard />}
          {tab === "simulate" && <Simulation />}
          {tab === "drivers" && <DriverManager />}
          {tab === "routes" && <RouteManager />}
          {tab === "orders" && <OrderManager />}
        </div>
      </div>
    </div>
  );
}
