import { useNavigate } from "react-router-dom";
import useLogin from "./useLogin"
import { FcGoogle } from "react-icons/fc";
import { ROUTES } from "../../constants/global.consts";
import PageLayout from "../common/PageLayout";
import { Box, Button, Paper, SvgIcon, TextField, Typography, useTheme } from "@mui/material";
import { BoxBackgroundStyle1, ButtonBlackAndWhite1 } from "../common/themeStyles";
import SnackbarAlert from "../common/SnackbarAlert";

const LoginPage = () => {
  const { email, password, setEmail, setPassword, submitLogin, errorMessage, setErrorMessage } = useLogin();
  const navigate = useNavigate();
  const theme = useTheme();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const success = await submitLogin();
    if (success) {
      navigate(ROUTES.HOME);
    }
  };

return (
      <PageLayout>
        <Box display="flex" justifyContent="center" alignItems="center" height="100vh">
          <Paper elevation={3} sx={{ width: { xs: "90%", sm: 470 }, p: 2, ...BoxBackgroundStyle1(theme) }}>
           <Typography variant="h5" gutterBottom align="center" fontWeight={100}>Login</Typography>
           <form onSubmit={handleSubmit}>
            <TextField label = "Email" type="email" fullWidth margin="dense" size="small" value={email} onChange={(e) => setEmail(e.target.value)} required/>
            <TextField label = "Password" type="password" fullWidth margin="dense" size="small" value={password} onChange={(e) => setPassword(e.target.value)} required/>
            <Button type="submit" variant="contained" size="large" fullWidth sx = {{ mt : 2, ...ButtonBlackAndWhite1(theme)}}> Login </Button>
            <Button variant="outlined" fullWidth startIcon={<SvgIcon component={FcGoogle as any} inheritViewBox />} sx= {{mt : 2}} onClick={() => alert("Google login placeholder")}>Login with Google</Button>
           </form>
          </Paper>
        </Box>
      
      <SnackbarAlert
        open={!!errorMessage}
        message={errorMessage}
        severity="error"
        onClose={() => setErrorMessage("")}
      />
      </PageLayout>
  );
};

export default LoginPage;