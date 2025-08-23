import { Box, Button, TextField, Typography, Paper, useTheme, SvgIcon, } from "@mui/material";
import useLogin from "./login.use";
import PageLayout from "../common/page.layout";
import { FcGoogle } from "react-icons/fc";
import SnackbarAlert from "../common/snackbar";
import { BoxBackgroundStyle1, ButtonBlackAndWhite1 } from "../common/themeStyles";

const LoginPage: React.FC = () => {
  const { email, password, setEmail, setPassword, handleSubmit, errorMessage, setErrorMessage } = useLogin();

  const theme = useTheme();

  return (
    <>
      <PageLayout>
        <Box display={"flex"} justifyContent={"center"}
          alignItems={"center"} height={"100vh"}
        >
          <Paper
            elevation={3}
            sx={{
              width: { xs: "90%", sm: 470 }, p: 2,
              ...BoxBackgroundStyle1(theme),
            }}
          >
            <Typography variant="h5" gutterBottom align="center" fontWeight="100">
              Login
            </Typography>

            <form onSubmit={handleSubmit}>
              <TextField label="Email" type="email" fullWidth margin="dense" size="small" value={email}
                onChange={(e) => setEmail(e.target.value)} required />

              <TextField label="Password" type="password" fullWidth margin="dense" size="small"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required />

              <Button type="submit" variant="contained" size="large" fullWidth
                sx={{ mt: 2, ...ButtonBlackAndWhite1(theme) }}
              > Login
              </Button>

              <Button variant="outlined" fullWidth startIcon={<SvgIcon component={FcGoogle as any}
                inheritViewBox />} sx={{ mt: 2 }} onClick={() => alert("Google login placeholder")}>
                Login with Google
              </Button>

            </form>
          </Paper>
        </Box>
      </PageLayout>

      <SnackbarAlert
        open={!!errorMessage}
        message={errorMessage}
        severity="error"
        onClose={() => setErrorMessage("")}
      />
    </>
  );
};

export default LoginPage;
