import axios from "axios";
import { API_ENDPOINTS } from "../constants/api.consts";
import { Login } from "../interfaces/login";

export const loginUser = async (payload: Login) => {
  try {
    const response = await axios.post(API_ENDPOINTS.LOGIN, payload, {
      headers: { "Content-Type": "application/json" },
    });

    if (typeof response.data === "boolean") {
      return response.data;
    } else {
      throw new Error("Unexpected response from server");
    }
  } catch (error: any) {
    throw error.response?.data || { message: "Login failed" };
  }
};
