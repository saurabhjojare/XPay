import { useState } from "react";
import { loginUser } from "../../services/user.service";
import { LoginProps } from "./LoginPage.types";

const useLogin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const submitLogin = async (): Promise<boolean> => {

    const payload: LoginProps = { email, password };

    try {
      const response = await loginUser(payload);
      if (response === true) {
        return true;
      }
      setErrorMessage("Invalid email or password");
      return false;
    } catch (error: any) {
      setErrorMessage(error.message || "Login error");
      return false;
    }
  };

  return {
    email,
    password,
    setEmail,
    setPassword,
    submitLogin,
    errorMessage,
    setErrorMessage,
  };
};

export default useLogin;
