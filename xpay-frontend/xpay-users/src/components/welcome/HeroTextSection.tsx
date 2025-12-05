import { Typography, Box } from "@mui/material";

const HeroTextSection = () => {
  return (
    <Box textAlign="center" sx={{ mb: 3 }}>
      <Typography variant="h4" fontWeight={600} gutterBottom>
        Instant short term loans between real people
      </Typography>

      <Typography variant="body1">
        Fast access to money without banks, paperwork, or friction.
      </Typography>
    </Box>
  );
};

export default HeroTextSection;
