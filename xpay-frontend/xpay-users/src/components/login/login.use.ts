import { useState } from "react";
import { loginUser } from "../../services/user.service";
import { Login as LoginPayLoad } from "../../interfaces/login";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../../constants/consts";

const useLogin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload: LoginPayLoad = { email, password };

    try {
      const response = await loginUser(payload);
      if (response === true) {
        navigate(ROUTES.HOME);
      } else {
        setErrorMessage("Login failed: Invalid credentials");
      }
    } catch (error: any) {
      console.error("Login error:", error);
      setErrorMessage(error.message || "Login failed");
    }
  };

  return {
    email,
    password,
    setEmail,
    setPassword,
    handleSubmit,
    errorMessage,
    setErrorMessage,
  };
};

export default useLogin;
