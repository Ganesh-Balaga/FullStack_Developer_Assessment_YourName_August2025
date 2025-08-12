import axios from "axios";

const API = axios.create({
  baseURL: "/api", // proxy forwards to backend
  timeout: 10000,
  withCredentials: true, // important: send cookies (JSESSIONID)
});

export default API;
