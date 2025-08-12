import React, { useState } from "react";
import API from "../api";

export default function Login({ onLogin }) {
  const [username, setUsername] = useState("admin");
  const [password, setPassword] = useState("admin123");
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const submit = async (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    try {
      const form = new URLSearchParams();
      form.append("username", username);
      form.append("password", password);

      await API.post("/auth/login", form.toString(), {
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        withCredentials: true, // keep session cookie
      });

      onLogin && onLogin();
    } catch (err) {
      console.error("Login error:", err);
      setError("Login failed — check credentials");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={submit}>
      <div className="card" style={{ maxWidth: 420 }}>
        <label>Username</label>
        <input value={username} onChange={(e) => setUsername(e.target.value)} />

        <label>Password</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <div style={{ marginTop: 8 }}>
          <button type="submit" disabled={loading}>
            {loading ? "Logging in..." : "Login"}
          </button>
        </div>

        {error && <small className="error">{error}</small>}
      </div>
    </form>
  );
}
