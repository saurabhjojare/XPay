import { Box, useTheme } from "@mui/material";
import PageLayout from "../common/PageLayout";
import HeroTextSection from "./HeroTextSection";
import QuickBenefitsSection from "./QuickBenefitsSection";
import CallToActionSection from "./CallToActionSection";

const WelcomePage = () => {
  const theme = useTheme();

  return (
    <PageLayout>
      <Box
        display="flex"
        flexDirection="column"
        justifyContent="center"
        alignItems="center"
        height="100vh"
        sx={{ backgroundColor: theme.palette.background.default }}
      >
        <HeroTextSection />
        <QuickBenefitsSection />
      </Box>
    </PageLayout>
  );
};

export default WelcomePage;
