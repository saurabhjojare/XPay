import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Snackbar,
  Alert,
  useTheme,
  SvgIcon,
} from "@mui/material";
import useLogin from "./login.use";
import PageLayout from "../common/page.layout";
import { FcGoogle } from "react-icons/fc";

const LoginPage: React.FC = () => {
  const {
    email,
    password,
    setEmail,
    setPassword,
    handleSubmit,
    errorMessage,
    setErrorMessage,
  } = useLogin();

  const theme = useTheme();

  return (
    <>
      <PageLayout centerContent>
        <Paper
          elevation={3}
          sx={{
            width: { xs: "90%", sm: 470 }, p: 2,
            backgroundColor:
              theme.palette.mode === 'dark'
                ? "#212121"
                : theme.palette.background.paper,
          }}
        >
          <Typography variant="h5" gutterBottom align="center" fontWeight="100">
            Login
          </Typography>

          <form onSubmit={handleSubmit}>
            <TextField
              label="Email"
              type="email"
              fullWidth
              margin="dense"
              size="small"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <TextField
              label="Password"
              type="password"
              fullWidth
              margin="dense"
              size="small"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            <Button
              type="submit"
              variant="contained"
              size="large"

              fullWidth
              sx={{
                mt: 2,
                backgroundColor:
                  theme.palette.mode === "dark"
                    ? theme.palette.common.white
                    : theme.palette.common.black,
                color:
                  theme.palette.mode === "dark"
                    ? theme.palette.common.black
                    : theme.palette.common.white,
                "&:hover": {
                  backgroundColor:
                    theme.palette.mode === "dark"
                      ? theme.palette.grey[200]
                      : theme.palette.grey[900],
                },
              }}
            >
              Login
            </Button>

            {/* Dummy Google login button */}
            <Button
              variant="outlined"
              fullWidth
              startIcon={
                <SvgIcon component={FcGoogle as any} inheritViewBox />
              }
              sx={{ mt: 2 }}
              onClick={() => alert("Google login placeholder")}
            >
              Login with Google
            </Button>
          </form>
        </Paper>
      </PageLayout>

      <Snackbar
        open={!!errorMessage}
        autoHideDuration={3000}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        onClose={() => setErrorMessage("")}
      >
        <Alert
          onClose={() => setErrorMessage("")}
          severity="error"
          sx={{ width: "100%" }}
        >
          {errorMessage}
        </Alert>
      </Snackbar>
    </>
  );
};

export default LoginPage;
