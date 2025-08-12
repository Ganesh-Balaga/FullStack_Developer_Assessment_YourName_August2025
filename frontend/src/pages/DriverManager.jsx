import React, { useEffect, useState } from "react";
import API from "../api";

export default function DriverManager() {
  const [list, setList] = useState([]);
  const [name, setName] = useState("");
  const [cur, setCur] = useState(0);
  const [past, setPast] = useState(0);
  const [lastDay, setLastDay] = useState(0);

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = async () => {
    try {
      const res = await API.get("/drivers");
      setList(res.data);
    } catch (e) {
      alert("Load drivers failed");
    }
  };

  const create = async () => {
    try {
      await API.post("/drivers", {
        name,
        currentShiftHours: parseFloat(cur),
        pastWeekHours: parseFloat(past),
        lastDayHours: parseFloat(lastDay),
      });
      setName("");
      setCur(0);
      setPast(0);
      setLastDay(0);
      fetchList();
    } catch (e) {
      alert("Create failed");
    }
  };

  const remove = async (id) => {
    if (!confirm("Delete driver?")) return;
    try {
      await API.delete(`/drivers/${id}`);
      fetchList();
    } catch (e) {
      alert("Delete failed");
    }
  };

  return (
    <div className="card">
      <h2>Drivers</h2>
      <div>
        <label>Name</label>
        <input value={name} onChange={(e) => setName(e.target.value)} />
        <label>Current Shift Hours</label>
        <input
          type="number"
          value={cur}
          onChange={(e) => setCur(e.target.value)}
        />
        <label>Past Week Hours</label>
        <input
          type="number"
          value={past}
          onChange={(e) => setPast(e.target.value)}
        />
        <label>Last Day Hours</label>
        <input
          type="number"
          value={lastDay}
          onChange={(e) => setLastDay(e.target.value)}
        />
        <div style={{ marginTop: 8 }}>
          <button onClick={create}>Add Driver</button>
        </div>
      </div>

      <h3 style={{ marginTop: 12 }}>Driver List</h3>
      <table className="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Cur</th>
            <th>Past Week</th>
            <th>Last Day</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {list.map((d) => (
            <tr key={d.id}>
              <td>{d.name}</td>
              <td>{d.currentShiftHours}</td>
              <td>{d.pastWeekHours}</td>
              <td>{d.lastDayHours}</td>
              <td>
                <button onClick={() => remove(d.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
