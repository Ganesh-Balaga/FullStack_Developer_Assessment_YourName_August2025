import React, { useEffect, useState } from "react";
import API from "../api";

export default function RouteManager() {
  const [list, setList] = useState([]);
  const [name, setName] = useState("");
  const [distance, setDistance] = useState(1);
  const [traffic, setTraffic] = useState("Low");
  const [baseTime, setBaseTime] = useState(30);

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = async () => {
    try {
      const res = await API.get("/routes");
      setList(res.data);
    } catch (e) {
      alert("Load routes failed");
    }
  };

  const create = async () => {
    try {
      await API.post("/routes", {
        name,
        distanceKm: parseFloat(distance),
        trafficLevel: traffic,
        baseTimeMinutes: parseFloat(baseTime),
      });
      setName("");
      setDistance(1);
      setTraffic("Low");
      setBaseTime(30);
      fetchList();
    } catch (e) {
      alert("Create failed");
    }
  };

  const remove = async (id) => {
    if (!confirm("Delete route?")) return;
    try {
      await API.delete(`/routes/${id}`);
      fetchList();
    } catch (e) {
      alert("Delete failed");
    }
  };

  return (
    <div className="card">
      <h2>Routes</h2>
      <label>Name</label>
      <input value={name} onChange={(e) => setName(e.target.value)} />
      <label>Distance (km)</label>
      <input
        type="number"
        value={distance}
        onChange={(e) => setDistance(e.target.value)}
      />
      <label>Traffic Level</label>
      <select value={traffic} onChange={(e) => setTraffic(e.target.value)}>
        <option>Low</option>
        <option>Medium</option>
        <option>High</option>
      </select>
      <label>Base Time (minutes)</label>
      <input
        type="number"
        value={baseTime}
        onChange={(e) => setBaseTime(e.target.value)}
      />
      <div style={{ marginTop: 8 }}>
        <button onClick={create}>Add Route</button>
      </div>

      <h3 style={{ marginTop: 12 }}>Route List</h3>
      <table className="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Dist</th>
            <th>Traffic</th>
            <th>BaseTime</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {list.map((r) => (
            <tr key={r.id}>
              <td>{r.name}</td>
              <td>{r.distanceKm}</td>
              <td>{r.trafficLevel}</td>
              <td>{r.baseTimeMinutes}</td>
              <td>
                <button onClick={() => remove(r.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
