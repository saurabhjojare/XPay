import { Box, Typography, Stack } from "@mui/material";
import DoneAllIcon from "@mui/icons-material/DoneAll";

const benefits = [
  "Money when you need it, without the waiting",
  "Borrow and lend without the banks in the middle",
  "Clear agreements and timelines without confusion",
];

const QuickBenefitsSection = () => {
  return (
    <Box textAlign="center" sx={{ mt: 4, mb: 6 }}>
      <Stack direction="column" spacing={2}>
        {benefits.map((text, index) => (
          <Stack
            key={index}
            direction="row"
            spacing={1}
            justifyContent="center"
            alignItems="center"
          >
            <DoneAllIcon fontSize="small" />
            <Typography variant="body1">{text}</Typography>
          </Stack>
        ))}
      </Stack>
    </Box>
  );
};

export default QuickBenefitsSection;
