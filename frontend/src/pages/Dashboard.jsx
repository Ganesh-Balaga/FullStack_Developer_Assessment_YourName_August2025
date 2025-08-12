import React, { useEffect, useState } from "react";
import API from "../api";
import { PieChart, Pie, Tooltip, Cell } from "recharts";

export default function Dashboard() {
  const [latest, setLatest] = useState(null);

  useEffect(() => {
    loadLatest();
  }, []);

  const loadLatest = async () => {
    try {
      const res = await API.get("/simulations/latest");
      setLatest(res.data);
    } catch (err) {
      setLatest(null);
    }
  };

  if (!latest || !latest.timestamp) {
    return (
      <div className="card">
        <h2>Dashboard</h2>
        <p>No simulation yet. Run a simulation from the Simulation tab.</p>
      </div>
    );
  }

  const onTime = latest.onTimeOrders || 0;
  const total = latest.totalOrders || 0;
  const late = Math.max(0, total - onTime);
  const data = [
    { name: "On time", value: onTime },
    { name: "Late", value: late },
  ];
  const COLORS = ["#00C49F", "#FF8042"];

  return (
    <div className="card">
      <h2>Dashboard</h2>
      <div>
        Total Profit: ₹{Math.round((latest.totalProfit || 0) * 100) / 100}
      </div>
      <div>Efficiency: {Math.round((latest.efficiency || 0) * 100) / 100}%</div>
      <div>
        On-time / Total: {onTime} / {total}
      </div>

      <div style={{ marginTop: 12 }}>
        <PieChart width={300} height={220}>
          <Pie data={data} dataKey="value" outerRadius={80} label>
            {data.map((entry, i) => (
              <Cell key={i} fill={COLORS[i % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
        </PieChart>
      </div>
    </div>
  );
}
