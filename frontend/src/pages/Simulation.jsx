import React, { useState } from "react";
import API from "../api";

export default function Simulation() {
  const [drivers, setDrivers] = useState(2);
  const [startTime, setStartTime] = useState("09:00");
  const [maxHours, setMaxHours] = useState(8);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const run = async () => {
    setLoading(true);
    setResult(null);
    try {
      const body = {
        numberOfDrivers: parseInt(drivers, 10),
        routeStartTime: startTime,
        maxHoursPerDriver: parseFloat(maxHours),
      };
      const res = await API.post("/simulate", body);
      setResult(res.data);
    } catch (err) {
      alert(err?.response?.data?.error || "Simulation failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <h2>Run Simulation</h2>
      <label>Number of drivers</label>
      <input
        type="number"
        value={drivers}
        onChange={(e) => setDrivers(e.target.value)}
      />
      <label>Route start time (HH:mm)</label>
      <input
        type="time"
        value={startTime}
        onChange={(e) => setStartTime(e.target.value)}
      />
      <label>Max hours per driver</label>
      <input
        type="number"
        value={maxHours}
        onChange={(e) => setMaxHours(e.target.value)}
      />
      <div style={{ marginTop: 8 }}>
        <button onClick={run} disabled={loading}>
          {loading ? "Running..." : "Run Simulation"}
        </button>
      </div>

      {result && (
        <div style={{ marginTop: 12 }}>
          <h3>Result</h3>
          <div>Total Profit: ₹{result.totalProfit}</div>
          <div>Efficiency: {result.efficiency}%</div>
          <div>
            On-time / Total: {result.onTimeOrders} / {result.totalOrders}
          </div>

          <h4>Orders (first 10)</h4>
          <pre
            style={{
              maxHeight: 220,
              overflow: "auto",
              background: "#f6f6f6",
              padding: 8,
            }}
          >
            {JSON.stringify(result.orderResults.slice(0, 10), null, 2)}
          </pre>
        </div>
      )}
    </div>
  );
}
