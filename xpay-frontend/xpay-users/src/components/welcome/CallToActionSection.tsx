import { Box, Button, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../../constants/global.consts";
import { useTheme } from "@mui/material";
import { ButtonBlackAndWhite1 } from "../common/themeStyles";

const CallToActionSection = () => {
  const navigate = useNavigate();
  const theme = useTheme();

  return (
    <Box textAlign="center">
      <Button
        variant="contained"
        size="large"
        fullWidth
        sx={{ mb: 2, ...ButtonBlackAndWhite1(theme) }}
        onClick={() => navigate(ROUTES.SIGNUP)}
      >
        Signup
      </Button>

      <Typography variant="body2">
        Already have an account?{" "}
        <span
          style={{ textDecoration: "underline", cursor: "pointer" }}
          onClick={() => navigate(ROUTES.ROOT)}
        >
          Login
        </span>
      </Typography>
    </Box>
  );
};

export default CallToActionSection;
